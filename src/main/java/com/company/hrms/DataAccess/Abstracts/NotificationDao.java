package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer> {

    @Query("from Notification where to.id=:userId")
    List<Notification> findByToId(int userId, Pageable pageable);

    @Query("select count(n) from Notification n where n.to.id=:userId")
    int findCountByToId(int userId);

}
