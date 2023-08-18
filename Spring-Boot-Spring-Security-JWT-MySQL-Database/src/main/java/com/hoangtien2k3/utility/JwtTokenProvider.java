package com.hoangtien2k3.utility;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // chúng ta có thể xem chi tiết các hoạt động và gỡ lỗi các vấn đề liên quan đến bảo mật
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret; // đây là khóa bí mật

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate; // đây là thời gian hết hạn của mã thông báo.

    // generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName(); // lấy tên người dùng từ thông tin xác thực (Authentication)

        Date currentDate = new Date();  // lấy thời gian hiện tại

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);  // Tính thời gian hết hạn của mã thông báo

        //  // Bắt đầu xây dựng mã JWT
        String token = Jwts.builder()       // bắt đầu tạo token
                .setSubject(username)       // đặt chủ thể subject của mã thông báo là tên người dùng
                .setIssuedAt(new Date())    // đặt thời gian phát hành thông báo là thời gian hiện tại
                .setExpiration(expireDate)  // đặt thời gian hết hạn mã thông báo
                .signWith(key())            // ký mã hóa thông báo bí mật
                .compact();                 // compact thông báo thành chuỗi

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    // get username from jwt token
    public String getUsername(String token) {
        // Xây dựng một trình phân tích JWT sử dụng khóa đã được định nghĩa trong phương thức key()
        Claims claims = Jwts.parserBuilder() //  Bắt đầu xây dựng một trình phân tích JWT (JWT parser).
                .setSigningKey(key()) // Đặt khóa bí mật sử dụng để xác minh chữ ký của JWT.
                .build()               // Kết thúc việc xây dựng trình phân tích và chuẩn bị sẵn sàng để giải mã và phân tích mã thông báo.
                .parseClaimsJws(token) // Giải mã và phân tích JWT
                .getBody();             // Lấy phần thân (payload) của mã thông báo JWT, được biểu diễn bởi đối tượng Claims.
        String username = claims.getSubject(); // Lấy tên người dùng từ phần thân (payload) của JWT
        return username; // Trả về tên người dùng
    }


    // validate Jwt token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
