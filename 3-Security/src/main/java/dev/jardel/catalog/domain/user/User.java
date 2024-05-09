package dev.jardel.catalog.domain.user;

import dev.jardel.catalog.domain.role.Role;
import dev.jardel.catalog.domain.utils.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends EntityBase {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // No spring security, o usuário tem que ter pelo menos uma role, então sempre será necessário carregar as roles do usuário corrente
    // FetchType.EAGER para que o Hibernate carregue as roles assim que o usuário for carregado
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
