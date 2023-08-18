package com.hoangtien2k3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // BCrypt mã hóa password
    }

    @Bean   // đánh dấu đây là 1 Bean: cấu hình và khởi tạo các thành phần của ứng dụng, quản lý bởi
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {    // Đây là một phương thức bean để cấu hình bộ lọc bảo mật.
        http.csrf().disable()   // vô hiệu hóa bảo mật CSRF
                .authorizeHttpRequests((authorize) ->  // Đây là phần cấu hình phân quyền cho các HTTP request.
                        authorize.requestMatchers("/register/**").permitAll()   // Các phương thức requestMatchers được sử dụng để xác định các URL mà phân quyền
                                .requestMatchers("/index").permitAll()          // index cũng được cho phép tất cả mọi người truy cập `permitAll()`
                                .requestMatchers("/users").hasRole("ADMIN")     //  chỉ được phép truy cập bởi những người dùng có vai trò (role) là "ADMIN"
                ).formLogin(form -> form
                                .loginPage("/login")    // Trang đăng nhập sẽ được định hướng đến "/login"
                                .loginProcessingUrl("/login")   // URL để xử lý quá trình đăng nhập.
                                .defaultSuccessUrl("/users")    // Sau khi đăng nhập thành công, người dùng sẽ được định hướng đến "/users".
                                .permitAll()                    //  Tất cả mọi người đều được phép truy cập trang đăng nhập.
                ).logout(logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Xác định URL để thực hiện đăng xuất.
                                .permitAll()        // Tất cả mọi người đều được phép truy cập trang đăng xuất.
                );
        return http.build();  // Trả về đối tượng SecurityFilterChain sau khi đã cấu hình.
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}