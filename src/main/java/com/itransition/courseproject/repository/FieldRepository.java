package com.itransition.courseproject.repository;

import com.itransition.courseproject.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, UUID> {
}
