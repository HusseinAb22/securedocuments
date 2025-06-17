package com.sain.securedocuments.repository;

import com.sain.securedocuments.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<CredentialEntity,Long> {
    Optional<CredentialEntity> getCredintialByUserEntityId(Long userdd);
}
