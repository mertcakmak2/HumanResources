package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.Language;

public interface LanguageService {

    DataResult<Language> saveLanguage(Language language);
}