<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
<%@ include file="css/new-match.css"%>
</style>
<title>Insert title here</title>
</head>
<body>

	<form class="form" action="new-match" method="post">
		<h2 class="form_title">Input names players</h2>
		<div class="form_group">

			<label class="form_label" for="firstPlayer">First: <input
				class="form_input" type="text" id="firstPlayer" name="firstPlayer">
			</label>
		</div>
		<div class="form_group">
			<label class="form_label" for="secondPlayer">Second: <input
				class="form_input" type="text" id="secondPlayer" name="secondPlayer">
			</label>
		</div>
			<input class="form_button" type="submit" value="Go"/>
	</form>
	
</body>
</html>