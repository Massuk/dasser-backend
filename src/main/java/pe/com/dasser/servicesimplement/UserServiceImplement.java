package pe.com.dasser.servicesimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.dasser.entities.Role;
import pe.com.dasser.entities.User;
import pe.com.dasser.repositories.IRoleRepository;
import pe.com.dasser.repositories.IUserRepository;
import pe.com.dasser.services.IUserService;

import java.util.List;

@Service
public class UserServiceImplement implements IUserService {
    @Autowired
    private IUserRepository uR;
    @Autowired
    private IRoleRepository rR;
    @Override
    public List<User> list() {
        return uR.findAll();
    }

    @Override
    public void create(User newUser) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(newUser.getPassword());
        User user = uR.findByLogin(newUser.getLogin());

        if (user == null) {
            Role role = rR.findById(2)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            newUser.setRole(role);
            newUser.setPassword(password);
        } else {
            newUser.setPassword(password);
        }
        uR.save(newUser);
    }

    @Override
    public User listId(int id) {
        return uR.findById(id).orElse(new User());
    }

    @Override
    public void updateStatus(int id, String newStatus) {
        User user = uR.findById(id).orElse(null);
        if (user != null) {
            user.setStatus(newStatus);
            uR.save(user);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

}
