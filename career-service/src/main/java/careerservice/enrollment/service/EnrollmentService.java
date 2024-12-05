package careerservice.enrollment.service;

import careerservice.enrollment.model.EnrollmentStatus;
import careerservice.enrollment.view.EnrollmentView;
import careerservice.enrollment.model.EnrollCommand;
import careerservice.enrollment.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    private EnrollmentMapper enrollmentMapper;

    public List<EnrollmentView> findAllByEmployee(long employeeId) {
        return enrollmentMapper.toViews(enrollmentRepository.findAllByEmployeeId(employeeId));
    }

    @Transactional
    public EnrollmentView enrollToCourse(EnrollCommand command) {
        Optional<Enrollment> mayBeEnrolled = enrollmentRepository.findByCourseIdAndEmployeeId(command.courseId(), command.employeeId());
        if (mayBeEnrolled.isPresent()) {
            return enrollmentMapper.toView(mayBeEnrolled.get());
        }
        else {
            Enrollment enrollment = Enrollment.enrollToCourse(command);
            enrollmentRepository.save(enrollment);
            return enrollmentMapper.toView(enrollment);
        }
    }

    @Transactional
    public void complete(long employeeId, long courseId) {
        Enrollment enrollment = enrollmentRepository.findByCourseIdAndEmployeeId(courseId, employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found employee %d course %d".formatted(
                        employeeId, courseId
                )));
//        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        // enrollment.setStatus(enrollment.getStatus().complete());
        enrollment.complete();
    }

    @Transactional
    public void fail(long employeeId, long courseId) {
        Enrollment enrollment = enrollmentRepository.findByCourseIdAndEmployeeId(courseId, employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found employee %d course %d".formatted(
                        employeeId, courseId
                )));
        enrollment.fail();
    }
}
