package iuh.fit.security_demo.repositories;

import iuh.fit.security_demo.entities.Role;
import iuh.fit.security_demo.entities.User;
import iuh.fit.security_demo.shared.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);
    User findUserByRole(Role name);
}
