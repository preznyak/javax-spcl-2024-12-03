package careerservice.skill.service;

import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.model.Skill;
import careerservice.skill.view.SkillView;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillService {

    private SkillRepository skillRepository;

    private SkillMapper skillMapper;

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
//        List<AssignedSkill> skills = assignedSkillRepository.findBySkillId(id);
//        assignedSkillRepository.deleteAll(skills);
        skillRepository.deleteById(id);
    }

}
