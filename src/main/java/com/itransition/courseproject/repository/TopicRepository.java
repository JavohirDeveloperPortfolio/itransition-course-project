package com.itransition.courseproject.repository;

import com.itransition.courseproject.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, UUID> {
    Boolean existsByName(String name);

    @Query(value = "select t from TopicEntity t where t.id = ?1")
    Optional<TopicEntity> findById(UUID id);

    @Query(value = "select t from TopicEntity t where t.name like %:name%")
    List<TopicEntity> findByName(@Param("name") String name);
}
