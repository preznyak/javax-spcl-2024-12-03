package careerservice.skillassignments.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkillAssignments {

    @Id
    @GeneratedValue
    private Long id;

    private long employeeId;

    @ElementCollection
    private List<LeveledSkill> leveledSkills;

    private SkillAssignments(long employeeId, List<LeveledSkill> leveledSkills) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("employeeId must be greater than 0");
        }
        if (leveledSkills == null || leveledSkills.isEmpty()) {
            throw new IllegalArgumentException("Can not hire employee without skills");
        }
        this.employeeId = employeeId;
        this.leveledSkills = leveledSkills;
    }

    public static SkillAssignments hire(long employeeId, List<LeveledSkill> leveledSkills) {
        return new SkillAssignments(employeeId, new ArrayList<>(leveledSkills));
    }

    public void learn(List<LeveledSkill> learnedSkills) {
        Map<Long, LeveledSkill> existingSkillsById = leveledSkills.stream().collect(Collectors.toMap(
                LeveledSkill::skillId,
                Function.identity()
        ));

        for (LeveledSkill learningSkill : learnedSkills) {
            LeveledSkill existingSkill = existingSkillsById.get(learningSkill.skillId());
            if (existingSkill == null) {
                leveledSkills.add(learningSkill);
            }
            else {
                leveledSkills.remove(existingSkill);
                leveledSkills.add(existingSkill.levelUp(learningSkill.level()));
            }
        }
    }

    public void removeSkill(int skillId) {
        leveledSkills.removeIf(skill -> skill.skillId() == skillId);
    }
}
