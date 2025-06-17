package com.sain.securedocuments.repository;

import com.sain.securedocuments.entity.ConfirmationEntity;
import com.sain.securedocuments.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity,Long> {
    Optional<ConfirmationEntity> findByKey(String key);

    Optional<ConfirmationEntity> findByUserEntity(UserEntity userEntity);
}
