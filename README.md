# Learn Spring Security 
![spring-security](https://github.com/hoangtien2k3qx1/spring-security/assets/122768076/eac5e3e5-4e86-4e24-8882-404935ec5cda)

## Spring Security cung cấp nhiều lớp và thành phần cần thiết để xây dựng hệ thống bảo mật trong ứng dụng. Dưới đây là một số lớp và thành phần quan trọng trong Spring Security:

- [AuthenticationManager](): Đây là giao diện chính để xác thực người dùng. Nó có nhiệm vụ xác minh thông tin đăng nhập và trả về một đối tượng Authentication đại diện cho người dùng đã xác thực.
Thông tin thường bao gồm:
    - [Principal](): Đại diện cho người dùng được xác thực.
    - [Credentials](): Thường là mật khẩu hoặc chứng chỉ xác thực.
    - [Authorities](): Các quyền hoặc vai trò mà người dùng đã xác thực sở hữu.
    - [Details](): Thông tin chi tiết khác về người dùng sau quá trình xác thực.



- [Authentication](): Đây là đối tượng mô tả thông tin xác thực của người dùng sau khi họ đăng nhập thành công. Nó bao gồm thông tin như tên người dùng, mật khẩu, quyền hạn, vv.

- [UserDetailsService](): Giao diện này cung cấp phương thức để tìm kiếm thông tin người dùng từ nguồn dữ liệu như cơ sở dữ liệu. Nó trả về một đối tượng UserDetails mô tả người dùng.

- [UserDetails](): Đây là đối tượng mô tả chi tiết về người dùng, bao gồm tên người dùng, mật khẩu đã mã hóa, danh sách quyền hạn, vv.

- [UserDetails](): Giao diện này được sử dụng để mã hóa mật khẩu người dùng và kiểm tra tính đúng đắn của mật khẩu.

- [GrantedAuthority](): là một giao diện trong Spring Security, đại diện cho các quyền (hoặc vai trò) được cấp cho người dùng. Giao diện này đại diện cho quyền hạn của người dùng đã xác thực. Các quyền hạn này xác định quyền truy cập vào các phần của ứng dụng.
```java
các quyền có thể là "ROLE_ADMIN", "ROLE_USER", "READ_PERMISSION", "WRITE_PERMISSION"...
```

- [SecurityContextHolder](): Lớp này lưu trữ thông tin xác thực của người dùng hiện tại trong một ngữ cảnh bảo mật. Nó giúp bạn truy cập thông tin người dùng đã xác thực từ bất kỳ nơi nào trong ứng dụng.

    - Lưu ý: sau khi người dùng đăng nhập thành công, thông tin xác thực sẽ được lưu trữ trong SecurityContextHolder, và khi người dùng truy cập các tài nguyên bảo mật, Spring Security sẽ kiểm tra thông tin này để đảm bảo rằng người dùng có quyền truy cập.


- [SecurityConfigurerAdapter](): Lớp trừu tượng này cho phép bạn cấu hình các phần khác nhau của Spring Security thông qua việc ghi đè các phương thức.

- [HttpSecurity](): Đối tượng này cho phép bạn cấu hình quyền hạn dựa trên các yếu tố như URL, HTTP method, vv. Bạn có thể xác định ai có thể truy cập vào các phần của ứng dụng.

- [AuthenticationEntryPoint](): Giao diện này cho phép bạn xác định cách xử lý khi người dùng chưa xác thực cố gắng truy cập một tài nguyên yêu cầu xác thực.

- [AccessDecisionManager](): Lớp này quyết định xem người dùng đã xác thực có quyền truy cập vào tài nguyên yêu cầu hay không.

- [FilterChainProxy](): Lớp này quản lý một chuỗi các bộ lọc bảo mật, chịu trách nhiệm cho việc thực hiện các chức năng bảo mật như xác thực và phân quyền.

- [AuthenticationManagerBuilder](): AuthenticationManagerBuilder giúp bạn cấu hình các yếu tố liên quan đến việc xác thực.
    - UserDetailsService: Bạn có thể cấu hình một UserDetailsService thông qua AuthenticationManagerBuilder. UserDetailsService sẽ được sử dụng để tìm kiếm thông tin người dùng từ nguồn dữ liệu như cơ sở dữ liệu hoặc danh sách người dùng cấu hình.

    - PasswordEncoder: AuthenticationManagerBuilder cho phép bạn cấu hình một PasswordEncoder để mã hóa mật khẩu người dùng trước khi lưu vào cơ sở dữ liệu và để so sánh với mật khẩu đã mã hóa khi xác thực.

    - AuthenticationProvider: Bạn có thể thêm các AuthenticationProvider vào AuthenticationManagerBuilder. Mỗi AuthenticationProvider chịu trách nhiệm xác thực cho một nguồn dữ liệu cụ thể, cho phép xác thực từ nhiều nguồn khác nhau.

    - Cấu hình phân quyền (authorization): Mặc dù chủ yếu là về xác thực, AuthenticationManagerBuilder cũng có thể liên quan đến việc cấu hình phân quyền dựa trên người dùng đã xác thực.

    - Cấu hình bổ sung: Bạn có thể thực hiện các cấu hình bổ sung liên quan đến quá trình xác thực như xác thực từ nhiều nguồn khác nhau, cấu hình đa yếu tố (multi-factor authentication), xử lý lỗi xác thực, và nhiều yếu tố khác.






