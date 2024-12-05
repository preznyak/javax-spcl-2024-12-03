package frontendservice.service;

import frontendservice.coursegateway.CourseResource;
import frontendservice.coursegateway.CourseServiceClient;
import frontendservice.employeegateway.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoursesService {

    private CourseServiceClient courseServiceClient;

    private EmployeesService employeesService;

    private CourseMapper courseMapper;

    public List<CourseResource> findAllCourses() {
        return courseServiceClient.findAllCourses();
    }

    public CompositeCourseResource findCourseById(long id) {
        // Courses Service - kurzusok - employee id
        CourseResource course = courseServiceClient.findCourseById(id);

        // Employees Service - alkalmazottak - employee name
        List<EmployeeDto> employees = employeesService.listEmployees();

        return courseMapper.toComposite(course, employees);
    }
}
