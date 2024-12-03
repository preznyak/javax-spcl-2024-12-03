package careerservice.skillassignments.service;

import careerservice.skillassignments.model.SkillAssignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SkillAssignmentsRepository extends JpaRepository<SkillAssignments, Long> {

    @Query("select s from SkillAssignments s join fetch s.leveledSkills where s.employeeId = :employeeId")
    Optional<SkillAssignments> findByEmployeeId(long employeeId);
}
