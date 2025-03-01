package iuh.fit.security_demo.bootstrap;

import iuh.fit.security_demo.entities.Role;
import iuh.fit.security_demo.entities.User;
import iuh.fit.security_demo.repositories.RoleRepository;
import iuh.fit.security_demo.repositories.UserRepository;
import iuh.fit.security_demo.shared.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger log = LoggerFactory.getLogger(ApplicationStartup.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationStartup(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        this.loadSuperAdmin();
    }

    private void loadSuperAdmin() {
        Role role = roleRepository.findRoleByName(RoleEnum.SUPER_ADMIN);
        User user = userRepository.findUserByRole(role);

        if(user == null) {

            user = new User();
            user.setEmail("tranducvu234@admin.com");
            user.setUsername("super_admin");
            user.setRole(role);
            user.setPassword(passwordEncoder.encode("123456a"));

            userRepository.save(user);
        } else {
            log.info("Super admin already exists");
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
