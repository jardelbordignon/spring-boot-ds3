package dev.jardel.catalog.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserDto {

    @JsonProperty("user")
    private UserDto userDto;

    public GetUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
