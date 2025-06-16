package com.hospitalAuth.AuthHMS.Repository;

import com.hospitalAuth.AuthHMS.Entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Optional<Privilege> findByName(String name);
}
