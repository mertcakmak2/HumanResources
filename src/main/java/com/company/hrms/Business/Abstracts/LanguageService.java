package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Language;
import com.company.hrms.Entities.Dtos.Language.LanguageSaveDto;
import org.apache.commons.codec.language.bm.Lang;

import java.util.List;

public interface LanguageService {

    DataResult<List<Language>> findAllLanguageByResumeId(int resumeId);
    DataResult<Language> saveLanguage(LanguageSaveDto languageSaveDto);
    DataResult<Language> updateLanguage(Language language);
    DataResult<Language> deleteLanguage(int languageId);
}
