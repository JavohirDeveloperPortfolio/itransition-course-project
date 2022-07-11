package com.itransition.courseproject.repository;

import com.itransition.courseproject.entity.ItemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
    @Query("select i from ItemEntity i where i.collection = ?1")
    Optional<ItemEntity> findAllByCollection(UUID id);
}
