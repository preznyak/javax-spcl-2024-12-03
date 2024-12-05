package frontendservice.service;

import frontendservice.employeegateway.EmployeeDto;
import lombok.Data;

import java.util.List;

@Data
public class CompositeCourseResource {

    private Long id;

    private String name;

    private String description;

    private String syllabus;

    private int limit;

    private List<EmployeeDto> enrolledEmployees;

    private List<EmployeeDto> completedEmployees;
}
