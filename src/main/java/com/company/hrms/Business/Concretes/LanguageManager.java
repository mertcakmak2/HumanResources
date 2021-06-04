package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.LanguageService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.LanguageDao;
import com.company.hrms.Entities.Concretes.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageManager implements LanguageService {

    private final LanguageDao languageDao;

    @Override
    public DataResult<Language> saveLanguage(Language language) {
        return new SuccessDataResult<Language>(languageDao.save(language),"Yabancı dil eklendi.");
    }

}
