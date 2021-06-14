package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.City;

import java.util.List;

public interface CityService {

    DataResult<List<City>> findAllCities();
}
