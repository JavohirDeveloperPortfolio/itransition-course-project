package com.itransition.courseproject.dto.request;

import com.itransition.courseproject.enums.FieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDataDto {
    @Enumerated(EnumType.STRING)
    private FieldTypeEnum fieldType;
    private String fieldName;
    private String value;
}
