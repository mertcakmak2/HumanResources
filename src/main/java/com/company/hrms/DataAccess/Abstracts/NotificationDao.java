package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Integer> {

    // Okunmamış bildirimleri getirir.
    @Query("from Notification where to.id=:id and isSeen=false")
    List<Notification> findByToIdAndSeen(int id);
}
