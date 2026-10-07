# HDT Logistics - Backend Service

Hệ thống Backend API & Xử lý nghiệp vụ cho HDT Logistics.

## Công nghệ sử dụng

- **Java**: 17 (Eclipse Temurin)
- **Framework**: Spring Boot 3.2.x (Spring Web, Spring Data JPA, Spring Security)
- **Database**: PostgreSQL 15 (cùng Flyway Migration)
- **Cache**: Redis 7
- **Authentication**: JWT (JSON Web Token)
- **Build tool**: Maven 3.9+

## Cấu trúc thư mục

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/hdlogistics/
│   │   │   ├── auth/          # Quản trị người dùng & Xác thực JWT
│   │   │   ├── cms/           # Quản lý nội dung (Dịch vụ, Bài viết, Menu, Tracking, Cài đặt)
│   │   │   ├── lead/          # Tiếp nhận Lead, Báo giá & Liên hệ
│   │   │   └── common/        # Config, Base Entity, Exception handling, Security
│   │   └── resources/
│   │       ├── db/migration/  # Flyway SQL migrations (V1 -> V6)
│   │       ├── db/seeds/      # Dữ liệu mẫu (Database seeds)
│   │       └── application.yml
│   └── test/
├── Dockerfile
└── pom.xml
```

## Hướng dẫn chạy

### Chạy trực tiếp qua Maven

Yêu cầu đã cài đặt Java 17 và Maven, kết nối đến PostgreSQL (port 5432) và Redis (port 6379).

```bash
mvn clean spring-boot:run
```

### Chạy qua Docker

```bash
docker build -t hdlogistics-backend .
docker run -p 8080:8080 hdlogistics-backend
```

API mặc định phục vụ tại: `http://localhost:8080/api/v1`
