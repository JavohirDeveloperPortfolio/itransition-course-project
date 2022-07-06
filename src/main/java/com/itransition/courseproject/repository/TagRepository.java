package com.itransition.courseproject.repository;

import com.itransition.courseproject.dto.request.TagDto;
import com.itransition.courseproject.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {
    @Query("select t from TagEntity t where t.name = ?1")
    TagEntity findByName(String tag);
}
