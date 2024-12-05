package careerservice.skillassignments.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record LeveledSkill(long skillId, int level) {

    public LeveledSkill levelUp(int level) {
        if (level > this.level()) {
            return new LeveledSkill(this.skillId, level);
        }
        else {
            return this;
        }
    }

}
