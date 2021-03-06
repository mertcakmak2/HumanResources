package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.CityService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.City;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<City>> findAllCities(){
        return cityService.findAllCities();
    }

}
