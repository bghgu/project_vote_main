# project_vote_main

성공회대학교 전자 투표 시스템

선거관리 위원회, 유권자

#### url : bghgu.tk/main

#### api : https://github.com/bghgu/project_vote_main/wiki

## 1. 시작하기

모든 소스코드는 IntelliJ + Window10 + JAVA 8 환경에서 작성되었습니다.

이 프로젝트에서는 아래 같은 **의존성 프로젝트**가 포함되어있습니다. 

**pom.xml** 파일에 아래와 같이 **의존성 프로젝트**를 추가해 주세요.

```
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-tomcat</artifactId>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
    <dependency>
		<groupId>org.apache.tomcat.embed</groupId>
         <artifactId>tomcat-embed-jasper</artifactId>
         <version>8.5.20</version>
    </dependency>
    <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-core</artifactId>
    </dependency>
    <!-- JWT -->
    <dependency>
         <groupId>io.jsonwebtoken</groupId>
         <artifactId>jjwt</artifactId>
         <version>0.9.0</version>
    </dependency>
    <!-- redis -->
    <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <dependency>
         <groupId>redis.clients</groupId>
         <artifactId>jedis</artifactId>
	</dependency>
</dependencies>
```
## 실행하기

window 10 환경

- `jdk8` 과 `maven` 을 설치합니다.
- `JAVA_JOME` 환경변수 설정을 합니다.
- `Path`에 `maven` 환경변수 설정을 합니다.spring boot 앱 실행

```
mvn spring-boot:run
```

- 중지하려면, 키보드에서 `Crtl + C`를 누릅니다.
- `application.properties` 파일이 필요합니다.

AWS EC2 Ubuntu 환경

- `jdk8` 과 `maven` 을 설치합니다.

백 그라운드 spring boot 앱 실행

```
nohup mvn spring-boot:run&
```

- 중지하려면,  `netstat -tnlp` 명령어를 통해 프로세스를 kill 하십시오.
- `application.properties` 파일이 필요합니다.

## 배포

AWS EC2

AWS RDS MySql

## 사용된 도구

* [Spring-boot](https://projects.spring.io/spring-boot/) - Spring-boot 웹 프레임워크
* [Maven](https://maven.apache.org/) - 의존성 관리 프로그램
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE
* [MySql](https://www.mysql.com/) - DataBase
* [Redis](https://redis.io/) - DataBase

## 저자

* **배다슬** - [bghgu](https://github.com/bghgu)


[기여자 목록](https://github.com/bghgu/project_vote_main/contributors)을 확인하여 이 프로젝트에 참가하신 분들을 보실 수 있습니다.

## 감사 인사

* 본인의 코드가 사용된 분께 경의를 표합니다
* 영감
* etc (등)

---


