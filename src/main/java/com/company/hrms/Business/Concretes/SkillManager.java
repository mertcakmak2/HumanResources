package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.SkillService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.SkillDao;
import com.company.hrms.Entities.Concretes.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillManager implements SkillService {

    private final SkillDao skillDao;

    @Override
    public DataResult<Skill> saveSkill(Skill skill) {
        return new SuccessDataResult<Skill>(skillDao.save(skill),"Yetenek kaydedildi.");
    }
}
