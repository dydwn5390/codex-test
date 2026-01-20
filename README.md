# Todo App (Spring Boot + Java 21)

간단한 할일 관리 REST API 예시입니다.

## Requirements

- Java 21
- Gradle 8+

## Run

```bash
./gradlew bootRun
```

브라우저에서 `http://localhost:8080`으로 접속하면 간단한 UI 화면을 확인할 수 있습니다.

## API

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/todos` | 할일 목록 조회 |
| GET | `/api/todos/{id}` | 단건 조회 |
| POST | `/api/todos` | 생성 |
| PUT | `/api/todos/{id}` | 전체 수정 |
| PATCH | `/api/todos/{id}` | 완료 여부 토글 |
| DELETE | `/api/todos/{id}` | 삭제 |

### Example payload

```json
{
  "title": "회의 준비",
  "description": "자료 정리",
  "completed": false,
  "dueDate": "2024-12-31"
}
```
