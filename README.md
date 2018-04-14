# project_vote_main

**당신의 회장에게 투표하세요**

블록체인을 이용한 전자 투표 시스템 

* 마이크로 서비스 아키텍쳐, 3개의 서비스 서버로 구성

선거 및 선관위 관리 시스템 / **투표 시스템** / 선거 정보 조회 시스템

프로젝트 기간 : 2018년 겨울 방학

맡은 역할 : PM, DB 설계, 투표 정보 조회 시스템 개발, 투표 시스템 개발

[투표 시스템 작품제안서](https://github.com/bghgu/project_vote_main/blob/master/%ED%88%AC%ED%91%9C%20%EC%8B%9C%EC%8A%A4%ED%85%9C_%EC%9E%91%ED%92%88%EC%A0%9C%EC%95%88%EC%84%9C.pdf) - 작품 제안서

API : https://github.com/bghgu/project_vote_main/wiki

![db.png](https://github.com/bghgu/project_vote_main/blob/master/image/db.png)

* ER 다이어그램

## 구성

![1.png](https://github.com/bghgu/project_vote_main/blob/master/image/1.png)

* 전체 시스템 구성도

![2.png](https://github.com/bghgu/project_vote_main/blob/master/image/2.png)

* 투표 시스템 구성도
* 기능
  * 선관위가 유권자 확인
  * 선관위가 유권자에게 인증번호 발급
  * 유권자는 인증번호로 로그인
  * 투표

![blockChain1.png](https://github.com/bghgu/project_vote_main/blob/master/image/blockChain1.png)

* 블록 체인 구성도

```
import com.skhu.vote.utils.SHA512EncryptUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by ds on 2018-02-04.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockHeader implements Serializable {

    private static final long serialVersionUID = -597819263112668252L;

    //이전블록 해쉬값
    private String preBlockHash;
    //현재 블록 해쉬값 = 리스트의 키 값
    private String merkleHash;
    //블록 생성 시간
    private Date createBlockTime;
    //현재 블록 바디 해쉬값
    private String blockHash;
    //블록 바디 값
    private BlockBody blockBody;

    public BlockHeader(final BlockBody blockBody, final String preBlockHash) {
        this.preBlockHash = preBlockHash;
        this.blockHash = blockBody.hash();
        this.blockBody = blockBody;
        this.createBlockTime = new Date();
        this.setMerkleHash();
    }

    private void setMerkleHash() {
        if(merkleHash == null)
            this.merkleHash = SHA512EncryptUtils.encrypt(blockHash);
    }

}
```

* 블록 헤더

```
import com.skhu.vote.domain.BLOCKCHAIN;
import com.skhu.vote.model.Req.CandidateReq;
import com.skhu.vote.utils.BlockHashUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ds on 2018-02-06.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockBody implements Serializable{

    private static final long serialVersionUID = -6156036100875586824L;

    //투표 시간
    private Date voteTime;
    //투표 값
    private int candidateId;
    //투표 종류
    private int voteId;
    //투표한 인증코드
    private String authCode;

    public BlockBody(final CandidateReq candidateReq, final String code) {
        this.voteTime = new Date();
        this.candidateId = candidateReq.getCandidateId();
        this.voteId = candidateReq.getVoteId();
        this.authCode = code;
    }

    public BlockBody(final BLOCKCHAIN blockchain) {
        this.voteTime = blockchain.getVoteTime();
        this.candidateId = blockchain.getCandidateId();
        this.voteId = blockchain.getVoteId();
        this.authCode = blockchain.getAuthCode();
    }

    public String hash() {
        return BlockHashUtils.hashCode(this.candidateId, this.voteId, this.authCode);
    }
}
```

* 블록 바디

```
import com.skhu.vote.model.BlockHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ds on 2018-02-04.
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BLOCKCHAIN implements Serializable {
    private static final long serialVersionUID = -5893925423553544422L;
    //블록 헤더
    //이전블록 해쉬값
    private String preBlockHash;
    //현재 블록 해쉬값
    private String merkleHash;
    //블록 생성 시간
    private Date createBlockTime;
    //현재 블록 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blockId;
    //현재 블록 바디 해쉬값
    private String blockHash;
    //블록 바디
    //투표 시간
    private Date voteTime;
    //투표 값
    private int candidateId;
    //투표 종류
    private int voteId;
    //투표한 인증코드
    private String authCode;
    public BLOCKCHAIN(final BlockHeader blockHeader) {
        this.preBlockHash = blockHeader.getPreBlockHash();
        this.merkleHash = blockHeader.getMerkleHash();
        this.createBlockTime = blockHeader.getCreateBlockTime();
        this.blockHash = blockHeader.getBlockHash();
        this.voteTime = blockHeader.getBlockBody().getVoteTime();
        this.candidateId = blockHeader.getBlockBody().getCandidateId();
        this.voteId = blockHeader.getBlockBody().getVoteId();
        this.authCode = blockHeader.getBlockBody().getAuthCode();
    }
}
```

* 블록 체인

## 시작하기

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
- `Path`에 `maven` 환경변수 설정을 합니다.
- 내장 톰캣을 이용해 서버를 배포 합니다.
- spring boot 앱 실행
- `application.properties` 파일이 필요합니다.

```
mvn spring-boot:run
```

- 중지하려면, 키보드에서 `Crtl + C`를 누릅니다.
- `application.properties` 파일이 필요합니다.

AWS EC2 Ubuntu 환경

- `jdk8` 과 `maven` 을 설치합니다.
- 백 그라운드 spring boot 앱 실행
- 내장 톰캣을 사용해 배포합니다.

```
nohup mvn spring-boot:run&
```

- 중지하려면,  `netstat -tnlp` 명령어를 통해 프로세스를 kill 하십시오.

## 배포

* AWS EC2 - 애플리케이션 서버
* AWS RDS - Mysql DB 서버
* AWS ElastiCache - 인 메모리 데이터 서버

## 사용된 도구

* [Spring-boot](https://projects.spring.io/spring-boot/) - Spring-boot 웹 프레임워크
* [Maven](https://maven.apache.org/) - 의존성 관리 프로그램
* [Tomcat](http://tomcat.apache.org/) - 웹 애플리케이션 서버
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE
* [MySql](https://www.mysql.com/) - DataBase
* [Redis](https://redis.io/) - DataBase
* [AWS EC2](https://aws.amazon.com/ko/ec2/?sc_channel=PS&sc_campaign=acquisition_KR&sc_publisher=google&sc_medium=english_ec2_b&sc_content=ec2_e&sc_detail=aws%20ec2&sc_category=ec2&sc_segment=177228231544&sc_matchtype=e&sc_country=KR&s_kwcid=AL!4422!3!177228231544!e!!g!!aws%20ec2&ef_id=WkRozwAAAnO-lPWy:20180412120123:s) - 클라우드 환경 컴퓨팅 시스템
* [AWS RDS](https://aws.amazon.com/ko/rds/) - 클라우드 환경 데이터베이스 관리 시스템
* [AWS ElastiCache](https://aws.amazon.com/ko/elasticache/) - 클라우드 환경 인 메모리 데이터 스토어

## 저자

* **배다슬** - [bghgu](https://github.com/bghgu)


[기여자 목록](https://github.com/bghgu/project_vote_main/graphs/contributors)을 확인하여 이 프로젝트에 참가하신 분들을 보실 수 있습니다.

## 감사 인사

* **이승기** - [sjaqjwor](https://github.com/sjaqjwor)
* **JongMin Kim** - [devetude](https://github.com/devetude)
* [블록체인 한번에 이해하기](https://homoefficio.github.io/2017/11/19/%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8-%ED%95%9C-%EB%B2%88%EC%97%90-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/)
* [Java 코드로 이해하는 블록체인](http://www.popit.kr/java-%EC%BD%94%EB%93%9C%EB%A1%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EB%8A%94-%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8blockchain/)

## 수상

- 2018년 제 10회 소프트웨어공학과 경진대회 자유주제 부분 1위

---


