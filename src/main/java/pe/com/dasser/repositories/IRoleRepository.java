package pe.com.dasser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.dasser.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
}
