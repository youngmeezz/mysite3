<%@page import="kr.co.itcen.mysite.vo.BoardVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="keyword" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:forEach items='${list}' var='vo'>
					<tr>
						<td>${vo.no }</td>
						<td style='padding-left:${vo.depth*15}px; text-align:left;'>
							<c:if test="${vo.depth gt 0}">
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'/>
							</c:if>
							<a href="${pageContext.servletContext.contextPath }/board/view&no=${vo.no}&userNo=${vo.userNo}">
								<c:choose>
									<c:when test="${vo.status eq 0}">
										삭제된 게시물 입니다.
									</c:when>
									<c:otherwise>
										${vo.title }
									</c:otherwise>
								</c:choose>
							</a></td>
						<td>${vo.name }</td>
						<td>${vo.hit }</td>
						<td>${vo.registerDate }</td>
						<td><a href="${pageContext.servletContext.contextPath }/board?a=deleteform&no=${vo.no}&userNo=${vo.userNo}" class="del">삭제</a></td>
					</tr>
					</c:forEach>
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
					
				<div class="bottom">
				
				<!-- session(로그인 된 상태)이 있으면 글쓰기 창 보이고 session(로그인 안 된 상태이면) 글쓰기 창 가리기 -->
				<c:if test="${not empty authUser }">
				<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
				</c:if>
				</div>		
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>