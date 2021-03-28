<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>Simple stack calculator</title>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
		<script src="functions.js"></script>
	</head>
	<body>
	    <h1>Simple stack calculator (rest application)</h1>
	    <div>
	        <button onclick="show();">Show</button>
	        <input id="input" size="10" />
	        <button onclick="put();">put</button>
	        <span> | </span>
	        <button onclick="add();">+</button>
	        <span> </span> <span style="color: blue;" id="message"></span>
	    </div>
	    <p id="numbers"></p>
	</body>
</html>