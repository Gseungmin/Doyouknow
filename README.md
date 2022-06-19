# DoYouKnow
자랑스러운 한국인, 한국의 장소, 한국의 미디어 컨텐츠를 소개하는 페이지입니다.😀
![마지막](https://user-images.githubusercontent.com/87487149/174481308-f0ffa720-9505-4c1d-8e07-57cf808f8b30.gif)

## 😄이런 프로젝트입니다!
Spring, SpringBoot, JPA를 통한 웹페이지 서버 개발과 ***서버를 통한 REST API 통신*** 을 위한 프로젝트입니다! 😸

## 🙋‍♂️ 이 프로젝트는 이걸 사용했어요!
### REST API
### PostMan
### Spring Framework 및 Spring Boot
### JPA
### 데이터베이스 - H2 Database
### 웹 페이지 - Thymeleaf

## 도메인 모델
![dwqdwqfew](https://user-images.githubusercontent.com/87487149/174481952-4cbea335-261d-4cc7-9ae1-8ee577f1a848.png)

## 😸이렇게 구현 했어요!
- ### VALIDATION
  - #### 타입 오류와 데이터 중복 문제를 해결해줬어요!
  - ![Validation 회원 가입_4](https://user-images.githubusercontent.com/87487149/174477404-599df322-3495-48a6-9e63-c7a3646323ba.gif)
  - 로그인과 게시물 업로드도 같은 방식으로 타입 검증이 이루어집니다!
- ### MULTIPART/FORM-DATA
  - #### 게시물에 정보뿐 아니라 이미지까지 함께 등록할 수 있어요!
  - ![첨부파일](https://user-images.githubusercontent.com/87487149/174478716-bfff709c-0284-4325-bed0-e63313651e73.gif)
- ### Cookie, Session
  - #### HttpRequestMethod가 제공하는 Session기능을 통해 로그인 상태를 유지 시켜줬어요!
  - ![쿠키세션](https://user-images.githubusercontent.com/87487149/174479073-6607e5f3-7313-44e0-8815-c4f67a0c30a0.gif)
- ### SORT
  - #### 게시물 정렬을 통해 좋아요가 가장 많은 게시물이 표지로 가요!
  - ![정렬](https://user-images.githubusercontent.com/87487149/174479553-7d9d4ce6-bdc2-4d78-9ed7-43edc3a01398.gif)
  - 사용자 해당 게시물에 좋아요나 싫어요 하나만 누를 수 있도록 구현해줬어요!
- ### ExceptionHandle
  - #### Spring Boot가 제공하는 BasicErrorController를 사용했어요!
  - ![에러](https://user-images.githubusercontent.com/87487149/174480273-f46dd2ca-d12b-4b85-b626-40ee2dd46aa9.gif)
  - 해당 view만 만들고 경로에 정해주면 따로 Error 처리 컨트롤러를 만들지 않아요!
- ### 그 외
  - 게시물에 댓글을 달고 간단하게 삭제, 백업이 가능하게 해줬어요!
  - ![Validation 회원 가입](https://user-images.githubusercontent.com/87487149/174480693-f679be47-00c4-429d-93c3-f8d264db165d.gif)

# 😸 REST API 이렇게 했어요!
- ### REST API 성능최적화
  - #### 모든 객체의 연관관계는 Lazy Loading! (N+1문제를 막아줘요!)
  - #### API 스펙에 맞춘 DTO를 만들어줬어요! (재사용성과 API스펙을 명확히 해줘요!)
  - #### ToOne 관계에 대해 Fetch join을 사용했어요! (쿼리의 효율을 높여주고 페이징도 사용할 수 있어요!)
  - #### BatchSize를 사용해줬어요!(컬렉션 연관관계에서 쿼리 효율을 높여줘요!)
  - #### 예외 처리는 @ExceptionHandler를 사용했어요!
- ### REST API 통신 예시
  - #### Postman을 통해 클라이언트에서 서버로 데이터 요청을 해요!
  - #### Request1: VIP가 올린 게시물을 조회해주세요!
  - - ##### @Getmapping(/member/vip/posts) 요청에 vip 이름과 게시물의 제목, 내용 데이터로 응답해요!
  - - - ![REST1](https://user-images.githubusercontent.com/87487149/174484060-8fe5adb7-42ef-40d5-8371-24918f5840ba.gif)
  - #### Request2: 좋아요가 2개 이상인 게시물을 조회해주세요!
  - - ##### @Getmapping(/posts/likes) 요청에 게시물의 제목, 내용 데이터로 응답해요. count를 변수로 받아 기준이 되는 좋아요 수를 정해줘요! (단 count의 기본 값은 0이에요!)
  - - - ![REST2](https://user-images.githubusercontent.com/87487149/174484061-940ca7a8-636d-41da-80b1-d9d0ce6ffe41.gif)

## 프로젝트 실행 시 주의사항
![Validation 회원 가입_2](https://user-images.githubusercontent.com/87487149/174486591-14d8148f-3c77-4986-8ff8-2dd7db5cb3bb.gif)
 - #### 1. ddl-auto를 create로 바꾼 후 먼저 한번 실행해야 이유: Board 엔티티에 대해 People보드 Place보드 Video보드를 init데이터로 만들어주는데 이는 Board의 생성은 오직 관리자만 해주게 하기 위함입니다. 단 재시작했을때 게시물 데이터가 하나도 없으므로 화면이 비어보이는 것에 대해서는 데이터를 추가하면 해결될 문제이므로 걱정하지 않아도 됩니다!
 - #### 2. ddl-auto를 none으로 바꾼 후 재실행하는 이유: create로 ddl-auto를 두면 재 시작 할때마다 모든 데이터가 초기화되기 때문입니다!
 - #### 3. file.dir을 자신의 경로에 맞게 바꿔야하는 이유: 첨부파일로 올라가는 이미지는 로컬 내 경로에 저장되고 그 경로를 통해 탐색됩니다! 따라서 자신의 로컬에 맞는 경로 설정이 중요합니다!

## 😿 프로젝트를 마치며
#### 해당 프로젝트는 ***REST API 통신 성능최적화 및 Spring과 JPA의 장점과 사용시 주의사항***에 대해 어떤 상황에 어떤 코드를 짜야하고 해당 코드에 문제가 발생했을 때 어떻게 대처해야 하는지 공부하기 위한 목적의 프로젝트입니다! 따라서 다른 개발자분들에게 도움을 줄 수 있는 그런 프로젝트는 아니겠지만 코드에 대한 이해도가 단순히 복습만 했을때보다 훨씬 더 높아졌으며 저에게 앞으로 진행할 프로젝트에 대한 방향성을 제시해준 프로젝트입니다. 첫 프로젝트라 더 틀리고 더 돌아갔던것 같습니다. 하지만 틀렸고 돌아갔다는 것은 결국 제 코드에 성장이 일어났다고 생각합니다. 다음에는 더 좋고 유익한 정보를 제공할 수 있는 프로젝트를 제공하겠습니다.

