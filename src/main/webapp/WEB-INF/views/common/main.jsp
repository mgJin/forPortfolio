<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<% request.setCharacterEncoding("utf-8"); %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#containerA{
            clear: both;
            width:1200px;
            height:550px;
            box-sizing: border-box;
            /* background-color: blueviolet; */
        }
        #containerB{
            width:1200px;
            height:400px;
            box-sizing: border-box;
            /*background-color: brown;*/
            
        }
        #containerB>ul{
            text-align: center;
        }
        #containerB>ul>li{
            width:200px;
            height:350px;
            /*background-color: darkgrey;*/
            float:left;
            margin-left:20px;
            list-style: none;
        }
        
        .main_image{
        	width:80%;
        	height:500px;
        }
</style>
<script src="${contextPath }/resources/js/goods_js.js"></script>
</head>
<body>
		<div id="containerA">
            <img src="${contextPath }/resources/image/main_salad.jpg" alt="main_image" class="main_image">
            

        </div>
        <div id="containerB">
            <ul>
                <li>
                	<img alt="상품1" src="">                	
                </li>
                
                <li>
                	<img alt="상품2" src="">
                </li>
                <li>
                	<img alt="상품3" src="">
                </li>
                <li>
                	<img alt="상품4" src="">
                </li>
                <li>
					<img alt="상품5" src="">                
                </li>
            </ul>
        </div>
</body>
</html>