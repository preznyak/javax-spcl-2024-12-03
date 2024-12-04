package courseservice.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResult {

    public enum Success {SUCCESSFUL, UNSUCCESSFUL}

    private Success success;

    public static EnrollmentResult successful() {
        return new EnrollmentResult(Success.SUCCESSFUL);
    }

    public static EnrollmentResult unsuccessful() {
        return new EnrollmentResult(Success.UNSUCCESSFUL);
    }
}
