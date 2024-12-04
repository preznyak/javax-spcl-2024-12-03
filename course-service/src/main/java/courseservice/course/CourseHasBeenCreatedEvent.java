package courseservice.course;

public record CourseHasBeenCreatedEvent(
        Long id,
        String name,
        String description,
        String syllabus
) {


}
