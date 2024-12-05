package careerservice.skill.service;

import careerservice.skill.SkillHasBeenDeleted;
import careerservice.skill.SkillServicePort;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.model.Skill;
import careerservice.skill.view.SkillView;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillService implements SkillServicePort {

    private SkillRepository skillRepository;

    private SkillMapper skillMapper;

    private ApplicationEventPublisher eventPublisher;

    public SkillView create(CreateSkillCommand command) {
        Skill skill = new Skill(command.name());
        skillRepository.save(skill);
        return skillMapper.toView(skill);
    }

    public List<SkillView> listSkills() {
        return skillMapper.toViews(skillRepository.findAll());
    }

    @Transactional
    public void deleteSkillById(long id) {
        skillRepository.deleteById(id);
        eventPublisher.publishEvent(new SkillHasBeenDeleted(id));
    }

    @Override
    public List<Long> findSkillIdsIn(List<Long> skillIds) {
        return skillRepository.findSkillIdsIn(skillIds);
    }
}
