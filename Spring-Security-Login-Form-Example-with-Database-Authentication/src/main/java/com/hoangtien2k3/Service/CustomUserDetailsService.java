package com.hoangtien2k3.Service;

import com.hoangtien2k3.model.User;
import com.hoangtien2k3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authoritySet = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())) // SimpleGrantedAuthority(role.getName()): kiểm tra xem role có giá trị (Text) hay không, nếu không có sẽ ném ra lỗi
                .collect(Collectors.toSet()); // máp tất cả về cùng 1 Set

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authoritySet
        );
    }
}
