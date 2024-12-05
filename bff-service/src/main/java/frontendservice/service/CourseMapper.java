package frontendservice.service;

import frontendservice.coursegateway.Course;
import frontendservice.coursegateway.CourseDetails;
import frontendservice.data.CourseData;
import frontendservice.data.CourseDetailsData;
import frontendservice.data.EmployeeData;
import frontendservice.employeesgateway.Employee;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(CourseMapperDecorator.class)
public interface CourseMapper {
    List<CourseData> toCourseData(List<Course> allCourses);

    @Mapping(target = "enrolledEmployees", ignore = true)
    @Mapping(target = "completedEmployees", ignore = true)
    CourseDetailsData toCourseDetailsData(CourseDetails course, List<Employee> employees);

    List<EmployeeData> toEmployeeData(List<Employee> list);
}
