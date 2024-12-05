package frontendservice.service;

import frontendservice.coursegateway.CourseResource;
import frontendservice.employeegateway.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class CourseMapperDecorator implements CourseMapper {

    @Autowired
    @Qualifier("delegate")
    private CourseMapper delegate;

    @Override
    public CompositeCourseResource toComposite(CourseResource course, List<EmployeeDto> employees) {
        var composite = delegate.toComposite(course, employees);

        composite.setEnrolledEmployees(
                employees.stream().filter(e -> Optional.ofNullable(course.getEnrolledEmployees()).orElse(List.of()).contains(e.getId())).toList()
        );
        composite.setCompletedEmployees(
                employees.stream().filter(e -> Optional.ofNullable(course.getCompletedEmployees()).orElse(List.of()).contains(e.getId())).toList()
        );

        return composite;
    }
}
