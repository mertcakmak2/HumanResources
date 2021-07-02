package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.ResumeService;
import com.company.hrms.Business.Abstracts.SkillService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.ResumeDao;
import com.company.hrms.DataAccess.Abstracts.SkillDao;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Concretes.Skill;
import com.company.hrms.Entities.Dtos.Skill.SkillSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillManager implements SkillService {

    private final SkillDao skillDao;
    private final ResumeDao resumeDao;

    @Override
    public DataResult<List<Skill>> findAllSkillByResumeId(int resumeId) {
        return new SuccessDataResult<List<Skill>>(skillDao.findByResume_IdAndIsActive(resumeId, true),
                "İş arayanın yetenekleri listelendi.");
    }

    @Override
    public DataResult<Skill> saveSkill(SkillSaveDto skillSaveDto) {
        Resume resume = resumeDao.findById(skillSaveDto.getResumeId())
                .orElseThrow(() -> new EntityNotFoundException("Özgeçmiş bulunamadı"));

        Skill skill = new Skill(skillSaveDto.getSkillName(), resume);
        return new SuccessDataResult<Skill>(skillDao.save(skill),"Yetenek kaydedildi.");
    }

    @Override
    public DataResult<Skill> updateSkill(Skill skill) {
        Skill existSkill = findSkillById(skill.getId());

        existSkill = skill;
        return new SuccessDataResult<Skill>(skillDao.save(existSkill), "Yetenek güncellendi.");
    }

    @Override
    public DataResult<Skill> deleteSkill(int skillId) {
        Skill skill = findSkillById(skillId);
        skill.setActive(false);
        return new SuccessDataResult<Skill>(skillDao.save(skill),"Yetenek silindi");
    }

    public Skill findSkillById(int id){
        return skillDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Yetenek bulunamadı."));
    }
}
