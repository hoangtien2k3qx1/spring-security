package com.hoangtien2k3.service;

import com.hoangtien2k3.dto.UserDto;
import com.hoangtien2k3.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}