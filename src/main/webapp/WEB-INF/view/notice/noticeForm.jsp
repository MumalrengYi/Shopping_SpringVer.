<%--
  Created by IntelliJ IDEA.
  User: Geunsu
  Date: 2021-07-19
  Time: 오후 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>noticeForm.jsp</title>
</head>
<body>
<form action="noticeWrite" method="post">
  제목: <input type="text" name="noticeSub"/> <br/>
  내용: <textarea name="noticeCon" cols="60" rows="5"></textarea> <br/>
  종류: <select name="noticeKind">
  <option>긴급</option>
  <option>일반</option>
  <option>공지</option>
</select>
  <br/>
  <input type="submit" value="공지등록">
</form>
</body>
</html>
