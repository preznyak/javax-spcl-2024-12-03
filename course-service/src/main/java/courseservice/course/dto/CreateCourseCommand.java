package courseservice.course.dto;

public record CreateCourseCommand(
        String name,
        String description,
        String syllabus,
        int limit
) {

}
