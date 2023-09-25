package com.company.shifo24.service.user;


import com.company.shifo24.domains.dtos.request.UserCreateDTO;
import com.company.shifo24.domains.dtos.request.WorkplaceDTO;
import com.company.shifo24.domains.dtos.response.UserResponseDTO;
import com.company.shifo24.domains.entity.User;
import com.company.shifo24.domains.entity.Workplace;
import com.company.shifo24.domains.enums.Role;
import com.company.shifo24.exception.ConfirmPasswordErrorException;
import com.company.shifo24.exception.ItemNotFoundException;
import com.company.shifo24.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDTO create(UserCreateDTO userCreateDTO) {
        checkUserPassword(userCreateDTO.getPassword(), userCreateDTO.getConfirmPassword());
        User mappedUser = modelMapper.map(userCreateDTO, User.class);
        mappedUser.setRole(Role.USER);
        User savedUser = userRepository.save(mappedUser);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getByID(Long userID) {
        User user = getUserByID(userID);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void delete(Long userID) {
        if (!userRepository.existsById(userID))
            throw new ItemNotFoundException("User not found with ID: " + userID);
        userRepository.deleteById(userID);
    }

    private UserResponseDTO toDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    private void checkUserPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword))
            throw new ConfirmPasswordErrorException("Confirm password error with Confirm password: " + confirmPassword);
    }

    private User getUserByID(Long userID) {
        return userRepository.findById(userID).orElseThrow(
                () -> new ItemNotFoundException("User not found with ID: " + userID)
        );
    }

}