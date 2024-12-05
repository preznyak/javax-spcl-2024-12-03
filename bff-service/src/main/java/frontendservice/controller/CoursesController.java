package frontendservice.controller;

import frontendservice.data.CourseData;
import frontendservice.data.CourseDetailsData;
import frontendservice.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CoursesController {

    private final CourseService courseService;

    @QueryMapping
    public List<CourseData> courses() {
        return courseService.findAllCourses();
    }

    @SchemaMapping
    public CourseDetailsData courseDetails(CourseData courseData) {
        return courseService.findCourseDetailsById(courseData.getId());
    }
}
