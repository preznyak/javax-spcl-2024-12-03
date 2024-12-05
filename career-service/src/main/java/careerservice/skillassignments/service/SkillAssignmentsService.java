package careerservice.skillassignments.service;

import careerservice.NotFoundException;
import careerservice.skill.SkillHasBeenDeleted;
import careerservice.skill.SkillServicePort;
import careerservice.skill.service.SkillService;
import careerservice.skillassignments.command.AssignSkillsToEmployeeCommand;
import careerservice.skillassignments.model.LeveledSkill;
import careerservice.skillassignments.model.SkillAssignments;
import careerservice.skillassignments.view.EmployeeSkillsView;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SkillAssignmentsService {

    private final SkillAssignmentsRepository skillAssignmentsRepository;

    private final SkillServicePort skillService;

    @Transactional
    public EmployeeSkillsView assignSkillToEmployee(AssignSkillsToEmployeeCommand command) {

        List<Long> existingSkillIds = skillService.findSkillIdsIn(
                command.skills().stream().map(LeveledSkill::skillId).toList()
        );
        if (existingSkillIds.size() != command.skills().size()) {
            throw new IllegalArgumentException("Some skills do not exist");
        }

        Optional<SkillAssignments> skillAssignments = skillAssignmentsRepository.findByEmployeeId(command.employeeId());
        if (skillAssignments.isEmpty()) {
            SkillAssignments hired = SkillAssignments.hire(command.employeeId(), command.skills());
            skillAssignmentsRepository.save(hired);
            return new EmployeeSkillsView(hired.getEmployeeId(), hired.getLeveledSkills());
        }
        else {
            skillAssignments.get().learn(command.skills());
            return new EmployeeSkillsView(skillAssignments.get().getEmployeeId(), skillAssignments.get().getLeveledSkills());
        }
    }

    public EmployeeSkillsView getSkillAssignments(long employeeId) {
        SkillAssignments skillAssignments = skillAssignmentsRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new NotFoundException("Skills not found for employee: " + employeeId));
        return new EmployeeSkillsView(skillAssignments.getEmployeeId(), skillAssignments.getLeveledSkills());
    }

    @EventListener
    @Transactional
    public void handleSkillHasBeenDeleted(SkillHasBeenDeleted skillHasBeenDeleted) {
        // TODO
    }


}
