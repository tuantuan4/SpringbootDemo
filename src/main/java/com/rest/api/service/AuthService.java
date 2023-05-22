package com.rest.api.service;

import com.rest.api.payload.LoginDTO;
import com.rest.api.payload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDto);

    String register(RegisterDTO registerDto);
}
