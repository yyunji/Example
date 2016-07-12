<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set value="${pageContext.request.contextPath }" var="ctx"/>

<title>Insert title here</title>
</head>
<body>

<h1>회원가입 테스트</h1>

<form action="${ctx }/test" method="post">
	<p><input type="text" id="id" name="id" placeHolder="아이디"/></p>
	<p><input type="password" name="password" placeHolder="비밀번호"/></p>
	<p><input type="password" placeHolder="비밀번호 확인"/></p>
	
	<p><input type="text" name="name" placeHolder="이름"/></p>
	<p><input type="submit" value="회원가입"></p>
</form>

</body>
</html>