# Learn Spring Security 

## Spring Security cung cấp nhiều lớp và thành phần cần thiết để xây dựng hệ thống bảo mật trong ứng dụng. Dưới đây là một số lớp và thành phần quan trọng trong Spring Security:

- [AuthenticationManager](): Đây là giao diện chính để xác thực người dùng. Nó có nhiệm vụ xác minh thông tin đăng nhập và trả về một đối tượng Authentication đại diện cho người dùng đã xác thực.

- [Authentication](): Đây là đối tượng mô tả thông tin xác thực của người dùng sau khi họ đăng nhập thành công. Nó bao gồm thông tin như tên người dùng, mật khẩu, quyền hạn, vv.

- [UserDetailsService](): Giao diện này cung cấp phương thức để tìm kiếm thông tin người dùng từ nguồn dữ liệu như cơ sở dữ liệu. Nó trả về một đối tượng UserDetails mô tả người dùng.

- [UserDetails](): Đây là đối tượng mô tả chi tiết về người dùng, bao gồm tên người dùng, mật khẩu đã mã hóa, danh sách quyền hạn, vv.

- [UserDetails](): Giao diện này được sử dụng để mã hóa mật khẩu người dùng và kiểm tra tính đúng đắn của mật khẩu.

- [GrantedAuthority](): Giao diện này đại diện cho quyền hạn của người dùng đã xác thực. Các quyền hạn này xác định quyền truy cập vào các phần của ứng dụng.

- [SecurityContextHolder](): Lớp này lưu trữ thông tin xác thực của người dùng hiện tại trong một ngữ cảnh bảo mật. Nó giúp bạn truy cập thông tin người dùng đã xác thực từ bất kỳ nơi nào trong ứng dụng.

- [SecurityConfigurerAdapter](): Lớp trừu tượng này cho phép bạn cấu hình các phần khác nhau của Spring Security thông qua việc ghi đè các phương thức.

- [HttpSecurity](): Đối tượng này cho phép bạn cấu hình quyền hạn dựa trên các yếu tố như URL, HTTP method, vv. Bạn có thể xác định ai có thể truy cập vào các phần của ứng dụng.

- [AuthenticationEntryPoint](): Giao diện này cho phép bạn xác định cách xử lý khi người dùng chưa xác thực cố gắng truy cập một tài nguyên yêu cầu xác thực.

- [AccessDecisionManager](): Lớp này quyết định xem người dùng đã xác thực có quyền truy cập vào tài nguyên yêu cầu hay không.

- [FilterChainProxy](): Lớp này quản lý một chuỗi các bộ lọc bảo mật, chịu trách nhiệm cho việc thực hiện các chức năng bảo mật như xác thực và phân quyền.








