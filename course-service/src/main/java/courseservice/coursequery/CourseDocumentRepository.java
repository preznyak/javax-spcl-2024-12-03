package courseservice.coursequery;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseDocumentRepository extends MongoRepository<CourseDocument, String> {

    List<CourseDocument> findAllBy(TextCriteria criteria);
}
