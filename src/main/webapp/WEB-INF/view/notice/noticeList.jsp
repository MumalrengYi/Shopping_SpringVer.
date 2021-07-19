<%--
  Created by IntelliJ IDEA.
  User: Geunsu
  Date: 2021-07-19
  Time: 오후 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ include file="../include/includeTags.jsp" %>
<html>
<head>
    <title>noticeList.jsp</title>
</head>
<body>

<table border="1">
    <tr>
        <td>번호</td>
        <td>제목</td>
        <td>등록일</td>
        <td>조회수</td>
    </tr>

    <c:forEach items = "${lists}" var="dto" varStatus="cnt">
        <tr>
            <td>${cnt.count}</td>
            <td>${dto.noticeSub}</td>
            <td>${dto.noticeDate}</td>
            <td>${dto.noticeCount}</td>
        </tr>
    </c:forEach>

</table>
<hr/>
<a href="noticeForm">공지사항 등록</a>


</body>
</html>
