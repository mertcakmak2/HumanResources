package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageDao extends JpaRepository<Language, Integer> {
}
