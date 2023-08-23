package pe.com.dasser.servicesimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import pe.com.dasser.entities.User;
import pe.com.dasser.repositories.IUserRepository;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository uR;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = uR.findByLogin(login);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("El usuario no existe", login));
        }

        GrantedAuthority role = new SimpleGrantedAuthority(user.getRole().getName());

        UserDetails uD = new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singletonList(role)
        );
        return uD;
    }
}
