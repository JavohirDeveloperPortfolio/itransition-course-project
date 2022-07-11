package com.itransition.courseproject.component;

import com.itransition.courseproject.entity.RoleEntity;
import com.itransition.courseproject.enums.RoleTypeEnum;
import com.itransition.courseproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode;

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args){
        if (mode.equals("always")) {
            roleRepository.saveAll(new HashSet<RoleEntity>(){{
                add(new RoleEntity(RoleTypeEnum.ROLE_USER));
            }});
            roleRepository.saveAll(new HashSet<RoleEntity>(){{
                add(new RoleEntity(RoleTypeEnum.ROLE_ADMIN));
            }});
        }
    }
}
