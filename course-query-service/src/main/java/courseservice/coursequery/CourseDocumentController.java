package courseservice.coursequery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course-documents")
@AllArgsConstructor
public class CourseDocumentController {

    private final CourseDocumentService courseDocumentService;

    @GetMapping
    public List<CourseDocument> findAllBy(@RequestParam String text) {
        return courseDocumentService.findAllBy(text);
    }
}
