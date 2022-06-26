package com.itransition.courseproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itransition.courseproject.dto.response.UserBasicResponseTo;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@EntityListeners(value = AuditingEntityListener.class)
public class UserEntity extends BaseEntity implements UserDetails {

    private String firstName;

    private String lastName;

    private String email;

//    @JsonIgnore
    private String password;

    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;

    @ManyToMany
    @JoinTable(
            name = "item_like",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName ="id"))
    Set<ItemEntity> likedItems;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public UserBasicResponseTo toBasic(){
        UserBasicResponseTo userBasicResponseTo = new UserBasicResponseTo();
        userBasicResponseTo.setId(this.getId());
        userBasicResponseTo.setFirstName(this.getFirstName());
        userBasicResponseTo.setLastName(this.getLastName());
        userBasicResponseTo.setEmail(this.getEmail());
        userBasicResponseTo.setRole(roles);
        return userBasicResponseTo;
    }
}
