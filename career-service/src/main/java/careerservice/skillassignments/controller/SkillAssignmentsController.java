package careerservice.skillassignments.controller;

import careerservice.skillassignments.command.AssignSkillsToEmployeeCommand;
import careerservice.skillassignments.service.SkillAssignmentsService;
import careerservice.skillassignments.view.EmployeeSkillsView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assigned-skills")
@AllArgsConstructor
public class SkillAssignmentsController {

    private final SkillAssignmentsService skillAssignmentsService;

    @PostMapping
    public EmployeeSkillsView assignSkillsToEmployee(@RequestBody AssignSkillsToEmployeeCommand command) {
        return skillAssignmentsService.assignSkillToEmployee(command);
    }

    @GetMapping
    public EmployeeSkillsView getAssignedSkills(@RequestParam int employeeId) {
        return skillAssignmentsService.getSkillAssignments(employeeId);
    }

}
