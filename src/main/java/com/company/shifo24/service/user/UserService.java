package com.company.shifo24.service.user;

import com.company.shifo24.domains.dtos.request.UserCreateDTO;
import com.company.shifo24.domains.dtos.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO create(UserCreateDTO userCreateDTO);

    UserResponseDTO getByID(Long userID);

    List<UserResponseDTO> getAllUser();

    void delete(Long userID);
}
