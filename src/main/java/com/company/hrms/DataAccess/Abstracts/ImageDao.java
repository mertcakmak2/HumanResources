package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image, Integer> {

    Image findByUserId(int userId);

    @Modifying
    @Query("delete from Image u where u.userId = ?1")
    void deleteUsersByUserId(int userId);
}
