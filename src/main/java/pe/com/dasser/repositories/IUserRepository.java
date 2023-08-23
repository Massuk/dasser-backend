package pe.com.dasser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.dasser.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT COUNT(u.login) FROM User u WHERE u.login =:login")
    public int validateUser(@Param("login") String login);
    public User findByLogin(String login);
}
