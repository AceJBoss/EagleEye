package com.jboss.eagleeye.repository;

import com.jboss.eagleeye.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
    User findByEmail(String email);
}
