package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Skill;

import java.util.List;

public interface SkillService {

    DataResult<Skill> saveSkill(Skill skill);
    DataResult<Skill> updateSkill(Skill skill);
}
