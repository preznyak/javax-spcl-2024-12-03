package careerservice.skillassignments.command;

import careerservice.skillassignments.model.LeveledSkill;

import java.util.List;

public record AssignSkillsToEmployeeCommand(long employeeId, List<LeveledSkill> skills) {

}
