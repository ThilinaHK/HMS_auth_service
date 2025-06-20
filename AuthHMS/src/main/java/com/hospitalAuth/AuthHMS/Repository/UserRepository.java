package com.hospitalAuth.AuthHMS.Repository;

import com.hospitalAuth.AuthHMS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String u);
}
