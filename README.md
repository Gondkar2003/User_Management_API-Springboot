# 👤 User Management API (Spring Boot)

A simple RESTful API to handle user registration, login, and profile management using Spring Boot and PostgreSQL.

---

## 🚀 Features
- GetAll Users
- View user by ID
- Add an User
- Update user details
- Delete user
- API Docs with Swagger UI
- Connected to PostgreSQL

---

## 🛠️ Technologies
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Swagger / SpringDoc
- Gradle

---

## 📦 API Endpoints

| Method | Endpoint               | Description           |
|--------|------------------------|-----------------------|
| POST   | `/api/users/add`       | Register new user     |
| GET    | `/api/users`           | Login user            |
| GET    | `/api/users/{id}`      | Get user by ID        |
| PUT    | `/api/users/{id}`      | Update user info      |
| DELETE | `/api/users/{id}`      | Delete user           |

---

## 🧪 Sample Requests

### 📝 Register
```json
POST /api/users/add

{
  "name": "Darshan",
  "email": "darshan@test.com",
  "password": "pass123"
}
