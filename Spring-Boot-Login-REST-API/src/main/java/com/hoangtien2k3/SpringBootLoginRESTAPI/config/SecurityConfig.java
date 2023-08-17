package com.hoangtien2k3.SpringBootLoginRESTAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
@EnableMethodSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean // sử dụng AuthenticationManager: dùng để xác thực người dùng
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean // Đánh dấu phương thức này như một bean trong Spring container.
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { // SecurityFilterChain là một bean có nhiệm vụ cấu hình các bộ lọc bảo mật cho ứng dụng của bạn.

        // HttpSecurity: cho phép bạn cấu hình bảo mật dựa trên các tùy chọn liên quan đến HTTP.
        httpSecurity
                .csrf().disable() // tắt bảo mật csrf: Dòng này tắt bảo mật CSRF (Cross-Site Request Forgery), cho phép bạn thao tác với API mà không cần phải cung cấp token CSRF.
                .authorizeHttpRequests(authorize -> authorize  // Phần này bắt đầu việc cấu hình việc cho phép truy cập dựa trên các yêu cầu HTTP.
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()     // Đoạn này xác định rằng các yêu cầu HTTP dạng GET tới các đường dẫn bắt đầu bằng "/api/" sẽ được phép truy cập mà không cần xác thực (không cần đăng nhập).
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated() // Đây là cách để xác định rằng bất kỳ yêu cầu HTTP nào khác (các yêu cầu không phải là GET đến "/api/") cần phải được xác thực (đăng nhập) để có quyền truy cập.
                );

        return httpSecurity.build(); //Khi bạn đã cấu hình xong HttpSecurity, bạn cần trả về một SecurityFilterChain đã được cấu hình hoàn chỉnh để Spring Security có thể sử dụng.
    }
}

