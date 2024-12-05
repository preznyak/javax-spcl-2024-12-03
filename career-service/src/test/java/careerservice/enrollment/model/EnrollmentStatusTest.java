package careerservice.enrollment.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentStatusTest {

    @Test
    void testCompleteStarted() {
        assertEquals(EnrollmentStatus.COMPLETED, EnrollmentStatus.STARTED.complete());
    }
}