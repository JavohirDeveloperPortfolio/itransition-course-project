package com.itransition.courseproject.entity;

import com.itransition.courseproject.enums.FieldTypeEnum;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "field")
@EntityListeners(value = AuditingEntityListener.class)
public class FieldEntity extends BaseEntity{
    private String name;//Author

    @Enumerated(EnumType.STRING)
    private FieldTypeEnum fieldType;//String
}
