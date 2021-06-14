package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.WorkingConceptService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.WorkingConceptDao;
import com.company.hrms.Entities.Concretes.WorkingConcept;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingConceptManager implements WorkingConceptService {

    private final WorkingConceptDao workingConceptDao;

    @Override
    public DataResult<List<WorkingConcept>> findAllWorkingConcepts() {
        return new SuccessDataResult<List<WorkingConcept>>(workingConceptDao.findAll(),
                "Tüm Çalışma konseptleri listelendi");
    }
}
