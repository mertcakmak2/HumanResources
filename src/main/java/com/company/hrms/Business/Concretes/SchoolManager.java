package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.SchoolService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.ResumeDao;
import com.company.hrms.DataAccess.Abstracts.SchoolDao;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Concretes.School;
import com.company.hrms.Entities.Dtos.School.SchoolSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolManager implements SchoolService {

    private final SchoolDao schoolDao;
    private final ResumeDao resumeDao;

    @Override
    public DataResult<List<School>> findAllSchoolByResumeId(int resumeId) {
        return new SuccessDataResult<List<School>>(schoolDao.findByResume_IdAndIsActive(resumeId,true),
                "Özgeçmiş okulları listelendi.");
    }

    @Override
    public DataResult<School> saveSchool(SchoolSaveDto schoolSaveDto) {
        Resume resume = resumeDao.findById(schoolSaveDto.getResumeId())
                .orElseThrow(() -> new EntityNotFoundException("Özgeçmiş bulunamadı."));

        School school = new School(
                schoolSaveDto.getSchoolName(),
                schoolSaveDto.getDepartment(),
                schoolSaveDto.getBeginDate(),
                schoolSaveDto.getGraduationDate(),
                schoolSaveDto.isGraduate(),
                resume
        );
        return new SuccessDataResult<School>(schoolDao.save(school), "Okul kaydedildi.");
    }

    @Override
    public DataResult<School> updateSchool(School school) {
        School existSchool = findSchoolById(school.getId());

        existSchool = school;
        return new SuccessDataResult<School>(schoolDao.save(existSchool));
    }

    @Override
    public DataResult<School> deleteSchool(int schoolId) {
        School school = findSchoolById(schoolId);
        school.setActive(false);
        return new SuccessDataResult<School>(schoolDao.save(school),"Okul silindi.");
    }

    public School findSchoolById(int id ){
        return schoolDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Okul bulunamadı"));
    }
}
