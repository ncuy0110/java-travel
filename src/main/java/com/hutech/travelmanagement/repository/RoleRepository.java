package com.hutech.travelmanagement.repository;

import com.hutech.travelmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneById(Long i);
}
