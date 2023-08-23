package pe.com.dasser.services;

import pe.com.dasser.entities.User;

import java.util.List;

public interface IUserService {
    List<User> list();
    void create(User newUser);
    User listId(int id);
    void updateStatus(int id, String newStatus);

}
