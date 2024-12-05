package frontendservice.service;

import frontendservice.coursegateway.CourseResource;
import frontendservice.employeegateway.EmployeeDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(CourseMapperDecorator.class)
public interface CourseMapper {

    @Mapping(target = "enrolledEmployees", ignore = true)
    @Mapping(target = "completedEmployees", ignore = true)
    CompositeCourseResource toComposite(CourseResource course, List<EmployeeDto> employees);

}
