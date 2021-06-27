package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.LanguageService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.LanguageDao;
import com.company.hrms.Entities.Concretes.Language;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageManager implements LanguageService {

    private final LanguageDao languageDao;

    @Override
    public DataResult<Language> saveLanguage(Language language) {
        return new SuccessDataResult<Language>(languageDao.save(language),"Yabancı dil eklendi.");
    }

    @Override
    public DataResult<Language> updateLanguage(Language language) {
        Language existLanguage = findLanguageById(language.getId());

        existLanguage = language;
        return new SuccessDataResult<Language>(languageDao.save(existLanguage), "Yabancı dil güncellendi.");
    }

    public Language findLanguageById(int id){
        return languageDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Yabancı dil bulunamadı."));
    }

}
