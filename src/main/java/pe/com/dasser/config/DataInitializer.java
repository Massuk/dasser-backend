package pe.com.dasser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.dasser.entities.Role;
import pe.com.dasser.repositories.IRoleRepository;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final IRoleRepository rR;

    @Autowired
    public DataInitializer(IRoleRepository rR) {
        this.rR = rR;
    }

    @PostConstruct
    public void initializeData() {
        if (rR.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            Role userRole = new Role();
            userRole.setName("USER");

            rR.save(adminRole);
            rR.save(userRole);
        }
    }
}

