<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="pager">
    <ul>
        <c:if test="${ curPageNum > 5 && !empty kwd }">
            <li><a href="/mysite/board?page=${ blockStartNum - 1 }&kwd=${ kwd }">◀</a></li>
        </c:if>
        
        <c:if test="${ curPageNum > 5 }">
            <li><a href="/mysite/board?page=${ blockStartNum - 1 }">◀</a></li>
        </c:if>
        
        <c:forEach var="i" begin="${ blockStartNum }" end="${ blockLastNum }">
            <c:choose>
                <c:when test="${ i > lastPageNum }">
                    <li>${ i }</li>
                </c:when>
                <c:when test="${ i == curPageNum }">
                    <li class="selected">${ i }</li>
                </c:when>
                <c:when test="${ !empty kwd}">
                    <li><a href="/mysite/board?a=search&page=${ i }&kwd=${ kwd }">${ i }</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/mysite/board?page=${ i }">${ i }</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        
        <c:if test="${ lastPageNum > blockLastNum && !empty kwd }">
            <li><a href="/mysite/board?a=search&page=${ blockLastNum + 1 }&kwd=${ kwd }">▶</a></li>
        </c:if>
        
        <c:if test="${ lastPageNum > blockLastNum }">
            <li><a href="/mysite/board?page=${ blockLastNum + 1 }">▶</a></li>
        </c:if>
    </ul>
</div>  
</body>
</html>