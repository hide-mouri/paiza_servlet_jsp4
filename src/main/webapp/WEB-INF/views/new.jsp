<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Java - paiza</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
        <h1>新規ログ</h1>
        <% String message = (String)request.getAttribute("message");%>
        <p><%= message %></p>

        <form action='' method='get'>
            <input type='hidden' name='id' value=''>
            <label for='title'>タイトル</label><br>
            <input type='text' name='title' value=''>
            <p></p>
            <label for='content'>本文</label><br>
            <textarea name='content' cols='40' rows='10'></textarea>
            <p></p>
            <button type='submit'>保存する</button>
            <a href='list'>キャンセル</a>
        </form>
    </body>
</html>
