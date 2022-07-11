package com.itransition.courseproject.repository;

import com.itransition.courseproject.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CollectionRepository extends JpaRepository<CollectionEntity, UUID> {
    @Query("select c from CollectionEntity c where c.createdBy = ?1")
    List<CollectionEntity> findByCreatedById(String email);
}
