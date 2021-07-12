<%--
  Created by IntelliJ IDEA.
  User: Geunsu
  Date: 2021-07-12
  Time: 오전 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>goodsDetail.jsp [상품상세보기]</title>
</head>
</head>
<body>
${test }<br />
<a href="prodModify?prodNum=${goodsCommand.prodNum }" >수정</a><br />
카테고리 :
<c:if test="${goodsCommand.ctgr == 'wear'}">의류</c:if>
<c:if test="${goodsCommand.ctgr == 'cosmetic'}">화장품</c:if>
<c:if test="${goodsCommand.ctgr == 'food'}">음식</c:if>
<c:if test="${goodsCommand.ctgr == 'car'}">자동차용품</c:if>
<br />
상품번호 : ${goodsCommand.prodNum }<br />
상품명 : ${goodsCommand.prodName }<br />
상품 가격 : <fmt:formatNumber value="${goodsCommand.prodPrice }" type="currency"/>
<br />
규격 : ${goodsCommand.prodCapacity }<br />
공급 업체 :  ${goodsCommand.prodSupplyer }<br />
배송비 : ${goodsCommand.prodDelFee }<br />
추천 여부 :
<c:if test="${goodsCommand.recommend == 'Y'}">추천</c:if>
<c:if test="${goodsCommand.recommend == 'N'}">비 추천</c:if>
<br />
상세 정보 ${goodsCommand.prodDetail }<br />
<c:forTokens items="${goodsCommand.prodImage }" delims="," var="prodImage">
    <img src="../goods/upload/${prodImage }" /><br />
</c:forTokens>

</body>
</html>
