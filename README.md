# wanted-pre-onboarding-backend
### 원티드 프리온보딩 8월 백엔드 인턴십 사전과제

---
### 인적사항
박성환

---
### 애플리케이션 실행 방법
1. 사용자 회원가입 엔드포인트
   - 회원가입 페이지 : /members/join GET
   - 회원가입 : /members/join POST
2. 사용자 로그인 엔드포인트
    - 로그인 페이지 : /members/login GET
    - 로그인 : /members/login POST

---
### 데이터베이스 테이블 구조


---
### 구현한 API의 동작을 촬영한 데모 영상 링크


---
### 구현 방법 및 이유에 대한 간략한 설명


---
### API 명세(request/response 포함)
1. 사용자 회원가입

   | Method | End Point     | Header                          | Request Body                                                     | Response Body | status code & exception                                                                 |
   |--------|---------------|---------------------------------|------------------------------------------------------------------|---------------|-----------------------------------------------------------------------------------------|
   | POST   | /members/join | Content-Type : application/json | { "joinMail" : "test@naver.com" ,   "joinRawPw" :   "test1234" } | "success"     | BAD_REQUEST(400) : 이메일과 패스워드 유효성 검사가 실패한 경우, 이미 존재한 계정인 경우 |

2. 사용자 로그인

   | Method | End Point      | Header                          | Request Body                                                  | Response Body | status code & exception                                                                                         |
   |--------|----------------|---------------------------------|---------------------------------------------------------------|---------------|-----------------------------------------------------------------------------------------------------------------|
   | POST   | /members/login | Content-Type : application/json | { "memberId" : "test@naver.com" ,   "memberPw" : "test1234" } | jwt 문자열    | BAD_REQUEST(400) : 이메일과 패스워드 유효성 검사가 실패한 경우, 존재하지 않는 계정인 경우, 패스워드가 틀린 경우 |

---
