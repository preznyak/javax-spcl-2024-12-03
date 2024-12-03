package careerservice.skill.command;

import java.util.List;

public record AssignSkillsToEmployeeCommand(long employeeId, List<LeveledSkill> skills) {

}
