package careerservice.enrollment.model;

public enum EnrollmentStatus {

    STARTED {
        @Override
        public EnrollmentStatus complete() {
            return COMPLETED;
        }

        @Override
        public EnrollmentStatus failedLimitReached() {
            return FAILED_LIMIT_REACHED;
        }
    }, COMPLETED, FAILED_LIMIT_REACHED;

    public EnrollmentStatus complete() {
        throw new IllegalStateException("Cannot complete Enrollment Status");
    }

    public EnrollmentStatus failedLimitReached() {
        throw new IllegalStateException("Cannot fail Enrollment Status");
    }
}
