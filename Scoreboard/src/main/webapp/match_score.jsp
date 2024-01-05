<%@page import="kettlebell.dto.MatchScoreDTO"%>
<%@page import="kettlebell.service.calculation.Status"%>
<%@ page import="kettlebell.model.MatchScoreModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
<%@ include file="css/match_score.css"%>
</style>
<title>Insert title here</title>

</head>
<body>
	<div class="form">
		<%
				MatchScoreDTO dto =(MatchScoreDTO) getServletContext().getAttribute("dto");
		%>
		
		<div class="name">
			<p class="title">Имена</p>
			<p class="indentation">
				<%out.print(dto.getFirstName());%></p>
			<p><%out.print(dto.getSecondName());%></p>
		</div>
		<div class="point">
			<p class="title">Очки</p>
			<p class="indentation">
				<%out.print(dto.getFirstPoint());%></p>
			<p><%out.print(dto.getSecondPoint());%></p>
		</div>
		<div class="set1">
		<p class="title">Сет №1</p>
			<p><%out.print(dto.getSet1_1());%></p>
			<p><%out.print(dto.getSet1_2());%></p>
		</div>
		<div class="set2">
		<p class="title">Сет №2</p>
			<p><%out.print(dto.getSet2_1());%></p>
			<p><%out.print(dto.getSet2_2());%></p>
		</div>
		<%if(!(dto.getSet3_1()==0&dto.getSet3_2()==0)){ %>
		<div class="set3">
			<p class="title">Сет №3</p>
			<p><%out.print(dto.getSet3_1());%></p>
			<p><%out.print(dto.getSet3_2());%></p>
		</div>
		<%} %>
		<% 
		Status status =(Status) getServletContext().getAttribute("status");
		
		if(status.equals(Status.CONTINUE)){ 
		%>
		<div class="button-block">
			<form method="POST" action="match-score">		 
				<p><button class="button" name="idPlayer" value="0">+1 очко</button> </p>
				<p><button class="button" name="idPlayer" value="1">+1 очко</button> </p>			
			</form>		
		</div>	
		<%}else if(status.equals(Status.END)) { %>
		<div class="block-winner">
			<p>Победитель</p>
			<p><%out.print(dto.getWinner());%></p>
			<a href="new-match.jsp" class="button">Новый матч</a>
			<a href="matches.jsp" class="button">Статистика</a>
		</div>

		<%} %>
	</div>
</body>
</html>