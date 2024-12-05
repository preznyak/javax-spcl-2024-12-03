package frontendservice.data;

import lombok.Data;

@Data
public class CourseData {

    private Long id;

    private String name;

    private int limit;

    private CourseDetailsData courseDetails;
}
