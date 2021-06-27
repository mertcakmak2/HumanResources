package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Language;
import org.apache.commons.codec.language.bm.Lang;

import java.util.List;

public interface LanguageService {

    DataResult<Language> saveLanguage(Language language);
    DataResult<Language> updateLanguage(Language language);
}
