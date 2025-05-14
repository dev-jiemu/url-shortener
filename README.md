# url-shortener
in-memory 환경에서 단축 url 제공하는 API Server (for SpringBoot)

---

### 🛠️ 프로젝트 구조
```

```

### 🏷️ API Endpoints

| Endpoint | HTTP Method | 설명 |
|------------|------------|------|
| `/api/shorten` | POST | 원본 URL을 전달받아 단축 URL을 생성하고 반환 |
| `/{shortKey}` | GET | 단축 URL의 키를 받아 해당하는 원본 URL로 리다이렉트 |
| `/not-found` | GET | 존재하지 않는 단축 URL에 접근했을 때 404 에러 페이지를 표시 |
