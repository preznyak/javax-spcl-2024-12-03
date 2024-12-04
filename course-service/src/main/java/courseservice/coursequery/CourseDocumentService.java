package courseservice.coursequery;

import courseservice.course.CourseHasBeenCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseDocumentService {

    private final CourseDocumentRepository courseDocumentRepository;

    public List<CourseDocument> findAllBy(String text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(text);
        return courseDocumentRepository.findAllBy(criteria);
    }

    @EventListener
    public void handleCourseCreated(CourseHasBeenCreatedEvent event) {
        CourseDocument document = new CourseDocument(event.id(), event.name(), event.description(),
                event.syllabus());
        courseDocumentRepository.save(document);
    }
}
