package frontendservice.service;

import frontendservice.coursegateway.CourseDetails;
import frontendservice.data.CourseDetailsData;
import frontendservice.employeesgateway.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CourseMapperDecorator implements CourseMapper {

    @Autowired
    @Qualifier("delegate")
    private CourseMapper delegate;

    @Override
    public CourseDetailsData toCourseDetailsData(CourseDetails course, List<Employee> employees) {
        var composite = delegate.toCourseDetailsData(course, employees);

        composite.setEnrolledEmployees(
                delegate.toEmployeeData(employees.stream().filter(
                        e -> Optional.ofNullable(course.getEnrolledEmployees()).orElse(List.of()).contains(e.getId())).toList())
        );
        composite.setCompletedEmployees(
                delegate.toEmployeeData(employees.stream().filter(
                        e -> Optional.ofNullable(course.getCompletedEmployees()).orElse(List.of()).contains(e.getId())).toList())
        );

        return composite;
    }
}
