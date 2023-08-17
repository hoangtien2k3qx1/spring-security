package com.hoangtien2k3.SpringSecurityBasicAuthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // dùng thuật toán mã hóa BCrypt mã hóa password
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails hoangtien2k3 = User.builder() // User.builder() : tạo ra đối tượng User
                .username("hoangtien2k3")     // cập nhật Username
                .password(passwordEncoder().encode("password")) // gọi hàm mã hóa password (BCrypt) và đặt mật khẩu
                .roles("USER")          // Gán vai trò "USER" cho người dùng
                .build();               // return UserDetails

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        //  là một triển khai của UserDetailsService để quản lý người dùng trong bộ nhớ, được sử dụng trong môi trường phát triển hoặc môi trường đơn giản.
        return new InMemoryUserDetailsManager(hoangtien2k3, admin);
    }
}