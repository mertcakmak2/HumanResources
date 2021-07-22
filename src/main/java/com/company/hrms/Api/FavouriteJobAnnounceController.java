package com.company.hrms.Api;

import com.company.hrms.Business.Abstracts.FavouriteJobAnnounceService;
import com.company.hrms.Core.ExceptionHandler.ValidationExceptionHandler;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.ErrorDataResult;
import com.company.hrms.Core.Utilities.Result.ErrorResult;
import com.company.hrms.Core.Utilities.Result.Result;
import com.company.hrms.Entities.Concretes.FavouriteJobAnnounce;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favourite-job-anounce")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class FavouriteJobAnnounceController {

    private final FavouriteJobAnnounceService favouriteJobAnnounceService;

    @GetMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<FavouriteJobAnnounce>> findAllFavouriteJobAnnounces(){
        return favouriteJobAnnounceService.findAllFavouriteJobAnnounces();
    }

    @GetMapping(value = "/{jobSeekerId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<List<FavouriteJobAnnounce>> findAllFavouriteJobAnnouncesByJobSeekerId(@PathVariable int jobSeekerId){
        return favouriteJobAnnounceService.findAllFavouriteJobAnnouncesByJobSeekerId(jobSeekerId);
    }

    @PostMapping(value = "")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<FavouriteJobAnnounce> saveFavouriteJobAnnounce(@RequestBody FavouriteJobAnnounce favouriteJobAnnounce){
        return favouriteJobAnnounceService.saveFavouriteJobAnnounce(favouriteJobAnnounce);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public DataResult<FavouriteJobAnnounce> deleteFavouriteJobAnnounce(@PathVariable int id){
        return favouriteJobAnnounceService.deleteFavouriteJobAnnounce(id);
    }

    @ExceptionHandler(value = {
            EntityNotFoundException.class,
    })
    public Result handleException(Exception e, HttpServletRequest httpServletRequest) {
        return new ErrorResult("Exception Message Found: "+e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        ValidationExceptionHandler validationExceptionHandler = new ValidationExceptionHandler();
        return validationExceptionHandler.getValidateExceptions(exceptions);
    }
}
