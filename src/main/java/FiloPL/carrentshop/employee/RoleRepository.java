package FiloPL.carrentshop.employee;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByAuthority(String authority);
}
