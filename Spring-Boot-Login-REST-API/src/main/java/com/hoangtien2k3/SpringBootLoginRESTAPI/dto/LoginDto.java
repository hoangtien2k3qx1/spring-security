package com.hoangtien2k3.SpringBootLoginRESTAPI.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}