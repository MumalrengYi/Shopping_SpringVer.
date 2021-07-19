<%--
  Created by IntelliJ IDEA.
  User: Geunsu
  Date: 2021-07-19
  Time: 오후 2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>noticeView.jsp</title>
</head>
<body>

글번호: ${dto.noticeNo} <br/>
글제목: ${dto.noticeSub} <br/>
글내용: ${dto.noticeCon} <br/>
등록일: ${dto.noticeDate} <br/>
종류: ${dto.noticeKind} <br/>
조회수: ${dto.noticeCount} <br/>
등록한 사원번호: ${dto.employeeId} <br/>
<br />
<a href="noticeUpdate?noticeNo=${dto.noticeNo}">수정:

</body>
</html>
