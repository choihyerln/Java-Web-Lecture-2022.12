<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = (Integer) request.getAttribute("result");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>계산기 ver.1</title>
    <style>
        input {padding: 5px;}
    </style>
</head>
<body style="margin: 40px;">
    <h1>계산기 Ver.1</h1>
    <hr>
    <br>
    <div style="margin: 5px; background: beige; font-weight: bold;"><%= result %></div>
    <form action="/ch12/calc/ver1" method="post">
        <input type="number" name="num1">
        <select name="op">
            <option value="+" selected>+</option>
            <option value="-">-</option>
            <option value="*">*</option>
            <option value="/">/</option>
        </select>
        <input type="number" name="num2">
        <input type="submit" value="&nbsp;=&nbsp;">
    </form>
</body>
</html>