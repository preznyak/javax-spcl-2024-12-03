package careerservice.skill.service;

import careerservice.skill.model.Skill;
import careerservice.skill.view.SkillView;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillMapper {
    SkillView toView(Skill skill);

    List<SkillView> toViews(List<Skill> skills);
}
