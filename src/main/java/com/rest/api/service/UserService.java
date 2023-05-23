package com.rest.api.service;

import com.rest.api.utils.request.dto.PostDTO;
import com.rest.api.utils.request.dto.UserDTO;
import com.rest.api.utils.response.PostRespondDTO;
import com.rest.api.utils.response.UserRespondDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserRespondDTO> getAll();
    Optional<UserRespondDTO> findById(Long id);
    UserRespondDTO save(UserDTO dto);
    UserRespondDTO update(UserDTO dto, Long id);
    String delete(Long id);
}
