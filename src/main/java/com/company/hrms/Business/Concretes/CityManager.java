package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.CityService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.CityDao;
import com.company.hrms.Entities.Concretes.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityManager implements CityService {

    private final CityDao cityDao;

    @Override
    public DataResult<List<City>> findAllCities() {
        return new SuccessDataResult<List<City>>(cityDao.findAll(),
                "Tüm Şehirler listelendi.");
    }
}
