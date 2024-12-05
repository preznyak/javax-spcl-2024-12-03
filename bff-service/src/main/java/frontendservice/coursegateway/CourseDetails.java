package frontendservice.coursegateway;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetails {

    private Long id;

    private String name;

    private String description;

    private String syllabus;

    private int limit;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    List<Long> enrolledEmployees;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    List<Long> completedEmployees;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    public void setEnrolledEmployees(List<Long> enrolledEmployees) {
        this.enrolledEmployees = enrolledEmployees;
    }

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    public void setCompletedEmployees(List<Long> completedEmployees) {
        this.completedEmployees = completedEmployees;
    }
}
