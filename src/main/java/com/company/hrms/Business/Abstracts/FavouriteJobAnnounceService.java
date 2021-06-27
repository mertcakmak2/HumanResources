package com.company.hrms.Business.Abstracts;

import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Entities.Concretes.FavouriteJobAnnounce;

import java.util.List;

public interface FavouriteJobAnnounceService {

    DataResult<List<FavouriteJobAnnounce>> findAllFavouriteJobAnnounces();
    DataResult<List<FavouriteJobAnnounce>> findAllFavouriteJobAnnouncesByJobSeekerId(int jobSeekerId);
    DataResult<FavouriteJobAnnounce> saveFavouriteJobAnnounce(FavouriteJobAnnounce favouriteJobAnnounce);
    DataResult<FavouriteJobAnnounce> deleteFavouriteJobAnnounce(int id);

 }
