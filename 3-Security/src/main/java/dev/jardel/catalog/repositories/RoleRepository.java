package dev.jardel.catalog.repositories;

import dev.jardel.catalog.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
