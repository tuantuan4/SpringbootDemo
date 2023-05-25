package com.rest.api.service.impl;

import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.entity.User;
import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.UserService;
import com.rest.api.utils.request.dto.UserDTO;
import com.rest.api.utils.response.PostRespondDTO;
import com.rest.api.utils.response.UserRespondDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserRespondDTO> getAll() {
        return userRepository.findAll().stream().map(p -> mapperToUserDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserRespondDTO> findById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return Optional.of(mapperToUserDTO(userRepository.findById(id).get()));
    }

    @Override
    public UserRespondDTO save(UserDTO dto) {
        return null;
    }

    @Override
    public UserRespondDTO update(UserDTO dto, Long id) {
        return null;
    }

    @Override
    public String delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found to delete id: " + id));
        userRepository.deleteById(id);
        return "Delete Post Success";
    }

    private UserRespondDTO mapperToUserDTO(User entity) {
        UserRespondDTO dto = new UserRespondDTO();
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setId(entity.getId());
        return dto;
    }
}
