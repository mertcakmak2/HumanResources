package com.company.hrms.Core.DataAccess;

import com.company.hrms.Core.Entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
