package com.sain.securedocuments.repository;

import com.sain.securedocuments.entity.CredintialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredintialsRepository extends JpaRepository<CredintialEntity,Long> {
    Optional<CredintialEntity> getCredintialByUserEntityId(Long userdd);
}
