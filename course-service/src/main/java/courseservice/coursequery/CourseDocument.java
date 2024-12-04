package courseservice.coursequery;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CourseDocument {

    @Id
    private String id;

    private long courseId;

    @TextIndexed
    private String name;

    @TextIndexed
    private String description;

    @TextIndexed
    private String syllabus;

    public CourseDocument(long courseId, String name, String description, String syllabus) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.syllabus = syllabus;
    }
}
