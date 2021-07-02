package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.LanguageService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.LanguageDao;
import com.company.hrms.DataAccess.Abstracts.ResumeDao;
import com.company.hrms.Entities.Concretes.Language;
import com.company.hrms.Entities.Concretes.Resume;
import com.company.hrms.Entities.Dtos.Language.LanguageSaveDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageManager implements LanguageService {

    private final LanguageDao languageDao;
    private final ResumeDao resumeDao;

    @Override
    public DataResult<List<Language>> findAllLanguageByResumeId(int resumeId) {
        return new SuccessDataResult<List<Language>>(
                languageDao.findByResume_IdAndIsActive(resumeId, true), "Özgeçmiş dilleri listelendi.");
    }

    @Override
    public DataResult<Language> saveLanguage(LanguageSaveDto languageSaveDto) {
        Resume resume = resumeDao.findById(languageSaveDto.getResumeId())
                .orElseThrow(() -> new EntityNotFoundException("Özgeçmiş bulunamadı"));

        Language language = new Language(languageSaveDto.getLanguageName(), languageSaveDto.getLanguageLevel(), resume);
        return new SuccessDataResult<Language>(languageDao.save(language),"Yabancı dil eklendi.");
    }

    @Override
    public DataResult<Language> updateLanguage(Language language) {
        Language existLanguage = findLanguageById(language.getId());

        existLanguage = language;
        return new SuccessDataResult<Language>(languageDao.save(existLanguage), "Yabancı dil güncellendi.");
    }

    @Override
    public DataResult<Language> deleteLanguage(int languageId) {
        Language language = findLanguageById(languageId);
        language.setActive(false);
        return new SuccessDataResult<Language>(languageDao.save(language),"Yabancı dil silindi.");
    }

    public Language findLanguageById(int id){
        return languageDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Yabancı dil bulunamadı."));
    }

}
