package dev.jardel.catalog.domain.role.dto;

import dev.jardel.catalog.domain.utils.dto.EntityIdDto;
import dev.jardel.catalog.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends EntityIdDto {

    private String authority;

    public RoleDto(Role role) {
        this.setId(role.getId());
        authority = role.getAuthority();
    }
}
