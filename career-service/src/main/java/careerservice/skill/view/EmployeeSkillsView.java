package careerservice.skill.view;

import careerservice.skill.command.LeveledSkill;

import java.util.List;

public record EmployeeSkillsView(long employeeId, List<LeveledSkill> skills) {

}
