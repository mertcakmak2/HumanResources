package com.company.hrms.Business.Concretes;

import com.company.hrms.Business.Abstracts.FavouriteJobAnnounceService;
import com.company.hrms.Core.Utilities.Result.DataResult;
import com.company.hrms.Core.Utilities.Result.SuccessDataResult;
import com.company.hrms.DataAccess.Abstracts.FavouriteJobAnnounceDao;
import com.company.hrms.Entities.Concretes.FavouriteJobAnnounce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteJobAnnounceManager implements FavouriteJobAnnounceService {

    private final FavouriteJobAnnounceDao favouriteJobAnnounceDao;

    @Override
    public DataResult<List<FavouriteJobAnnounce>> findAllFavouriteJobAnnounces() {
        return new SuccessDataResult<List<FavouriteJobAnnounce>>(favouriteJobAnnounceDao.findAll(),
                "Tüm favori iş ilanları listelendi.");
    }

    @Override
    public DataResult<List<FavouriteJobAnnounce>> findAllFavouriteJobAnnouncesByJobSeekerId(int jobSeekerId) {
        return new SuccessDataResult<List<FavouriteJobAnnounce>>(favouriteJobAnnounceDao.findByJobSeeker_Id(jobSeekerId),
                "İş Arayanın favori iş ilanları listelendi id= "+jobSeekerId);
    }

    @Override
    public DataResult<FavouriteJobAnnounce> saveFavouriteJobAnnounce(FavouriteJobAnnounce favouriteJobAnnounce) {
        return new SuccessDataResult<FavouriteJobAnnounce>(favouriteJobAnnounceDao.save(favouriteJobAnnounce),
                "İş ilanı favorilere eklendi");
    }

    @Override
    public DataResult<FavouriteJobAnnounce> deleteFavouriteJobAnnounce(int id) {
        FavouriteJobAnnounce existFavJobAnnounce = findFavouriteJobAnnounceById(id);
        existFavJobAnnounce.setActive(false);
        return new SuccessDataResult<FavouriteJobAnnounce>(favouriteJobAnnounceDao.save(existFavJobAnnounce),
                "Favori iş ilanı silindi.");
    }

    public FavouriteJobAnnounce findFavouriteJobAnnounceById(int id){
        return favouriteJobAnnounceDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Favori iş ilanı bulunamadı."));
    }
}
