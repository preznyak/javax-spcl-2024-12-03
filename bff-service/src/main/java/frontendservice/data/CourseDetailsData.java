package frontendservice.data;

import frontendservice.employeesgateway.Employee;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetailsData {

    private String description;

    private String syllabus;

    private int limit;

    private List<EmployeeData> enrolledEmployees;

    private List<EmployeeData> completedEmployees;
}
