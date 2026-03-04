# CGV 영화 예매 시스템

CGV 영화관 예매 시스템을 모방한 Spring Boot 웹 애플리케이션입니다.

## 프로젝트 정보

- **프로젝트명**: CGV
- **버전**: 0.0.1-SNAPSHOT
- **설명**: 영화 예매 시스템 (movie reservation)
- **Java 버전**: 17
- **Spring Boot 버전**: 3.5.8

## 주요 기능

- 🎬 **영화 관리**: 영화 정보 조회, 상세 정보 확인
- 👤 **사용자 관리**: 회원가입, 로그인, 마이페이지
- 🎫 **예매 시스템**: 상영 일정 조회, 좌석 선택, 예매 처리
- 🏪 **매점**: 음식 주문 기능
- 🎭 **극장 관리**: 극장, 상영관, 좌석 관리
- 👨‍💼 **관리자 기능**: 영화, 상영 일정, 극장 관리

## 기술 스택

### Backend
- **Spring Boot** 3.5.8
- **Spring Web** - REST API 및 웹 컨트롤러
- **Spring Data JPA** - 데이터베이스 ORM
- **MyBatis** 3.0.5 - SQL 매핑 프레임워크
- **Thymeleaf** - 서버사이드 템플릿 엔진
- **Lombok** - 코드 간소화

### Database
- **MySQL** - 메인 데이터베이스
- **MySQL Connector/J** - JDBC 드라이버

### Development Tools
- **Spring Boot DevTools** - 개발 편의성
- **Maven** - 빌드 도구

## 데이터베이스 구조

주요 엔티티:
- `User` - 사용자 정보 (ID, 비밀번호, 이름, 생년월일, 잔액, 등급, 포인트)
- `Movie` - 영화 정보 (제목, 설명, 개봉일, 상영시간, 연령등급, 배급사)
- `Theater` - 극장 정보
- `Screen` - 상영관 정보
- `Schedule` - 상영 일정
- `Seat` - 좌석 정보
- `Reservation` - 예매 정보
- `Review` - 영화 리뷰
- `Food` - 매점 음식

## 설치 및 실행

### 사전 요구사항
- Java 17 이상
- Maven 3.6 이상
- MySQL 8.0 이상

### 데이터베이스 설정

1. MySQL에 `cinema` 데이터베이스 생성:
```sql
CREATE DATABASE cinema;
```

2. `src/main/resources/application.properties` 파일에서 데이터베이스 연결 정보 확인/수정:
```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/cinema?serverTimezone=Asia/Seoul
spring.datasource.username=root
spring.datasource.password=0000
```

<!-- TODO: 확인 필요 - 데이터베이스 스키마 초기화 방법 -->

### 애플리케이션 실행

1. 프로젝트 클론:
```bash
git clone <repository-url>
cd CGV
```

2. 의존성 설치 및 빌드:
```bash
./mvnw clean install
```

3. 애플리케이션 실행:
```bash
./mvnw spring-boot:run
```

또는 JAR 파일로 실행:
```bash
./mvnw clean package
java -jar target/CGV-0.0.1-SNAPSHOT.jar
```

4. 브라우저에서 접속:
```
http://localhost:8080
```

## API 엔드포인트

### 메인 페이지
- `GET /` - 홈페이지 (인기 영화, 예정 영화 목록)

### 영화 관련
- `GET /movies` - 영화 목록
- `GET /movie/{id}` - 영화 상세 정보

### 예매 관련
- `GET /reservation/select?scheduleId={id}` - 좌석 선택 페이지
- `POST /reservation/book` - 예매 처리
- `GET /reservation/success` - 예매 완료 페이지

### 사용자 관련
- `GET /login` - 로그인 페이지
- `GET /register` - 회원가입 페이지
- `GET /mypage` - 마이페이지

### 매점
- `GET /store/menu` - 매점 메뉴
- `GET /store/select-theater` - 극장 선택

### 관리자 기능
- `GET /admin/main` - 관리자 메인
- `GET /admin/movies` - 영화 관리
- `GET /admin/theaters` - 극장 관리
- `GET /admin/schedules` - 상영 일정 관리

## 프로젝트 구조

```
src/
├── main/
│   ├── java/com/cgv/CGV/
│   │   ├── api/           # REST API 컨트롤러
│   │   ├── controller/    # 웹 컨트롤러
│   │   ├── DTO/          # 데이터 전송 객체
│   │   ├── entity/       # JPA 엔티티
│   │   ├── repository/   # 데이터 접근 계층
│   │   └── service/      # 비즈니스 로직
│   └── resources/
│       ├── templates/    # Thymeleaf 템플릿
│       └── application.properties
└── test/                 # 테스트 코드
```

## 개발 환경 설정

### IDE 설정
- IntelliJ IDEA 또는 Eclipse 사용 권장
- Lombok 플러그인 설치 필요

### 개발 모드 실행
Spring Boot DevTools가 포함되어 있어 코드 변경 시 자동 재시작됩니다.

