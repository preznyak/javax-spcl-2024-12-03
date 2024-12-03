package careerservice.enrollment.service;

import careerservice.enrollment.view.EnrollmentView;
import careerservice.enrollment.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface EnrollmentMapper {

    EnrollmentView toView(Enrollment enrollment);

    List<EnrollmentView> toViews(List<Enrollment> enrollments);
}
