package com.itransition.courseproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tags")
public class TagEntity extends BaseEntity{
    private String name;

    public TagEntity(String name) {
        super();
        this.name = name;
    }
}
