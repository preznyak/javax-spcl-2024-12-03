package careerservice.enrollment.gateway;

public record EnrollResponse(long employeeId, long courseId, EnrollResult enrollResult) {

    public enum EnrollResult{OK, FULL}
}
