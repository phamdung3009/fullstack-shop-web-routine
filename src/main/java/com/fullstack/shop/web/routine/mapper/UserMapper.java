package com.fullstack.shop.web.routine.mapper;

import com.fullstack.shop.web.routine.dto.request.UserRequestDTO;
import com.fullstack.shop.web.routine.dto.response.UserResponseDTO;
import com.fullstack.shop.web.routine.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User userEntitiesToUserRequestDto(UserRequestDTO user);

	UserResponseDTO userResponseDtoToUserEntities(User user);

	void updateUser(@MappingTarget User user, UserRequestDTO userRequestDTO);
}
