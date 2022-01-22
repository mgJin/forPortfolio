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
<title>boardMain</title>
<script src="${contextPath }/resources/js/board_js.js"></script>
</head>
<body>
<table>
  <tr>
     <td >글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <!-- 작성일 할 수 있으면 넣기 -->
  </tr>
 <c:choose>
 	<c:when test="${articleList==null }">
 	<tr>
      <td colspan="4">
         <p>
            <b><span >등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  	</c:when>
  	<c:when test="${articleList!=null }">
  	
  		<c:forEach var="article" items="${articleList }" varStatus="articleNum">
  			<tr>
  				<td>${articleNum.count }</td>
  				<td>${article.id}</td>
	  			<td>
	  				<span ></span>
		   				<c:choose>
		   					<c:when test="${article.level>1 }" >
		   						<c:forEach begin="1" end="${article.level }" step="1">
		   							<span style="padding-left:20px"/>
		   						</c:forEach>
		   						<span >[답변]</span>
		   						<a href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO }">${article.title }</a>
		   					 </c:when>
					         <c:otherwise>
					         	
					            <a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
					         </c:otherwise>
		   				</c:choose>
		   		</td>
	  			<td>${article.writeDate}</td> 
	</tr>
  	</c:forEach>
  </c:when>
 </c:choose>
 
  </table>
  <a href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/addArticleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p>글쓰기</p></a>
</body>
</html>
  
</body>
</html>