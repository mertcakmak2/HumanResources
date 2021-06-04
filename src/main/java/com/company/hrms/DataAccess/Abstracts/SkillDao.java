package com.company.hrms.DataAccess.Abstracts;

import com.company.hrms.Entities.Concretes.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillDao extends JpaRepository<Skill, Integer> {
}
