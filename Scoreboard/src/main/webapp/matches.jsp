<%@page import="kettlebell.dto.MatchDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
<%@ include file="/css/matches.css"%>
</style>
</head>
<body>
	<div class="form">
	
							<h2 class="form_title">Просмотр сыграных игр</h2>
							
					<form action="matches" method="get" class="form_mid" id="main">		
						<label class="form_label" for="namePlayer">Имя игрока:
						<%
							String defaultName = (String) request.getAttribute("default");
							if(defaultName==null){defaultName="";}
						%> 
							<input class="form_input" type="text" id="namePlayer" name="filter_by_player_name" value = "<%=defaultName%>">
						</label>		
					</form>
			
		<div>		
				<%
					List<MatchDTO> list=(List) request.getAttribute("listMatches");
					if(list!=null){
				%>
								<section class="section">
								<div class="block_list">Номер в базе</div>
								<div class="block_list">Первая позиция</div>
								<div class="block_list">Вторая позиция</div>
								<div class="block_list">Победитель</div>
								</section>
				<%for(int i=0;i<list.size();i++){%>
						<section class="section">
		
								<div class="block_list"><%=list.get(i).getId()%></div>
								<div class="block_list"><%=list.get(i).getPlayerFirstName()%></div>
								<div class="block_list"><%=list.get(i).getPlayerSecondName()%></div>
								<div class="block_list"><%=list.get(i).getWinner()%></div>
		
						</section>		
				<% }%>	
								<p class="title_pages">Доступные страницы</p>
					<div class="section_page">
							<%
								Integer countPages = 0;
								try {
									countPages = Integer.valueOf((String)request.getAttribute("count"));				 
								} catch(Exception e){					 
									countPages = 0;
								}				
								for(int j=0;j<countPages;j++){ 
							%>
				<section class="pages">
					<input type="radio" form="main" id="<%=j%>" name="page" value="<%=j%>">
					<label for="<%=j%>"><%=j+1%></label>
				</section>
								<%} 
			 			} %>
				<div>
					<input class="button" form="main" type="submit" value="Искать"/>
					<a href="main-page.jsp" class="button">На главную</a>
				</div>
			</div>		
		</div>
	</div>
</body>
</html>