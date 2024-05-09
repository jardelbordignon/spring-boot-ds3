package dev.jardel.catalog.services;

import dev.jardel.catalog.config.DatabaseException;
import dev.jardel.catalog.domain.role.Role;
import dev.jardel.catalog.domain.role.dto.RoleDto;
import dev.jardel.catalog.domain.user.User;
import dev.jardel.catalog.domain.user.dto.CreateUserDto;
import dev.jardel.catalog.domain.user.exceptions.UserNotFoundException;
import dev.jardel.catalog.domain.user.dto.GetUserDto;
import dev.jardel.catalog.domain.user.dto.UserDto;
import dev.jardel.catalog.repositories.RoleRepository;
import dev.jardel.catalog.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEnconder;

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPaged(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserDto::new);
    }

    @Transactional(readOnly = true)
    public GetUserDto findById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        UserDto userDto = new UserDto(user);
        return new GetUserDto(userDto);
    }

    @Transactional
    public UserDto create(CreateUserDto dto) {
        User entity = new User();
        handleCopyDtoToEntity(dto, entity);
        entity.setPassword(passwordEnconder.encode(dto.getPassword()));
        entity = userRepository.save(entity);
        return new UserDto(entity);
    }

    @Transactional
    public GetUserDto update(Long id, UserDto dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            entity = userRepository.save(handleCopyDtoToEntity(dto, entity));
            dto = new UserDto(entity);
            return new GetUserDto(dto);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new UserNotFoundException("User not found with id: " + id);
            }
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("User with id: " + id + " cannot be deleted because it is in use by other entities.");
        }
    }

    private User handleCopyDtoToEntity(UserDto dto, User entity) {
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) entity.setLastName(dto.getLastName());

        entity.getRoles().clear();
        for(RoleDto roleDto : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entity.getRoles().add(role);
        }

        return entity;
    }
}
