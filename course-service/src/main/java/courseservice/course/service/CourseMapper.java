package courseservice.course.service;

import courseservice.course.CourseHasBeenCreatedEvent;
import courseservice.course.dto.CourseDetailsView;
import courseservice.course.dto.CourseView;
import courseservice.course.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    CourseView toView(Course course);

    CourseDetailsView toDetailsView(Course course);

    CourseHasBeenCreatedEvent toEvent(Course course);

}
