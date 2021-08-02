package ru.aseev.jm231.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roless")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    @Column
    private String role;

    @Transient
    @ManyToMany( mappedBy = "roless")
    private Set<User> users;

    public Role() {
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        if (getRole().equals("ROLE_USER")) {
            return "USER";
        } else {
            return "ADMIN";
        }
    }
}
