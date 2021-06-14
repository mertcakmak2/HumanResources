package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {
}
