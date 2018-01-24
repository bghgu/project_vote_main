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
    "code": "선관위로 부터 부여 받은 6자리 인증번호"
}
```
#### 예
```json
{
    "code": "420000"
}
```
### 응답 바디
#### 유권자가 투표 해야 할 리스트 및 해당 투표 후보자 리스트
```json
{
    "voteList": [
        {
            "voteId": 1,
            "voteName": "총학생회장 투표",
            "startTime": 1514732400000,
            "endTime": 1517324400000,
            "target": 1,
            "candidates": [
                {
                    "candidateId": 1,
                    "name": "학생1",
                    "departmentName": "소프트웨어공학과",
                    "position": "총학생회장",
                    "campName": "선본이름",
                    "photoUrl": "사진URl"
                },
                {
                    "candidateId": 2,
                    "name": "학생2",
                    "departmentName": "정보통신공학과",
                    "position": "부총학생회장",
                    "campName": "선본이름",
                    "photoUrl": "사진URL"
                }
            ]
        },
        {
            "voteId": 2,
            "voteName": "IT학부회장 투표",
            "startTime": 1514732400000,
            "endTime": 1517324400000,
            "target": 40,
            "candidates": [
                {
                    "candidateId": 3,
                    "name": "학생3",
                    "departmentName": "컴퓨터공학과",
                    "position": "IT학부회장",
                    "campName": "선본이름",
                    "photoUrl": "사진URL"
                },
                {
                    "candidateId": 4,
                    "name": "학생4",
                    "departmentName": "글로컬IT학과",
                    "position": "IT학부회장",
                    "campName": "선본이름",
                    "photoUrl": "사진URL"
                }
            ]
        },
        {
            "voteId": 3,
            "voteName": "소프트웨어공학전공대표 투표",
            "startTime": 1514732400000,
            "endTime": 1517324400000,
            "target": 42,
            "candidates": [
                {
                    "candidateId": 5,
                    "name": "학생5",
                    "departmentName": "소프트웨어공학과",
                    "position": "전공대표",
                    "campName": "선본이름",
                    "photoUrl": "사진URL"
                },
                {
                    "candidateId": 6,
                    "name": "학생6",
                    "departmentName": "소프트웨어공학과",
                    "position": "전공대표",
                    "campName": "선본이름",
                    "photoUrl": "사진URL"
                }
            ]
        }
    ],
    "message": "SUCCESS"
}
 ```
#### 유권자 인증 실패
 ```json
{
    "message": "FAIL"
}
  ```
---
