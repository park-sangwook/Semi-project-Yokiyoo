## 1. 개인프로젝트-요기요(벤치마킹)

## 2. 개발환경
+ Front : HTML
+ Back : SpringBoot
+ ORM : Mybatis
+ IDE : Eclipse(Maven)
## 3. 개발기간(8.19 ~ 8.30)

## 4. 주요 기능
### [메인페이지]
+ 카카오 API 지도기능을 사용하여 현재위치 출력



### [로그인]
+ 카카오 로그인 API를 이용한 간편 로그인
+ 이메일 인증하는 JavaMailSender를 이용한 비밀번호 인증



### [메뉴]
+ swiper를 이용한 애니메이션 처리
```
let swiper = new Swiper(".mySwiper", {
  slidesPerView: 4,
  slidesPerGroupSkip: 1,
  keyboard: {
    enabled: true,
  },
  scrollbar: {
    el: ".swiper-scrollbar",
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});
```
![chromeMenu](https://github.com/user-attachments/assets/e9fbce44-292d-4459-85f4-6e98da863571)


## 5. 결과
### 


## 6. 후기
- 좋은점
  완벽히 요기요를 벤치마킹한것은 아니지만 개인프로젝트로 연습하기 좋았던 프로젝트였습니다.
  이걸 통해 MVC패턴에 대해 이해하기 좋았고, 스프링부트의 편의성에대해 이해한 프로젝트였습니다.

- 아쉬운점
  결제시스템을 도입하여 프로젝트의 완성도를 높이고자 하였지만 난이도가 어려워 도입하지 못한점이 아쉬웠습니다.
  메인페이지에서 검색버튼을 눌렀을때 주소를 입력하는 폼이 나오는데,
  실제 요기요 서비스에서는 주소를 입력하는 input태그에서 비동기로 관련된 주소를 보여주는 기술을 적용시키지 못한게 아쉬웠습니다.
  
  <img src="https://github.com/user-attachments/assets/fc64ee82-3702-410e-abab-a4e091ce5385" />
