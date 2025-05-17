# URL Shortener

---

간단한 URL 단축 서비스 API 서버입니다. 

Java Spring Boot 프레임워크를 사용하여 구현되었으며, 인메모리 저장소를 사용합니다.

## 🔧 기능

- 원본 URL을 단축 URL로 변환
- 단축 URL을 통한 원본 URL로의 리다이렉션
- URL.방문 횟수 추적
- URL 만료 기능 지원 (일 단위)

## ⚙️ 기술 스택

- Java 17
- Spring Boot 3.2.4
- Lombok
- 인메모리 저장소 (ConcurrentHashMap)

## ⛓️ API 엔드포인트

| 엔드포인트 | HTTP 메서드 | 설명                                     |
|------------|------------|----------------------------------------|
| `/api/shorten` | POST | 원본 URL을 전달받아 단축 URL을 생성하고 반환           |
| `/{shortKey}` | GET | 단축 URL의 키를 받아 해당하는 원본 URL로 리다이렉트       |
| `/not-found` | GET | 존재하지 않는 단축 URL에 접근했을 때 404 에러 페이지를 표시  |

## 📜 프로젝트 구조

```
src/main/java/com/urlshortener/
├── UrlShortenerApplication.java
├── controller
│   └── UrlShortenerController.java
├── service
│   └── UrlShortenerService.java
├── repository
│   ├── UrlRepository.java
│   └── InMemoryUrlRepository.java
├── model
│   └── ShortenedUrl.java
└── dto
    ├── UrlShortenRequest.java
    └── UrlShortenResponse.java
```

## 🛠️ 빌드 및 실행

### 요구사항

- Java 17
- Gradle

### 빌드

```bash
./gradlew build
```

### 실행

```bash
./gradlew bootRun
```

또는 빌드 후 JAR 파일을 직접 실행

```bash
java -jar build/libs/url-shortener-0.0.1-SNAPSHOT.jar
```

Default Server Port : 8080

## API 사용 예시

### URL 단축

```bash
curl -X POST http://localhost:8080/api/shorten \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.example.com/very/long/url/that/needs/to/be/shortened", "plusDays": 7}'
```

응답:

```json
{
  "originalUrl": "https://www.example.com/very/long/url/that/needs/to/be/shortened",
  "shortUrl": "http://localhost:8080/AbCdEf"
}
```

### URL 접근

브라우저나 curl을 사용하여 단축 URL에 접근:

```
http://localhost:8080/AbCdEf
```

--- 

## 🤔 앞으로 해야할 일

1. **영속성 추가**:
   - JPA와 데이터베이스(MySQL, PostgreSQL 등)를 통한 영구 저장소 구현

2. **캐싱 계층**:
   - Redis를 이용한 자주 사용되는 URL 캐싱

3. **사용자 인증**:
   - 사용자별 URL 관리를 위한 인증 시스템 추가

4. **분석 기능**:
   - 클릭 통계, 지역별 접근 데이터 등 고급 분석 기능

5. **커스텀 URL**:
   - 사용자 지정 단축 URL 지원

=> 이후 작업은 별도 branch 에서 진행