# wanted-pre-onboarding-backend
### 원티드 프리온보딩 8월 백엔드 인턴십 사전과제

---
### 인적사항
박성환

---
### AWS 배포
AWS에 배포한 애플리케이션 주소 : http://43.201.186.149/members/login

<img width="300" alt="스크린샷 2023-08-06 오후 5 22 18" src="https://github.com/tjdghks1994/wanted-pre-onboarding-backend/assets/57320084/d8e3fcba-2e3e-4ddb-ba5b-b81673f4d75f">

### 애플리케이션 실행 방법
1. 사용자 회원가입 엔드포인트
   - 회원가입 페이지 : /members/join (GET)
   - 회원가입 : /members/join (POST)
2. 사용자 로그인 엔드포인트
    - 로그인 페이지 : /members/login (GET)
    - 로그인 : /members/login (POST)
3. 새로운 게시글을 생성하는 엔드포인트
   - 게시글 등록 페이지 : /board/addForm (GET)
   - 게시글 등록 : /board (POST)
4. 게시글 목록을 조회하는 엔드포인트
   - 게시글 목록 페이지 : /board/list (GET)
   - 게시글 목록 : /board (GET)
5. 특정 게시글을 조회하는 엔드포인트
   - 게시글 조회 페이지 : /board/lookup (GET)
   - 게시글 조회 : /board/{boardId} (GET)
6. 특정 게시글을 수정하는 엔드포인트
   - 게시글 수정 페이지 : /board/modifyForm    (GET)
   - 게시글 수정 : /board   (PATCH)
7. 특정 게시글을 삭제하는 엔드포인트
   - 게시글 삭제 : /board/{boardId}   (DELETE)
---
### 데이터베이스 테이블 구조

<img width="617" alt="스크린샷 2023-08-06 오전 12 31 28" src="https://github.com/tjdghks1994/wanted-pre-onboarding-backend/assets/57320084/125433d0-0510-4971-8c6c-66214021fcd2">

---
### 구현한 API의 동작을 촬영한 데모 영상 링크

https://youtu.be/1E3XtL-hg2c

---
### 구현 방법 및 이유에 대한 간략한 설명
1. 회원가입시 요구사항인 검증처리를 위해 검증로직을 편리하게 적용할 수 있는 스프링이 제공하는
   BindingResult와 BeanValidation을 활용하였습니다.
2. 회원가입시 사용자 패스워드를 암호화하여 저장하기 위해 스프링 시큐리티에서 제공하는
   PasswordEncoder 구현체인 BCryptPasswordEncoder를 활용하여 단방향 암호화방식으로
   암호화된 패스워드를 DB에 저장하도록 구현했습니다. <br>
   양방향 암호화방식일 경우 복호화키를 알면 암호화된 패스워드를 평문으로 복호화 할 수 있기 때문에,
   양방향 암호화방식이 아닌 단방향 암호화방식을 채택하였습니다.
3. 사용자 로그인 성공 시 JWT 토큰을 반환하기 위해 jsonwebtoken 라이브러리를 활용했습니다.
4. 사용자 로그인 시 요구사항인 검증처리를 위해서도 회원가입과 동일하게 스프링이 제공하는
   BindingResult와 BeanValidation을 활용하였습니다.
5. jwt방식을 사용하기 위해 스프링 시큐리티의 설정을 변경하였는데, 
   form로그인 방식 비활성화, http basic 방식 비활성화, 세션 비활성화 설정하였습니다.
6. 로그인 이후 게시글과 관련된 기능을 사용하기 위해서는 Authorization 헤더에 jwt토큰이 유효한지
   확인하기 위한 Filter를 추가했습니다.
7. REST API에서 발생하는 예외를 한 곳에서 처리하기 위해 @RestControllerAdvice 와 
   @ExceptionHandler 를 활용한 클래스를 사용했습니다.
8. DB 접속정보를 암호화 하기 위해 Jasypt 라이브러리를 활용하였으며, 해당 암호화 방식은 양방향
   암호화 방식이므로, 암호화에 필요한 Key가 있어야 했으며, 해당 Key를 소스코드에 보관할 수 없어 
   배포한 AWS EC2 서버에 환경변수로 등록하여 사용하도록 했다.

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

3. 새로운 게시글 생성

   | Method | End Point | Header                                                           | Request Body                                                                                  | Response Body                  | status code & exception                                 |
   |--------|-----------|-------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|--------------------------------|---------------------------------------------------------|
   | POST   | /board    | Content-Type : application/json Authorization : Bearer 로그인 생성 시 반환된 jwt문자열  | { "boardTitle": "게시글 등록 테스트 입니다" , "boardContents": "<p> 게시글 내용 작성 </p>" }  | 게시글 등록이 완료 되었습니다. | BAD_REQUEST(400) : 사용자가 존재하지 않는 사용자인 경우 |

4. 게시글 목록 조회

   | Method | End Point | Header                                                                     | Request Body    | Response Body                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | status code & exception |
   |--------|----------------------------------------------------------------------------|-------------------------------------------------------------------|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|
   | GET    | /board    | Content-Type : application/json Authorization : Bearer 로그인 생성 시 반환된 jwt문자열 | { "pageNum":2 } | [      {         "boardNum":3 ,         "boardId":12 ,         "boardTitle":"작성!!" ,         "modifyDate":"2023-08-05" ,        "lookupCnt":0 ,         "memberId":"parktjdghks@naver.com"     },      {         "boardNum":2 ,         "boardId":11 ,         "boardTitle":"작성작성" ,         "modifyDate": "2023-08-05" ,         "lookupCnt":1 , "memberId":"parktjdghks@naver.com" },      { "boardNum":1 , "boardId":5 ,         "boardTitle": "게시글 등록 테스트 입니다" ,         "modifyDate": "2023-08-04" ,         "lookupCnt":2 ,         "memberId": "parktjdghks@naver.com" } ] |                         |

5. 특정 게시글 조회

   | Method | End Point        | Header                           | Request Body | Response Body                                                                                                                                                                                                     | status code & exception                        |
   |--------|------------------|----------------------------------|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------|
   | GET    | /board/{boardId} | Authorization : Bearer 로그인 생성 시 반환된 jwt문자열 |              | {     "boardId": 37 ,     "boardTitle":"게시글 수정 테스트 POSTMAN" , "boardContents": "<p>게시글 수정ㅎㅎ</p>" ,     "modifyDate": "2023-08-05" ,     "lookupCnt": 30 ,     "memberId":"parktjdghks@naver.com" } | BAD_REQUEST(400) : 게시글이 존재하지 않는 경우 |

6. 특정 게시글 수정

   | Method | End Point | Header                                                           | Request Body                                                                     | Response Body    | status code & exception                                             |
   |--------|-----------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|------------------|---------------------------------------------------------------------|
   | PATCH  | /board    | Content-Type : application/json Authorization : Bearer 로그인 생성 시 반환된 jwt문자열 | {"boardId":"11", "boardTitle":"게시글 수정 테스트 POSTMAN", "boardContents":"게시글 수정ㅎㅎ" } | 수정 성공 메시지 | BAD_REQUEST(400) : 작성자 ID와 수정하려는 ID(로그인 ID)가 다른 경우 |

7. 특정 게시글 삭제

   | Method | End Point | Header                                                           | Request Body                                                           | Response Body    | status code & exception                                             |
   |--------|-----------|-----------------------------------------------------------------|-------------------------------------------------------------------------|------------------|---------------------------------------------------------------------|
   | DELETE | /board/{boardId}    | Content-Type : application/json Authorization : Bearer 로그인 생성 시 반환된 jwt문자열 | | 삭제 성공 메시지 | BAD_REQUEST(400) : 작성자 ID와 삭제하려는 ID(로그인 ID)가 다른 경우 |

---
