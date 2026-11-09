
<div align="center">

# 📦 Rental Manager

### 물품 등록 · 대여 · 반납 관리 웹 애플리케이션

![Type](https://img.shields.io/badge/TYPE-BACKEND_PRACTICE-2563EB?style=for-the-badge)
![Java](https://img.shields.io/badge/Java_21-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)

</div>

## 🎯 Overview

물품 등록부터 대여 및 반납 상태 관리까지 구현한 Spring Boot 기반 웹 애플리케이션입니다.

MVC 패턴, JPA, 페이징, 세션 및 테스트를 학습하기 위해 개발했습니다.

## ✨ Features

- 물품 등록·조회·수정·삭제
- 물품 이름 검색 및 페이징
- 물품 대여 및 반납 상태 관리
- 회원 데이터 저장 및 세션 로그인
- 물품 조회 REST API
- Entity / DTO 분리
- 예외 처리 및 테스트 코드
- 대여 기록 도메인 구성

### Rental Status

```text
AVAILABLE ↔ RENTED
```

## 🛠 Tech Stack

`Java 21` · `Spring Boot 4` · `Spring Data JPA` · `H2 Database` · `Mustache` · `Gradle` · `JUnit 5` · `MockMvc`

## 🔌 API & Pages

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/items/{id}` | 물품 상세 조회 API |
| GET | `/items` | 물품 목록 및 검색 |
| POST | `/items` | 물품 등록 |
| POST | `/items/{id}/rent` | 물품 대여 |
| POST | `/items/{id}/return` | 물품 반납 |

## 🧱 Domain

```text
Item
 └─ 물품 정보 및 대여 상태

Member
 └─ 회원 정보 및 세션

Rental
 └─ 대여 기록 및 물품 연관관계
```

## 🧪 Testing

JUnit 5 및 MockMvc를 이용한 테스트 코드가 포함되어 있습니다.

- 물품 Controller / Service
- 회원 Controller / Service
- 대여 Service
- API 응답 검증

```bash
./gradlew test
```

## ▶️ Getting Started

```bash
git clone https://github.com/JunHH3/rental-manager.git
cd rental-manager
./gradlew bootRun
```

Windows에서는 `gradlew.bat bootRun`을 사용할 수 있습니다.

> 학습용 프로젝트입니다. 현재 로그인 코드에서 비밀번호를 평문으로 비교하므로 실제 서비스 배포 전 암호화 및 인증·인가 보완이 필요합니다.

[🔗 GitHub Repository](https://github.com/JunHH3/rental-manager)
