<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<c:set var="result" value="${param.result }" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>login</title>
    <c:choose>
		<c:when test="${result=='loginFailed' }">
	  		<script>
	    		window.onload=function(){
	      			alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
	    		}
	  		</script>
		</c:when>
	</c:choose>  
</head>
<body>
    <form method="post" encType="UTF-8" action="${contextPath }/member/login.do"><!--액션은 로그인 체크해주는 서블릿으로이동-->
        <p>아이디</p>
        <input type="text" name="id">
        <p>비밀번호</p>
        <input type="password" name="pw">
        <input type="submit" value="로그인">
        
    </form>
  	<a href="${contextPath }/member/addmemberForm.do">회원가입</a>
    <!-- 관리자로 로그인시 지점관리 페이지로 가도록 컨트롤러 작성-->
    <!--a태그 : 아이디찾기-->
    <!--a태그: 비밀번호 찾기-->
</body>
</html>