package com.hoangtien2k3.service;

import com.hoangtien2k3.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}