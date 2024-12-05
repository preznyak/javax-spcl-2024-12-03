package courseservice.course.model;

import courseservice.course.dto.CreateCourseCommand;
import courseservice.course.dto.EnrollCommand;
import courseservice.course.dto.EnrollmentResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    @Lob
    private String syllabus;

    @Column(name = "attendee_limit")
    private int limit;

    @ElementCollection
    private List<Long> enrolledEmployees;

    @ElementCollection
    private List<Long> completedEmployees;

    private Course(String name, String description, String syllabus, int limit) {
        this.name = name;
        this.description = description;
        this.syllabus = syllabus;
        this.limit = limit;
    }

    public static Course announceCourse(CreateCourseCommand command) {
        return new Course(command.name(), command.description(), command.syllabus(), command.limit());
    }

    public EnrollmentResult enroll(EnrollCommand command) {
        if (alreadyEnrolled(command)) {
            return EnrollmentResult.successful();
        }
        if (isFull()) {
            return EnrollmentResult.unsuccessful();
        }
        else {
            enrolledEmployees.add(command.getEmployeeId());
            return EnrollmentResult.successful();
        }
    }

    private boolean alreadyEnrolled(EnrollCommand command) {
        return enrolledEmployees.contains(command.getEmployeeId());
    }

    private boolean isFull() {
        return enrolledEmployees.size() >= limit;
    }

}
