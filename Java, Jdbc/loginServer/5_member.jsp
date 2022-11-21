<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer src="./js/regist.js"></script>
</head>
<body>
	<h2>회원가입</h2>
	<form action="member_ok" name="regform" id="regform"
		method="post" onsubmit="return sendit()">
		<input type ="hidden" name = "isIdCheck" id = "isIdCheck" value ="n">
		
		<p>
			아이디 : <input type="text" name="userid" id="userid" maxlength="20" onkeydown ="idModify()">
			<input type="button" id="btnIdCheck" value="중복체크"
				onclick="clickBtn()">
		</p>
		<p id="checkmsg"></p>
		<p>
			비밀번호 : <input type="password" name="userpw" id="userpw"
				maxlength="20">
		</p>
		<p>
			비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re"
				maxlength="20">
		</p>
		<p>
			이름 : <input type="text" name="name" id="name">
		</p>
		<p>
			휴대폰 번호 : <input type="text" name="hp" id="hp">
		</p>
		<p>
			이메일 : <input type="text" name="email" id="email">
		</p>
		<p>
			성별 :<label> 남자 <input type="radio" name="gender" value="남자"
				checked></label> <label> 여자 <input type="radio"
				name="gender" value="여자"></label>
		</p>
		<p>
			취미 : <label>등산<input type="checkbox" name="hobby" value="등산"></label>
			<label>게임<input type="checkbox" name="hobby" value="게임"></label>
			<label>영화감상<input type="checkbox" name="hobby" value="영화감상"></label>
			<label>드라이브<input type="checkbox" name="hobby" value="드라이브"></label>
			<label>운동<input type="checkbox" name="hobby" value="운동"></label>
		</p>
		<p>
			주민등록번호 : <input type="text" name="ssn1"> -<input type="text"
				name="ssn2">
		</p>


		<p>
			우편번호: <input type="text" name="zipcode" maxlength="5"
				id="sample6_postcode">
			<button type="button" onclick="sample6_execDaumPostcode()">검색</button>
		</p>
		<p>
			주소 <input type="text" name="sample6_address">
		</p>
		<p>
			상세주소 <input type="text" name="sample6_detailAddress">
		</p>
		<p>
			참고사항 <input type="text" name="sample6_extraAddress">
		</p>
		<p>
			<button>가입완료</button>
			<button type="reset">다시작성</button>
		</p>
	</form>


</body>
</html>