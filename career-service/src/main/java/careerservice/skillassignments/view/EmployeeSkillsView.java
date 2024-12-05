package careerservice.skillassignments.view;


import careerservice.skillassignments.model.LeveledSkill;

import java.util.List;

public record EmployeeSkillsView(long employeeId, List<LeveledSkill> skills) {

}
