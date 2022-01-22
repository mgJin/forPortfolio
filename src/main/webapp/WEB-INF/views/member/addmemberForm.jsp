<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<style>
	#addFormwrap{
		
	}
</style>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script type="text/javascript">
	function fn_active(){
		console.log("dddd");
		$.ajax({
			type="POST",
			url : "/member/checkDuplicationById",
			data : {
				"id": $("#formId").val();
			},
			error : function(error){
				console.log("제대로 전달안됨");
			},
			success : function(data){
				if(data==0&&data=null){
					
					var submitBut = document.getElementById("formSubmit");
				
					submitBut.disabled = false;
				}else if(data==1)
				alert("중복된 아이디입니다");
			}
		});	
	}
</script>
</head>
<body>
	<div id="addFormwrap">
    <h2>회원 가입창</h2>
    
    <form class="form" method="post" enctype="UTF-8" action="${contextPath }/member/addMember.do">
        
        <!--필수 항목을 입력하지 않으면 다시 입력하는 창으로-->
        <!--만약 입력했었던 페이지 정보를 남길 수 있다면 best-->
        <p>*아이디</p>
        <input type="text" name="id" id="formId">
        <input type="button" name="checkIdOverlap" value="아이디 중복확인" onClick="fn_active();">
        <p>*비밀번호</p>
        <input type="password"  name="pw" id="formPw">
        <!--구현못하면 빼자-->
		<!--
        <p>*비밀번호 재확인</p>
        <input type="password" name="checkPw">
		-->     
        <p>*이름</p>
        <input type="text" name="name" id="formName">
        <p>생일</p>
        <input type="date" name="birth" id="formBirth">
        <p>핸드폰 번호</p>
        <input type="text" name="call" id="formHp">
        <p>성별</p>
        <input type="text" name="gender" id="formGender">
        <p>이메일</p>
        <input type="text" name="email" id="formEmail">
        <p>주소</p>
        <input type="text" name="address" id="formAddress">
        <!--아이디와 비밀번호는 필수-->
        <!--아이디 비밀번호 이름  핸드폰번호 성별 이메일 주소-->

        <input type="submit" id="formSubmit" value="가입하기" >
        <!--아이디 중복확인을 누르면 disable이 풀림 -->
        <input type="reset" value="다시입력">
    </form>
	</div>
</body>
</html>