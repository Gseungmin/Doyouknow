# 😸DoYouKnow
자랑스러운 한국인, 한국의 장소, 한국의 미디어 컨텐츠를 소개하는 페이지😀
![마지막](https://user-images.githubusercontent.com/87487149/174481308-f0ffa720-9505-4c1d-8e07-57cf808f8b30.gif)

## 😀프로젝트의 목표
### Spring과 Jpa에 대한 기본적인 지식 점검 및 활용

## 😸프로젝트 사용 기술
### Spring Framework 및 Spring Boot
### ORM - Jpa 및 SpringDataJpa
### 데이터베이스 - H2 Database
### View - Thymeleaf

## 😀도메인 모델
![dwqdwqfew](https://user-images.githubusercontent.com/87487149/174481952-4cbea335-261d-4cc7-9ae1-8ee577f1a848.png)

## 😸프로젝트 설계
- ### VALIDATION
  - #### 타입 오류와 데이터 중복 문제를 해결
  - ![Validation 회원 가입_4](https://user-images.githubusercontent.com/87487149/174477404-599df322-3495-48a6-9e63-c7a3646323ba.gif)
  - 로그인과 게시물 업로드도 같은 방식으로 타입 검증
- ### MULTIPART/FORM-DATA
  - #### 게시물에 정보뿐 아니라 이미지까지 함께 등록
  - ![첨부파일](https://user-images.githubusercontent.com/87487149/174478716-bfff709c-0284-4325-bed0-e63313651e73.gif)
- ### COOKIE, SESSION
  - #### HttpRequestMethod가 제공하는 Session기능을 통해 로그인 상태를 유지
  - ![쿠키세션](https://user-images.githubusercontent.com/87487149/174479073-6607e5f3-7313-44e0-8815-c4f67a0c30a0.gif)
- ### SORT
  - #### 게시물 정렬을 통해 좋아요가 가장 많은 게시물이 표지로 이동
  - ![정렬](https://user-images.githubusercontent.com/87487149/174479553-7d9d4ce6-bdc2-4d78-9ed7-43edc3a01398.gif)
  - 사용자 해당 게시물에 좋아요나 싫어요 하나만 누를 수 있도록 구현
- ### EXCEPTIONHANDLE
  - #### Spring Boot가 제공하는 BasicErrorController를 사용
  - ![에러](https://user-images.githubusercontent.com/87487149/174480273-f46dd2ca-d12b-4b85-b626-40ee2dd46aa9.gif)
  - 해당 view만 만들고 경로에 정해주면 따로 Error 처리 컨트롤러를 생성 X
- ### 그 외
  - 게시물에 댓글을 달고 간단하게 삭제, 백업이 가능
  - ![Validation 회원 가입](https://user-images.githubusercontent.com/87487149/174480693-f679be47-00c4-429d-93c3-f8d264db165d.gif)

## 😿 프로젝트를 진행하면서 아쉬웠던 부분
  - ### 1. Entity에 대한 Setter를 Public으로 열어두었다.
    - #### 프로젝트의 크기가 커질수록 Entity의 변경 포인트를 예측하기 어려워질 수 있다. 
  - ### 2. JPA 성능 최적화 기능을 제대로 활용하지 못했다.
    - #### 연관관계를 지연로딩으로 설정하였지만 Fetch Join이나 별도의 DTO를 사용하지 않았으므로 JPA 성능 최적화 문제에서 최적의 효과를 가져오진 못하였다.
