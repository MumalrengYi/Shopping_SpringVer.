
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>outPw.jsp [회원탈퇴 페이지]</title>
    <script type="text/javascript">
        function outConfirm(){
            if(confirm("레알로 탈퇴하실라구요?")){
                document.frm.submit();
            }else{
                return false;
            }
        }
    </script>
</head>
<body>
<form action="memOutOk" method="post"
      onsubmit="return outConfirm()" name="frm">
    비밀번호 : <input type="password" name="memPw" />
    <span>${pwFail}</span><br />
    <input type="submit" value="탈퇴" />
</form>
</body>
</html>