# wanted-pre-onboarding-backend
### 원티드 프리온보딩 8월 백엔드 인턴십 사전과제

---
### 인적사항
박성환

---
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
   - 게시글 조회 : /board/${boardId} (GET)
6. 특정 게시글을 수정하는 엔드포인트
   - 게시글 수정 페이지 : /board/modifyForm    (GET)
   - 게시글 수정 : /board   (PATCH)
7. 특정 게시글을 삭제하는 엔드포인트
   - 게시글 삭제 : /board   (DELETE)
---
### 데이터베이스 테이블 구조

<img width="617" alt="스크린샷 2023-08-06 오전 12 31 28" src="https://github.com/tjdghks1994/wanted-pre-onboarding-backend/assets/57320084/125433d0-0510-4971-8c6c-66214021fcd2">

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
