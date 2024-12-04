package careerservice.skill;

import java.util.List;

public interface SkillServicePort {
    List<Long> findSkillIdsIn(List<Long> skillIds);
}
