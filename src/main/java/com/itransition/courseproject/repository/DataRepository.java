package com.itransition.courseproject.repository;

import com.itransition.courseproject.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DataRepository extends JpaRepository<DataEntity, UUID> {
}
