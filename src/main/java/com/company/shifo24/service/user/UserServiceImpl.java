package com.company.shifo24.service.user;


import com.company.shifo24.domains.dtos.request.UserCreateDTO;
import com.company.shifo24.domains.dtos.response.UserDTO;
import com.company.shifo24.domains.entity.User;
import com.company.shifo24.domains.enums.Role;
import com.company.shifo24.exception.ConfirmPasswordErrorException;
import com.company.shifo24.exception.DuplicateValueException;
import com.company.shifo24.exception.ItemNotFoundException;
import com.company.shifo24.repository.UserRepository;
import com.company.shifo24.service.media.MediaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MediaService mediaService;

    @Override
    public UserDTO create(UserCreateDTO userCreateDTO) {
        checkUserPassword(userCreateDTO.getPassword(), userCreateDTO.getConfirmPassword());
        User mappedUser = modelMapper.map(userCreateDTO, User.class);
        mappedUser.setRole(Role.USER);
        User savedUser = userRepository.save(mappedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getByID(Long userID) {
        User user = getUserByID(userID);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public UserDTO update(Long userID, UserDTO userDTO) {
        User user = getUserByID(userID);
        if(userDTO.getEmail() != null || userDTO.getPhoneNumber() != null) {
            checkUserUnique(userDTO.getEmail(), userDTO.getPhoneNumber());
        }
        modelMapper.map(userDTO, user);
        if (userDTO.getMediaID() != null)
            user.setMedia(mediaService.getMediaById(userDTO.getMediaID()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public void delete(Long userID) {
        if (!userRepository.existsById(userID))
            throw new ItemNotFoundException("User not found with ID: " + userID);
        userRepository.deleteById(userID);
    }

    private UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
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

    private void checkUserUnique(String email, String phoneNumber) {
        if (userRepository.existsByEmail(email))
            throw new DuplicateValueException("User already exists with Email: " + email);
        else if (userRepository.existsByPhoneNumber(phoneNumber))
            throw new DuplicateValueException("User already exists with Phone number: " + phoneNumber);
    }

}