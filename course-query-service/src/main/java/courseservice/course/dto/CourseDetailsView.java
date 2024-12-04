package courseservice.course.dto;


import java.util.List;

public record CourseDetailsView(
        Long id,
        String name,
        String description,
        String syllabus,
        int limit,
        List<Long> enrolledEmployees
) {

}
