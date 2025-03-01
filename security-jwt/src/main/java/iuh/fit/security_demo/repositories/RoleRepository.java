package iuh.fit.security_demo.repositories;

import iuh.fit.security_demo.entities.Role;
import iuh.fit.security_demo.shared.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(RoleEnum name);
}
