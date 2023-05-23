package com.rest.api.utils.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserRespondDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
}
