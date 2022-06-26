package com.itransition.courseproject.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "tags")
public class TagEntity extends BaseEntity{
    private String name;
}
