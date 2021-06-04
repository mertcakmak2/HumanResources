package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.ResumeService;
import com.company.hrms.Core.ProfilePictureService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.Core.Utilities.Result.SuccessResult;
import com.company.hrms.DataAccess.Abstracts.*;
import com.company.hrms.Entities.Concretes.ProfilePicture;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Dtos.ResumeSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeManager implements ResumeService {

    private final ResumeDao resumeDao;
    private final ProfilePictureDao profilePictureDao;
    @Qualifier(value = "CloudinaryAdapter")
    private final ProfilePictureService cloudinaryAdapter;

    @Override
    public DataResult<List<Resume>> findAllResumes() {
        return new SuccessDataResult<List<Resume>>(resumeDao.findAll(),"Tüm CV'ler listelendi.");
    }

    @Override
    public Result saveResume(ResumeSaveDto resumeSaveDto) {

        Resume resume = new Resume(
                resumeSaveDto.getJobSeeker(),
                resumeSaveDto.getGithub(),
                resumeSaveDto.getLinkedin());
        resumeDao.save(resume);
        return new SuccessResult("Cv başarıyla kaydedildi");
    }

    @Override
    public Result setResumeProfilPicture(MultipartFile picture, int resumeId) {
        String profilePicturePath = cloudinaryAdapter.saveProfilePicture(picture);
        ProfilePicture savedProfilePicture = profilePictureDao.save(new ProfilePicture(profilePicturePath));

        Resume resume = resumeDao.findById(resumeId).orElseThrow(() -> new EntityNotFoundException("Cv bulunamadı"));
        resume.setProfilePicture(savedProfilePicture);
        return new SuccessDataResult<Resume>(resumeDao.save(resume),"Profil fotoğrafı cvye kaydedildi.");
    }

}
