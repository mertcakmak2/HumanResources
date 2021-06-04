package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Skill;

public interface SkillService {

    DataResult<Skill> saveSkill(Skill skill);
}
