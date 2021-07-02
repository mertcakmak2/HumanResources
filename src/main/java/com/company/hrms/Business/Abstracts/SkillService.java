package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Skill;
import com.company.hrms.Entities.Dtos.Skill.SkillSaveDto;

import java.util.List;

public interface SkillService {

    DataResult<List<Skill>> findAllSkillByResumeId(int resumeId);
    DataResult<Skill> saveSkill(SkillSaveDto skillSaveDto);
    DataResult<Skill> updateSkill(Skill skill);
    DataResult<Skill> deleteSkill(int skillId);
}
