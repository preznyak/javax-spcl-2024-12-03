package careerservice.skillassignments.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SkillAssignmentsTest {

    @Test
    void learn() {
        SkillAssignments assignments = SkillAssignments.hire(1L, List.of(
                new LeveledSkill(1, 3),
                new LeveledSkill(2, 4)));

        assignments.learn(List.of(new LeveledSkill(1, 4)));

        Assertions.assertThat(assignments.getLeveledSkills()).containsExactlyInAnyOrder(
                new LeveledSkill(1, 4),
                new LeveledSkill(2, 4)
        );
    }
}