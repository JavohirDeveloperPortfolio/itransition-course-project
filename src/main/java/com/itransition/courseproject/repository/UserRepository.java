package com.itransition.courseproject.repository;

import com.itransition.courseproject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("select u from UserEntity u where u.email = ?1 and u.enabled = true")
    Optional<UserEntity> findByEmailAndEnabledTrue(String email);

    @Query("select (count(u) > 0) from UserEntity u where u.email = ?1")
    boolean existsUserEntityByEmail(String email);
}
