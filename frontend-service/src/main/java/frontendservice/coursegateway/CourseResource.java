package frontendservice.coursegateway;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CourseResource {

    private Long id;

    private String name;

    private String description;

    private String syllabus;

    private int limit;

    public CourseResource(Long id, String name, String description, String syllabus, int limit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.syllabus = syllabus;
        this.limit = limit;
    }

    private List<Long> enrolledEmployees;

    private List<Long> completedEmployees;
}
