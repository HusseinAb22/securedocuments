package com.sain.securedocuments.repository;

import com.sain.securedocuments.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity,Long> {

    Optional<RolesEntity> findByNameIgnoreCase(String name);
}
