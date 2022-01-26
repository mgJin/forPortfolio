<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<c:set var="isLogOn" value="${sessionScope.isLogOn }"/>
<c:set var="member" value="${sessionScope.member }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style>
 		#header{
            width: 1200px;
            height:100px;
            <!--border:1px solid black;-->
            box-sizing: border-box;
            font-size:20px;
        }
        #logo{
        	float:left;
            width:200px;
            height: 100px;
            
            
            
        }
        #menunav{
        	
            margin-top: 40px;
            margin-left:500px;
            width:500px;
            height:100px;
            <!--border: 1px solid black;-->
            
            
        }
        #menunav>ul>li{
        	margin-top:50px;
            width: 100px;
            height: 80px;
            display:inline-block;
            
            
        }
        #logout{
            float:right;
            margin-top: -120px;
            width: 150px;
            height: 50px;
            <!--font-size:120px;-->
           <!-- border: 1px solid black;-->
        }
        .nav_logout{
            list-style: none;
        }
        #header a{
        	text-decoration-line:none;
        }
</style>
</head>
<body>
 		<div id="header">
            <a href="${contextPath }/main.do" id="logo"><img src="${contextPath }/resources/image/logo.png" alt="logoImage" /></a>
            <nav id="menunav">
                <ul>
                    <li><a href="${contextPath }/board/listArticle.do">게시판</a></li><!-- 각각의 페이지로 가기 -->
                    <li><a href="${contextPath }/goods/viewGoods.do">제품</a></li>
                    <c:if test="${member.authority eq 'admin' }"> 
                    	<li><a href="">회원관리</a></li>
                    </c:if>
                </ul>
            </nav>
            <!-- isLogOn으로 choose분기 나누기 -->
            <div id="logout">
	            <c:choose>
	            	<c:when test="${isLogOn==true && member !=null }">
	            	<a href="${contextPath }/member/logout.do"><h4>로그아웃</h4></a>
	            	</c:when>
	            	<c:otherwise>
	            		<a href="${contextPath}/member/loginForm.do"><h4>로그인</h4></h2></a>
	            	</c:otherwise>
				</c:choose>
            </div>
	</div>
</body>
</html>