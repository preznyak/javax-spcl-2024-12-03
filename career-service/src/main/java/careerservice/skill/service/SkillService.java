package careerservice.skill.service;

import careerservice.skill.command.AssignSkillsToEmployeeCommand;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.command.LeveledSkill;
import careerservice.skill.model.AssignedSkill;
import careerservice.skill.model.Skill;
import careerservice.skill.view.EmployeeSkillsView;
import careerservice.skill.view.SkillView;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillService {

    private SkillRepository skillRepository;

    private AssignedSkillRepository assignedSkillRepository;

    private SkillMapper skillMapper;

    public SkillView create(CreateSkillCommand command) {
        Skill skill = new Skill(command.name());
        skillRepository.save(skill);
        return skillMapper.toView(skill);
    }

    public List<SkillView> listSkills() {
        return skillMapper.toViews(skillRepository.findAll());
    }

    @Transactional
    public void deleteSkillById(long id) {
        List<AssignedSkill> skills = assignedSkillRepository.findBySkillId(id);
        assignedSkillRepository.deleteAll(skills);
        skillRepository.deleteById(id);
    }

    @Transactional
    public EmployeeSkillsView assignSkillsToEmployee(AssignSkillsToEmployeeCommand command) {
        List<AssignedSkill> assignedSkills = assignedSkillRepository.findByEmployeeId(command.employeeId());
        Map<Long, AssignedSkill> skillsById = assignedSkills.stream()
                .collect(Collectors.toMap(assignedSkill -> assignedSkill.getSkill().getId(), Function.identity()));

        for (LeveledSkill leveledSkill: command.skills()) {
            AssignedSkill existingSkill = skillsById.get(leveledSkill.skillId());
            if (existingSkill == null) {
                AssignedSkill assignedSkill = new AssignedSkill();
                assignedSkill.setEmployeeId(command.employeeId());
                Skill skill = skillRepository.findById(leveledSkill.skillId())
                        .orElseThrow(() -> new NoSuchElementException("Skill not found: " + leveledSkill.skillId()));
                assignedSkill.setSkill(skill);
                assignedSkill.setLevel(leveledSkill.level());
                assignedSkillRepository.save(assignedSkill);
            }
            else {
                if (existingSkill.getLevel() < leveledSkill.level()) {
                    existingSkill.setLevel(leveledSkill.level());
                }
            }
        }

        return getAssignedSkills(command.employeeId());
    }

    public EmployeeSkillsView getAssignedSkills(long employeeId) {
        List<AssignedSkill> assignedSkills = assignedSkillRepository.findByEmployeeId(employeeId);
        List<LeveledSkill> skills = assignedSkills.stream().map(assignedSkill -> new LeveledSkill(assignedSkill.getSkill().getId(), assignedSkill.getLevel())).toList();
        return new EmployeeSkillsView(employeeId, skills);
    }

}
