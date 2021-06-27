package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.FavouriteJobAnnounce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteJobAnnounceDao extends JpaRepository<FavouriteJobAnnounce, Integer> {

    List<FavouriteJobAnnounce> findByJobSeeker_Id(int jobSeekerId);
    List<FavouriteJobAnnounce> findByJobAnnounce_City_IdAndJobAnnounce_JobType_Id(int cityId, int jobTypeId);
}
