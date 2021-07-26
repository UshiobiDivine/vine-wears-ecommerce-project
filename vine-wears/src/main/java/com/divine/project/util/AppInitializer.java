package com.divine.project.util;
import com.divine.project.model.user.Role;
import com.divine.project.model.user.RoleEnum;
import com.divine.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class AppInitializer implements ApplicationRunner {
    private final RoleRepository roleRepository;

    @Autowired
    public AppInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Role role;
        if (roleRepository.findByName(RoleEnum.ROLE_ADMIN).isEmpty()) {
            role = new Role();
            role.setName(RoleEnum.ROLE_ADMIN);
            roleRepository.save(role);
        }
        if (roleRepository.findByName(RoleEnum.ROLE_USER).isEmpty()) {
            role = new Role();
            role.setName(RoleEnum.ROLE_USER);
            roleRepository.save(role);
        }
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

}