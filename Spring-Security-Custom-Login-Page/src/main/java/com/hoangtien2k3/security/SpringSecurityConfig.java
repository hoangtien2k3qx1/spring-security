package com.hoangtien2k3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean   //  cấu hình bộ lọc bảo mật (Security Filter Chain)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  //  Đây là một cấu hình để tắt bảo vệ CSRF. CSRF (Cross-Site Request Forgery) là một lỗ hổng bảo mật mà tấn công viên sử dụng để thực hiện các hành động không mong muốn trên tài khoản của người dùng.
                .authorizeHttpRequests((authorize) ->    // Đây là cấu hình quyền truy cập.
                        authorize.anyRequest().authenticated()
                ).formLogin(   // Đây là cấu hình xác thực thông qua giao diện đăng nhập.
                        form -> form
                                .loginPage("/login")    // Điều này định nghĩa URL của trang đăng nhập.
                                .loginProcessingUrl("/login")   // Đây là URL mà biểu mẫu đăng nhập sẽ gửi đến để xử lý.
                                .defaultSuccessUrl("/welcome")  // URL mà người dùng sẽ được chuyển đến sau khi đăng nhập thành công.
                                .permitAll()        // Cho phép tất cả người dùng truy cập vào trang đăng nhập mà không cần xác thực trước.
                ).logout(       // Đây là cấu hình cho chức năng đăng xuất.
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Xác định URL mà người dùng gửi yêu cầu để đăng xuất.
                                .permitAll()        // Cho phép tất cả người dùng thực hiện hành động đăng xuất mà không cần xác thực trước.
                );

        return http.build();    // Trả về đối tượng http đã được cấu hình sau khi bạn đã hoàn thành việc cấu hình.
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails hoangtien2k3 = User.builder()
                .username("hoangtien2k3")
                .password(passwordEncoder().encode("12042003"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()  // Đây là bắt đầu của việc tạo một đối tượng UserDetails mới.
                .username("admin")  // Đây là phương thức để đặt tên người dùng
                .password(passwordEncoder().encode("admin"))    // Đây là phương thức để đặt mật khẩu người dùng.
                .roles("ADMIN") // đây là quyền truy cập
                .build();   // Đây là phương thức để tạo một đối tượng UserDetails hoàn chỉnh từ các thông tin đã cung cấp trước đó.

        return new InMemoryUserDetailsManager(hoangtien2k3, admin); // lưu thông tin vào bộ nhớ Memory
    }
}
