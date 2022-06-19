# DoYouKnow
자랑스러운 한국인, 한국의 장소, 한국의 미디어 컨텐츠를 소개하는 페이지입니다.😀
![마지막](https://user-images.githubusercontent.com/87487149/174481308-f0ffa720-9505-4c1d-8e07-57cf808f8b30.gif)

## 😄이런 프로젝트입니다!
Spring, SpringBoot, JPA를 통한 웹페이지 서버 개발과 ***서버를 통한 REST API 통신*** 을 해보았어요!

## 🙋‍♂️ 이 프로젝트는 이걸 사용했어요!
### REST API
### PostMan
### Spring Framework 및 Spring Boot
### JPA
### 데이터베이스 - H2 Database
### 웹 페이지 - Thymeleaf

## 애플리케이션 아키텍처

## 도메인 모델

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

### 이전 프로젝트보다 나아진 점
- 많은 엔티티와 그에 따른 연관관계 매핑을하면서 많이 배웠다.
1. 다대일, 일대일 매핑 등을 하면서 cascade를 왜 쓰는지 많은 에러를 통해 몸소 느꼈다.
2. 상속관계 매핑에서 dtype을 지정하는 이유를 직접 알게되었다.  
- 부트스트랩을 쓰지 않고, jsp로 프론트엔드를 직접 구현하면서 좀 더 발전하였다.
- 아키텍처 부분 (controller, repository, service, domain) 설계가 전 보다는 익숙해진 것 같다.

### 문제점, 개선 사항
- 상속관계 매핑을 굳이 조인 전략으로 할 필요가 없었다. 다음부터 이런 상황에서는 싱글 테이블 전략을 사용해야겠다.
- 아직도 틀리는 부분이 많아 예전 코드를 참고하는 경우가 많다.
- JPA의 persist, remove 등은 할 수 있으나, 데이터를 다중으로 가져오거나, 조건적으로 가져올 때 JPQL 사용법을 전혀 모른다. -> JPA 공부한 후 다음 프로젝트를 할 예정
- 프론트엔드 영역에서 시간이 많이 소요되나, 그만큼 성과가 나오지 않는다. -> css 복습 필요
