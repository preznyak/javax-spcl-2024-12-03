package frontendservice.controller;

import frontendservice.coursegateway.CourseResource;
import frontendservice.service.CompositeCourseResource;
import frontendservice.service.CoursesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class CoursesController {

    private CoursesService coursesService;

    @GetMapping("/courses")
    public ModelAndView findAllCourses() {
        List<CourseResource> courses = coursesService.findAllCourses();
        Map<String, Object> model = Map.of("courses", courses);
        return new ModelAndView("courses", model);
    }

    @GetMapping("/course")
    public ModelAndView findCourseById(@RequestParam long courseId) {
        CompositeCourseResource course = coursesService.findCourseById(courseId);
        Map<String, Object> model = Map.of("course", course);
        return new ModelAndView("course", model);
    }
}
