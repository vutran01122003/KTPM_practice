package iuh.fit.security_demo.bootstrap;

import iuh.fit.security_demo.entities.Role;
import iuh.fit.security_demo.repositories.RoleRepository;
import iuh.fit.security_demo.shared.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }

    private void loadRoles() {
        RoleEnum[] roles = {RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN};
        Map<RoleEnum, String> roleMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.SUPER_ADMIN, "Default super admin role",
                RoleEnum.ADMIN, "Default admin role"
        );

        Arrays.stream(roles).forEach(roleName -> {
            Role role = roleRepository.findRoleByName(roleName);

            if (role == null) {
                String description = roleMap.get(roleName);
                Role newRole = new Role();
                newRole.setName(roleName);
                newRole.setDescription(description);
                roleRepository.save(newRole);
            } else {
                log.info("Role with name {} already exists", roleName);
            }
        });
    }
}
