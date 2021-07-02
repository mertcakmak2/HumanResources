package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.*;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Core.Utilities.Result.SuccessResult;
import com.company.hrms.DataAccess.Abstracts.*;
import com.company.hrms.Entities.Concretes.*;
import com.company.hrms.Entities.Dtos.Resume.ResumeSaveDto;
import com.company.hrms.Entities.Dtos.Resume.ResumeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeManager implements ResumeService {

    private final ResumeDao resumeDao;

    @Override
    public DataResult<Resume> findByJobSeekerId(int jobSeekerId) {
        return new SuccessDataResult<Resume>(resumeDao.findByJobSeeker_Id(jobSeekerId),
                "Kullanıcı özgeçmişi getirildi.");
    }

    @Override
    public DataResult<List<Resume>> findAllResumes() {
        return new SuccessDataResult<List<Resume>>(resumeDao.findAll(), "Tüm CV'ler listelendi.");
    }

    @Override
    public Result saveResume(ResumeSaveDto resumeSaveDto) {

        Resume resume = new Resume(
                resumeSaveDto.getJobSeeker(),
                resumeSaveDto.getGithub(),
                resumeSaveDto.getLinkedin(),
                resumeSaveDto.getCoverLetter()
        );

        return new SuccessDataResult<Resume>(resumeDao.save(resume), "Cv başarıyla kaydedildi.");
    }

    @Override
    public DataResult<Resume> updateResume(ResumeUpdateDto resumeUpdateDto)  {

       Resume existResume = resumeDao.findById(resumeUpdateDto.getId())
               .orElseThrow(() -> new EntityNotFoundException("Cv bulunamadı."));

       existResume.setGithub(resumeUpdateDto.getGithub());
       existResume.setLinkedin(resumeUpdateDto.getLinkedin());
       existResume.setCoverLetter(resumeUpdateDto.getCoverLetter());

       return new SuccessDataResult<Resume>(resumeDao.save(existResume), "Cv güncellendi.");
    }

}
