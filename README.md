# wanted-pre-onboarding-backend

### 목록

[1. 지원자](#지원자-김지욱)  
[2. 애플리케이션 실행 방법](#애플리케이션-실행-방법)  
[3. 데이터베이스 테이블 구조](#데이터베이스-테이블-구조)  
[4. 구현한 API의 동작을 촬영한 데모 영상 링크](#구현한-api의-동작을-촬영한-데모-영상-링크-아래-사진-클릭)  
[5. 구현 방법 및 이유에 대한 간략한 설명](#구현-방법-및-이유에-대한-간략한-설명)  
[6. API 명세(request/response 포함)](#api-명세requestresponse-포함)  
[7. 디렉토리 구조](#디렉토리-구조)  
[7. 도커 컴포즈 실행](#docker-compose)

---

## 지원자: 김지욱

---

## 애플리케이션 실행 방법

1. 레포지토리 클론
```text
git clone https://github.com/N1ghtsky0/wanted-pre-onboarding-backend.git
```
2. application.yml 수정
```text
1. ./src/main/resources 의 application.yml 파일의 spring.profiles.default = dev 로 변경

2. 같은 디렉토리 내의 application-dev.yml 내의 정보를 변경
```
3. 프로젝트 실행
```text
./gradlew bootrun
```

---
## 데이터베이스 테이블 구조

![image](https://github.com/N1ghtsky0/wanted-pre-onboarding-backend/assets/90381800/457e4ba2-a6ec-4397-b701-0b573e731faf)

<table>
  <tr>
    <td colspan="2">User 테이블</td>
    <td colspan="2">Board 테이블</td>
  </tr>
  <tr>
    <td>컬럼명</td>
    <td>특징</td>
    <td>컬럼명</td>
    <td>특징</td>
  </tr>
  <tr>
    <td>id</td>
    <td>사용자의 pk</td>
    <td>id</td>
    <td>게시글의 pk</td>
  </tr>
  <tr>
    <td>created_at</td>
    <td>회원가입 날짜 및 시각</td>
    <td>user_id</td>
    <td>게시글을 작성한 유저의 fk</td>
  </tr>
  <tr>
    <td>updated_at</td>
    <td>마지막 회원정보 수정 날짜 및 시각</td>
    <td>created_at</td>
    <td>게시글 생성 날짜 및 시각</td>
  </tr>
  <tr>
    <td>uuid</td>
    <td>유저의 고유 uuid<br>유저의 pk 노출을 막기 위해 통신에 사용됨</td>
    <td>updated_at</td>
    <td>마지막 게시글 수정 날짜 및 시각</td>
  </tr>
  <tr>
    <td>nick_name</td>
    <td>회원가입에 사용한 유저의 닉네임<br>별도의 설정이 없을 경우 이메일에서 추출하여 저장</td>
    <td>content</td>
    <td>게시글의 본문에 해당하는 내용<br>공백 금지</td>
  </tr>
  <tr>
    <td>sign_in_id</td>
    <td>로그인에 사용되는 아이디<br>이메일 형식</td>
    <td>title</td>
    <td>게시글의 제목<br>공백 금지</td>
  </tr>
  <tr>
    <td>sign_in_pwd</td>
    <td>로그인에 사용되는 비밀번호<br>암호화 되어 저장됨</td>
    <td></td>
    <td></td>
  </tr>
</table>


---

## 구현한 API의 동작을 촬영한 데모 영상 링크 ([클릭](https://youtu.be/Y6rMd_tZvY0))

---

## 구현 방법 및 이유에 대한 간략한 설명

* 게시판(Board)에 유저(User)를 ManyToOne으로 연결하면서 Lazy를 하지 않은 이유
  * 게시판은 게시글을 작성, 조회할 때 작성자가 누구인지 항상 알려주는 특징이 있다고 생각했기 때문에
* 게시글 목록 조회 시 JPQL을 사용한 이유
  * Lazy 설정을 하지 않아서 게시글을 조회할 때 유저의 모든 정보를 함께 반환하는 것을 방지하기 위해서

---

## API 명세(request/response 포함)

| 기능명      | http method | endpoint       | request header        | request body                                              | response body                                                       |
|----------|-------------|----------------|-----------------------|-----------------------------------------------------------|---------------------------------------------------------------------|
| 회원가입     | post        | /auth/register | none                  | signInId: String<br>signInPwd: String<br>nickName: String | none                                                                |
| 로그인      | post        | /auth/sign-in  | none                  | signInId: String<br>signInPwd: String                     | uid: String<br>accessToken: String                                  |
| 게시글 작성   | post        | /board         | AUTHORIZATION: String | title: String<br>content: String<br>uid: String           | boardId: number(int)                                                |
| 게시글 목록 조회 | get         | /board         | none                  | page: number<br>size: number<br>sort: String[]            | * 아래의 접힌글 참고                                                        |
| 특정 게시글 조회 | get         | /board/{id}    | none                  | none                                                      | id: number<br>title: String<br>content: String<br>createdAt: String |
| 게시글 수정   | patch       | /board         | AUTHORIZATION: String | userUuid: String<br>boardId: number<br>content: String    | none                                                                |
| 게시글 삭제   | delete      | /board         | AUTHORIZATION: String | userUuid: String<br>boardId: number                       | none                                                                |

<details>
<summary>게시글 목록 조회 api response body</summary>
<div>

```text
{
  "totalPages": 0,
  "totalElements": 0,
  "size": 0,
  "content": [
    {
      "id": 0,
      "content": "string",
      "updatedAt": "2023-08-02T12:13:13.958Z",
      "createdAt": "2023-08-02T12:13:13.958Z",
      "title": "string",
      "author": "string"
    }
  ],
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
  },
  "first": true,
  "last": true,
  "numberOfElements": 0,
  "pageable": {
    "offset": 0,
    "sort": {
      "empty": true,
      "sorted": true,
      "unsorted": true
    },
    "pageNumber": 0,
    "pageSize": 0,
    "unpaged": true,
    "paged": true
  },
  "empty": true
}
```

</div>
</details>

정확한 정보 및 테스트는 **프로젝트 실행 후** http://localhost:8080/swagger-ui.html 로 접속하여 확인 가능

---

### 디렉토리 구조

```text
./src/main/java/com/example

└─demo
    ├─api
    │  ├─auth
    │  │  ├─controller
    │  │  ├─dto
    │  │  └─service
    │  ├─board
    │  │  ├─controller
    │  │  ├─dao
    │  │  ├─dto
    │  │  ├─model
    │  │  ├─repository
    │  │  └─service
    │  └─user
    │      ├─model
    │      └─repository
    └─global
        ├─annotation
        ├─config
        │  ├─apiDocs
        │  ├─jwt
        │  └─security
        └─exception
```

---

### docker-compose

```text
1. ./src/main/resources 의 application.yml 파일의 spring.profiles.default = docker 로 변경

2. docker-compose up -d
```
