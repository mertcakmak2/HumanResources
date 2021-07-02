package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillDao extends JpaRepository<Skill, Integer> {

    List<Skill> findByResume_IdAndIsActive(int resumeId, boolean active);
}
