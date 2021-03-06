# 유권자(`/vote`)
---
## 유권자 인증번호 로그인

메소드 | 경로 | 짧은 설명
--- | --- | ---
POST | /access | 유권자 인증번호 인증

### 요청 헤더
~~~
Content-Type: application/json
~~~

### 요청 바디
#### 설명
```json
{
    "code": "선관위로 부터 부여 받은 8자리 인증번호(영문소문자 + 숫자)"
}
```
#### 예
```json
{
    "code": "00000000"
}
```
### 응답 바디
#### 유권자가 투표 해야 할 리스트 및 해당 투표 후보자 리스트
```json
{
    "status": "SUCCESS",
    "data": {
        "jwt": "jwt 토큰값",
        "list": [
            {
                "voteId": 1,
                "voteName": "총학생회선거",
                "startTime": 1514764800000,
                "endTime": 1517389200000,
                "target": 1,
                "candidates": [
                    {
                        "candidateId": 1,
                        "campName": "선본1",
                        "leaderName": "회장1",
                        "leaderDeptName": "소프",
                        "subLeaderName": "부회장1",
                        "subLeaderDeptName": "소프",
                        "photo": "https://s3.ap-northeast-2.amazonaws.com/project-vote/%ED%94%BC%EC%B9%B4%EC%B8%84.jpg"
                    },
                    {
                        "candidateId": 2,
                        "campName": "선본2",
                        "leaderName": "회장2",
                        "leaderDeptName": "정통",
                        "subLeaderName": "부회장2",
                        "subLeaderDeptName": "정통",
                        "photo": "https://s3.ap-northeast-2.amazonaws.com/project-vote/%ED%94%BC%EC%B9%B4%EC%B8%84.jpg"
                    }
                ]
            },
            {
                "voteId": 2,
                "voteName": "IT학부선거",
                "startTime": 1514764800000,
                "endTime": 1517389200000,
                "target": 40,
                "candidates": [
                    {
                        "candidateId": 3,
                        "campName": "선본3",
                        "leaderName": "IT학부회장1",
                        "leaderDeptName": "소프",
                        "subLeaderName": "IT학부부회장1",
                        "subLeaderDeptName": "소프",
                        "photo": "https://s3.ap-northeast-2.amazonaws.com/project-vote/%ED%94%BC%EC%B9%B4%EC%B8%84.jpg"
                    },
                    {
                        "candidateId": 4,
                        "campName": "선본4",
                        "leaderName": "IT학부회장2",
                        "leaderDeptName": "글티",
                        "subLeaderName": "IT학부부회장1",
                        "subLeaderDeptName": "글티",
                        "photo": "https://s3.ap-northeast-2.amazonaws.com/project-vote/%ED%94%BC%EC%B9%B4%EC%B8%84.jpg"
                    }
                ]
            },
            {
                "voteId": 3,
                "voteName": "소프트웨어공학전공선거",
                "startTime": 1514764800000,
                "endTime": 1517389200000,
                "target": 42,
                "candidates": [
                    {
                        "candidateId": 5,
                        "campName": "선본5",
                        "leaderName": "소프대표1",
                        "leaderDeptName": "소프",
                        "subLeaderName": "소프부대표1",
                        "subLeaderDeptName": "소프",
                        "photo": "https://s3.ap-northeast-2.amazonaws.com/project-vote/%ED%94%BC%EC%B9%B4%EC%B8%84.jpg"
                    },
                    {
                        "candidateId": 6,
                        "campName": "선본6",
                        "leaderName": "소프대표2",
                        "leaderDeptName": "소프",
                        "subLeaderName": "소프부대표2",
                        "subLeaderDeptName": "소프",
                        "photo": "https://s3.ap-northeast-2.amazonaws.com/project-vote/%ED%94%BC%EC%B9%B4%EC%B8%84.jpg"
                    }
                ]
            }
        ]
    },
    "msg": "투표 및 후보자 리스트"
}
 ```
#### 중복 로그인 시도
  ```json
 {
     "status": "FAIL",
     "data": null,
     "msg": "현재 로그인 중입니다."
 }
 ```
#### 인증번호 인증 실패
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "등록된 인증번호가 아닙니다."
}
```
#### 투표 및 후보자 리스트 없음
```json
{
    "status": "FAIL",
    "data": null,
    "msg": "투표 및 후보자 리스트가 없습니다."
}
```
#### 이미 투표를 진행 한 경우
```json
{
    "status": "FAIL",
    "data": null,
    "msg": "이미 투표를 진행했습니다."
}
```
---
## 투표

메소드 | 경로 | 짧은 설명
--- | --- | ---
POST | / | 투표

### 요청 헤더
~~~
Content-Type: application/json
Authorization : JWT 토큰값
~~~

### 요청 바디
#### 설명
```json
{
    "code": "선관위로 부터 부여 받은 8자리 인증번호(영문소문자 + 숫자)",
    "voteList" : [
    		{
    			"voteId" : "투표 고유 번호(자연수의 Integer)",
    			"candidateId" : "투표 고유 번호(자연수의 Integer)"
    		},
    		{
                "voteId" : "투표 고유 번호(자연수의 Integer)",
                "candidateId" : "투표 고유 번호(자연수의 Integer)"
            },
    		{
                "voteId" : "투표 고유 번호(자연수의 Integer)",
                "candidateId" : "투표 고유 번호(자연수의 Integer)"
            }
    ]
}
```
#### 예
```json
{
	"code" : "00000000",
	"voteList" : [
		{
			"voteId" : 1,
			"candidateId" : 1
		},
		{
			"voteId" : 2,
			"candidateId" : 3
		},
		{
			"voteId" : 3,
			"candidateId" : 7
		}
	]
}
```
### 응답 바디
#### 투표 성공
```json
{
    "status": "SUCCESS",
    "data": null,
    "msg": "투표가 성공적으로 끝났습니다."
}
 ```
#### 잘못된 접근
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "해당 인증코드는 사용하실 수 없습니다."
}
```
#### 인증코드 오류
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "존재하지 않는 인증코드 입니다."
}
```
#### 이미 투표를 진행한 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "이미 투표를 진행했습니다."
}
```
#### 빈 투표 값
```json
{
    "status": "FAIL",
    "data": null,
    "msg": "투표 값이 없습니다."
}
```
#### 유효하지 않은 투표 값
```json
{
    "status": "FAIL",
    "data": null,
    "msg": "유효하지 않은 투표 값 입니다."
}
```
#### 토큰 값 오류
```json
{
    "status": "FAIL",
    "data": null,
    "msg": "session-token-error"
}
```
---

