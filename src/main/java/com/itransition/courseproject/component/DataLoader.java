package com.itransition.courseproject.component;

import com.itransition.courseproject.entity.RoleEntity;
import com.itransition.courseproject.enums.FieldTypeEnum;
import com.itransition.courseproject.enums.RoleTypeEnum;
import com.itransition.courseproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode;

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            Set<RoleEntity> roleList = new HashSet<>();
            roleList.add(new RoleEntity(RoleTypeEnum.ROLE_USER));
            roleList.add(new RoleEntity(RoleTypeEnum.ROLE_ADMIN));
            roleRepository.saveAll(roleList);
        }
    }
}
