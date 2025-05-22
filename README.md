## 오늘의 운세는? - Fortune Teller

> 본 프로젝트는 클라우드 엔지니어링 부트캠프 3기의 1차 프로젝트, 6조가 수행한 결과물입니다.
> 
![프로젝트 이미지](https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5334%2F2024%2F09%2F19%2F0000239658_001_20240919101010510.png&type=sc960_832)



## 주요 내용 📋

- 프로젝트의 목적 및 기능
- 사용 기술
- 설치 및 실행 방법

## 프로젝트 목적 및 기능 ⭐

사용자의 이름과 생년월일을 입력받아, 
해당 정보를 바탕으로 간단한 운세를 제공하는 웹 어플리케이션입니다. 

운세 종류는 타로 및 십이지신 2가지가 제공되며, 
오늘의 운세를 확인해보고 QR코드를 통해 친구들과 운세를 나눠보세요!

## 사용 기술 🛠️

- Java
- Spring Boot
- MySQL
- Thymeleaf

## 설치 및 실행 방법 ⚙️

1. 프로젝트 클론
    
    ```bash
    git clone https://github.com/CLD-3rd/infra-team6.git
    ```
    
2. Properties 설정
    
    ```bash
    #application.properties
    
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://[IPAdress:Port]/fortune?serverTimezone=Asia/Seoul
    spring.datasource.username=
    spring.datasource.password=
    
    # JPA
    spring.jpa.generate-ddl=false
    spring.jpa.hibernate.ddl-auto=none
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.show-sql=true
    
    # Thymeleaf
    spring.thymeleaf.prefix=classpath:/templates/
    spring.thymeleaf.suffix=.html
    spring.thymeleaf.cache=false
    spring.thymeleaf.check-template-location=true
    ```
    
    ```bash
    #admin.properties
    
    #관리자 계정의 패스워드
    admin.account.password = 
    
    #운영중인 서버의 아이피:포트
    app.server.address = 
    
    #AES 암호화키 16자
    data.crypt.key = **
    ```
    
3. 실행
    
    ```bash
    ./deploy.sh
    
    ```
    

## 기여 방법 🤝

1. Fork 후 변경 사항을 커밋합니다.
2. Pull Request를 생성합니다.

## 참여자 👥

### 팀장:

- 문지현

### 부팀장:

- 한동연

### 팀원:

- 백주희
- 서인석
- 이예지
