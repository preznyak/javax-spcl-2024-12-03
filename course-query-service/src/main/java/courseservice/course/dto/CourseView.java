package courseservice.course.dto;

public record CourseView(
        Long id,
        String name,
        int limit
) {

}
