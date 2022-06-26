package com.itransition.courseproject.dto.response;

import com.itransition.courseproject.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicResponseTo {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleEntity> role;
    private String token;
}
