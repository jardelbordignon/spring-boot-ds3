package dev.jardel.catalog.domain.user.dto;

import dev.jardel.catalog.domain.role.Role;
import dev.jardel.catalog.domain.role.dto.RoleDto;
import dev.jardel.catalog.domain.user.User;
import dev.jardel.catalog.domain.utils.dto.EntityBaseDto;
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
public class UserDto extends EntityBaseDto {

    private String firstName;
    private String lastName;
    private String email;

    Set<RoleDto> roles = new HashSet<>();

    public UserDto(User user) {
        this.setId(user.getId());
        this.setCreatedAt(user.getCreatedAt());
        this.setUpdatedAt(user.getUpdatedAt());
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        // Na entity User foi definido o Hibernate como eager, para que o Hibernate carregue as roles assim que o usuário for carregado
        user.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }
}
