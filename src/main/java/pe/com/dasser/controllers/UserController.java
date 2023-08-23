package pe.com.dasser.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.com.dasser.dtos.UserDTO;
import pe.com.dasser.entities.User;
import pe.com.dasser.repositories.IUserRepository;
import pe.com.dasser.services.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService uS;
    @Autowired
    private IUserRepository uR;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserDTO> list() {
        return uS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, UserDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void create(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        User u = m.map(dto, User.class);
        uS.create(u);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public void update(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        User u = m.map(dto, User.class);
        uS.create(u);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public UserDTO listId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        UserDTO dto = m.map(uS.listId(id), UserDTO.class);
        return dto;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = uR.findByLogin(login);
        if (user == null) {
            throw new RuntimeException("No se ha encontrado el usuario: " + login);
        }

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return ResponseEntity.ok(userDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable int id, @RequestBody String newStatus) {
        try {
            uS.updateStatus(id, newStatus);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado del usuario");
        }
    }

}
