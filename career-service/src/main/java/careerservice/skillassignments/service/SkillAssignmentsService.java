package careerservice.skillassignments.service;

import careerservice.NotFoundException;
import careerservice.skillassignments.command.AssignSkillsToEmployeeCommand;
import careerservice.skillassignments.model.SkillAssignments;
import careerservice.skillassignments.view.EmployeeSkillsView;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SkillAssignmentsService {

    private final SkillAssignmentsRepository skillAssignmentsRepository;

    @Transactional
    public EmployeeSkillsView assignSkillToEmployee(AssignSkillsToEmployeeCommand command) {
        Optional<SkillAssignments> skillAssignments = skillAssignmentsRepository.findByEmployeeId(command.employeeId());
        if (skillAssignments.isEmpty()) {
            SkillAssignments hired = SkillAssignments.hire(command.employeeId(), command.skills());
            skillAssignmentsRepository.save(hired);
            return new EmployeeSkillsView(hired.getEmployeeId(), hired.getLeveledSkills());
        } else {
            skillAssignments.get().learn(command.skills());
            return new EmployeeSkillsView(skillAssignments.get().getEmployeeId(), skillAssignments.get().getLeveledSkills());
        }
    }

    public EmployeeSkillsView getSkillAssignments(long employeeId) {
        SkillAssignments skillAssignments = skillAssignmentsRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new NotFoundException("Skills not found for employee: " + employeeId));
        return new EmployeeSkillsView(skillAssignments.getEmployeeId(), skillAssignments.getLeveledSkills());
    }
}
