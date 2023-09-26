package com.company.shifo24.service.user;

import com.company.shifo24.domains.dtos.request.UserCreateDTO;
import com.company.shifo24.domains.dtos.response.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserCreateDTO userCreateDTO);

    UserDTO getByID(Long userID);

    List<UserDTO> getAllUser();

    UserDTO update(Long userID, UserDTO userDTO);

    List<UserDTO> searchByName(String firsName);

    void delete(Long userID);
}
