package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.WorkingConcept;

import java.util.List;

public interface WorkingConceptService {

    DataResult<List<WorkingConcept>> findAllWorkingConcepts();
}
