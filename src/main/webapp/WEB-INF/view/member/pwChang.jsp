<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> pwChang.jsp [비밀번호 변경 페이지]</title>
</head>
<body>
<form action="pwChangeOk" name="frm" method="post">
    비밀번호 : <input type="password" name="memPw" />
    <span>${pwFail1}</span><br />
    <input type="submit" value="확인" />
</form>
</body>
</html>