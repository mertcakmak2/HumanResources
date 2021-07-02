package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Language;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageDao extends JpaRepository<Language, Integer> {

    List<Language> findByResume_IdAndIsActive(int resumeId, boolean active);
}
