# Rental Manager

Spring Boot를 이용해 물품 등록, 대여·반납, 회원 로그인, REST API를 구현한 물품 대여 관리 웹 애플리케이션입니다.

Controller-Service-Repository 계층을 분리하고,
Spring Data JPA, 세션 로그인, DTO, 예외 처리, REST API 및 테스트를 직접 구현했습니다.

## 기술 스택

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Mustache
- H2 Database
- Gradle
- JUnit 5 / MockMvc

## 주요 기능

- 물품 등록 / 조회 / 수정 / 삭제
- 물품 검색 및 페이징
- 물품 대여 / 반납
- 대여 기록 관리
- 회원가입 / 로그인
- HttpSession 기반 로그인 상태 관리
- 로그인 사용자 대여 권한 확인
- Entity / DTO 분리
- REST API
- API 예외 처리 및 JSON 오류 응답

## 프로젝트 구조

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### 주요 도메인

```text
Item
├─ 물품 정보 및 대여 상태
│
Rental
├─ 대여자
├─ 대여 시간
└─ Item과 @ManyToOne 관계

Member
└─ 회원 및 로그인 정보
```

## REST API

### 물품 상세 조회

```http
GET /api/items/{id}
```

정상 응답:

```json
{
  "id": 1,
  "name": "카메라",
  "description": "배터리",
  "rentalStatus": "AVAILABLE"
}
```

존재하지 않는 물품 조회:

```json
{
  "error": "물건을 찾을 수 없습니다."
}
```

HTTP Status:

```text
404 Not Found
```

## 테스트

JUnit과 MockMvc를 이용해 Service와 REST API를 테스트했습니다.

API 테스트에서는 다음을 검증합니다.

```text
200 OK
404 Not Found
JSON 응답 데이터
Content-Type
```

전체 테스트:

```bash
./gradlew test
```

## 실행

```bash
git clone https://github.com/JunHH3/rental-manager.git
cd rental-manager
./gradlew bootRun
```

접속:

```text
http://localhost:8080
```