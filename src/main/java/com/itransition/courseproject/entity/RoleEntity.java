package com.itransition.courseproject.entity;

import com.itransition.courseproject.enums.RoleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum roleType;

    public RoleEntity(RoleTypeEnum roleType){
        this.roleType = roleType;
    }

    @Override
    public String getAuthority() {
        return roleType.name();
    }

    @Override
    public String toString(){
        return roleType.name();
    }
}
