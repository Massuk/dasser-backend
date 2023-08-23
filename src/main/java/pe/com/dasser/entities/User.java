package pe.com.dasser.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;
    @Column(name = "login", length = 50, nullable = false)
    private String login;
    @Column(name = "password", length = 150, nullable = false)
    private String password;
    @Column(name = "status", length = 9, nullable = false)
    private String status;
    @CreationTimestamp
    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;
    @UpdateTimestamp
    @Column(name = "modifiedDate", nullable = false)
    private LocalDate modifiedDate;
    @ManyToOne
    @JoinColumn(name = "idRole", nullable = false)
    private Role role;

    public User() {
    }

    public User(int idUser, String name, String lastname, String login, String password, String status, LocalDate creationDate, LocalDate modifiedDate, Role role) {
        this.idUser = idUser;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.status = status;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
