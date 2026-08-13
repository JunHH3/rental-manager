# Rental Manager

물품의 등록, 조회, 상세 확인, 수정, 삭제를 처리하는 Spring Boot 기반 웹 애플리케이션입니다.

Spring MVC의 요청 처리 흐름과 Spring Data JPA를 이용한 데이터 영속화를 직접 구현하며,
웹 애플리케이션의 기본적인 CRUD 구조를 학습하고 적용하는 것을 목적으로 개발했습니다.

## 기술 스택

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Mustache
- H2 Database
- Gradle

## 주요 기능

### 물건 등록

사용자가 상품명과 설명을 입력하면 `ItemForm`으로 입력값을 전달받고,
이를 `Item` 엔티티로 변환하여 데이터베이스에 저장합니다.

```text
등록 화면
→ ItemForm
→ Item
→ ItemRepository
→ DB
```

### 물건 삭제

물건 상세 화면에서 삭제 요청을 보내면 물건의 `id`를 전달받아
`ItemRepository`를 통해 해당 물건을 데이터베이스에서 삭제합니다.

```text
상세 화면
→ 물건 삭제 요청
→ id
→ ItemRepository
→ DB 삭제
```