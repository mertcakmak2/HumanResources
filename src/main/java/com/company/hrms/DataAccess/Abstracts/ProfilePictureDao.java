package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePictureDao extends JpaRepository<ProfilePicture, Integer> {
}
