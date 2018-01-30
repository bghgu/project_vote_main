# 선거 관리 위원회(`/emc`)
---
## 선관위 로그인 (미구현)

메소드 | 경로 | 짧은 설명
--- | --- | ---
POST | /login | 선관위 로그인

### 요청 헤더
~~~
Content-Type : application/json
~~~

### 요청 바디
#### 설명
```json
{
    "id" : "선관위 학번",
    "password" : "비밀번호"
}
```
#### 예
```json
{
    "id" : "201232016",
    "password" : "1234"
}
```
### 응답 바디
#### 로그인 성공
```json
{
    "status": "SUCCESS",
    "data": "JWT 토큰값?",
    "msg": "로그인 성공"
}
 ```
#### 로그인 실패
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "로그인 실패"
}
```
---
## 유권자 투표권 확인

메소드 | 경로 | 짧은 설명
--- | --- | ---
GET | /check | 유권자 확인

### 요청 헤더
~~~
Content-Type : application/json
Authorization : JWT 토큰값(미구현)
~~~

### 요청 파라미터
#### 설명 
`/emc/check/{id}`
#### 예 
`bghgu.tk/emc/check/201232016`

### 응답 바디
#### 유권자 확인
```json
{
    "status": "SUCCESS",
    "data": {
        "id": "201232016",
        "name": "배다슬",
        "userType": 1,
        "tel": "1",
        "confirmCheck": 0,
        "department": {
            "departmentId": 42,
            "departmentName": "IT융합자율학부 소프트웨어공학전공"
        }
    },
    "msg": "인증번호 발급 가능"
}
 ```
#### 이미 투표를 했을 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "이미 투표를 진행했습니다."
}
```
#### 유권자가 아닐 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "유권자가 아닙니다."
}
```
#### 없는 사람일 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "등록된 학생이 없습니다."
}
```
#### 비인가 접근(JWT 토큰값 오류)
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "비인가 접근 오류"
}
```
---
## 유권자 인증번호 발급

메소드 | 경로 | 짧은 설명
--- | --- | ---
POST | /confirm | 투표 인증번호 발급

### 요청 헤더
~~~
Content-Type : application/json
Authorization : JWT 토큰값(미구현)
~~~

### 요청 바디
#### 설명
```json
{
    "id" : "유권자 학번"
}
```
#### 예
```json
{
    "id": "201232016"
}
```
### 응답 바디
#### 인증 번호 발급 성공
```json
{
    "status": "SUCCESS",
    "data": "gu327hyw",
    "msg": "인증번호 발급 성공"
}
 ```
#### 이미 투표를 했을 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "이미 투표를 진행했습니다."
}
```
#### 유권자가 아닐 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "유권자가 아닙니다."
}
```
#### 없는 사람일 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "등록된 학생이 없습니다."
}
```
#### 없는 사람일 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "인증코드가 중복됩니다. 잠시후 다시 시도해 주세요."
}
```
#### 없는 사람일 경우
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "인증번호 저장 실패. 잠시후 다시 시도해 주세요."
}
```
#### 비인가 접근(JWT 토큰값 오류)
 ```json
{
    "status": "FAIL",
    "data": null,
    "msg": "비인가 접근 오류"
}
```
---

