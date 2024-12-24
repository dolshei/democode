
# API 명세서

## 개요

- **API 이름**: Example API
- **버전**: v1.0
- **기본 URL**: `https://api.example.com`
- **인증 방식**: API Key 또는 OAuth2

---

## 목차

- [API 명세서](#api-명세서)
    - [개요](#개요)
    - [목차](#목차)
    - [인증](#인증)
        - [API Key](#api-key)
        - [OAuth2](#oauth2)
    - [엔드포인트 개요](#엔드포인트-개요)
    - [엔드포인트 상세](#엔드포인트-상세)
        - [GET /users](#get-users)
        - [POST /users](#post-users)
    - [에러 코드](#에러-코드)
    - [예시](#예시)
        - [사용자 목록 가져오기](#사용자-목록-가져오기)

---

## 인증

### API Key

- API 요청 시, HTTP 헤더에 API Key를 포함해야 합니다.
- **헤더 형식**:

  ```text
  Authorization: Bearer {API_KEY}
  ```

### OAuth2

- `client_id`와 `client_secret`을 사용해 토큰을 발급받습니다.
- **토큰 엔드포인트**: `https://api.example.com/oauth/token`

---

## 엔드포인트 개요

| 메서드 | URL         | 설명                   |
|--------|-------------|------------------------|
| GET    | /users      | 사용자 목록 조회        |
| POST   | /users      | 사용자 생성            |
| GET    | /users/{id} | 특정 사용자 정보 조회   |
| PUT    | /users/{id} | 사용자 정보 업데이트    |
| DELETE | /users/{id} | 사용자 삭제            |

---

## 엔드포인트 상세

### GET /users

- **설명**: 모든 사용자의 정보를 반환합니다.
- **URL**: `/users`
- **Method**: `GET`
- **Headers**:

  ```json
  {
    "Authorization": "Bearer {API_KEY}"
  }
  ```

- **Query Parameters**:

  | 이름       | 타입   | 필수 여부 | 설명               |
    |------------|--------|-----------|--------------------|
  | `page`     | int    | 선택      | 페이지 번호         |
  | `limit`    | int    | 선택      | 한 페이지당 결과 수 |

- **응답**:
    - **HTTP 200 OK**

      ```json
      {
        "data": [
          {
            "id": 1,
            "name": "John Doe",
            "email": "john@example.com"
          },
          {
            "id": 2,
            "name": "Jane Doe",
            "email": "jane@example.com"
          }
        ],
        "meta": {
          "page": 1,
          "limit": 10,
          "total": 50
        }
      }
      ```

    - **HTTP 401 Unauthorized**

      ```json
          {
            "error": "Invalid API Key"
          }
      ```

---

### POST /users

- **설명**: 새로운 사용자를 생성합니다.
- **URL**: `/users`
- **Method**: `POST`
- **Headers**:

  ```json
  {
    "Authorization": "Bearer {API_KEY}",
    "Content-Type": "application/json"
  }
  ```

- **Body**:

  ```json
  {
    "name": "John Doe",
    "email": "john@example.com"
  }
  ```

- **응답**:
    - **HTTP 201 Created**

      ```json
      {
        "id": 3,
        "name": "John Doe",
        "email": "john@example.com",
        "createdAt": "2024-11-27T12:34:56Z"
      }
      ```

    - **HTTP 400 Bad Request**

      ```json
      {
        "error": "Invalid email format"
      }
      ```

---

## 에러 코드

| 코드 | 메시지                    | 설명                            |
|------|---------------------------|---------------------------------|
| 400  | Bad Request               | 잘못된 요청입니다.              |
| 401  | Unauthorized              | 인증에 실패했습니다.            |
| 404  | Not Found                 | 요청한 리소스를 찾을 수 없습니다. |
| 500  | Internal Server Error     | 서버 내부 오류가 발생했습니다.  |

---

## 예시

### 사용자 목록 가져오기

**요청**:

```text
GET /users?page=1&limit=10 HTTP/1.1
Host: api.example.com
Authorization: Bearer {API_KEY}
```

**응답**:

```json
{
  "data": [
    {
      "id": 1,
      "name": "John Doe",
      "email": "john@example.com"
    }
  ],
  "meta": {
    "page": 1,
    "limit": 10,
    "total": 50
  }
}
```