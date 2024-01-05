package kettlebell.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kettlebell.dto.MatchDTO;
import kettlebell.repository.MatchRepository;
import kettlebell.service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FinishedMatchesPersistenceService service;
	private List<MatchDTO> listMatches;
	private String defaultName = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		service = new FinishedMatchesPersistenceService(new MatchRepository());

		String nameParameter = request.getParameter("filter_by_player_name");
		String pageParameter = request.getParameter("page");

		Optional<String> nameOptional = Optional.ofNullable(nameParameter);
		Optional<String> pageOptional = Optional.ofNullable(pageParameter);
		
		defaultName = nameParameter;
		
		request.setAttribute("default", defaultName);
		Map<String, List<MatchDTO>> map = service.findCountPagesAndPageMatchesByName(pageOptional, nameOptional);
		String countPages = map.keySet().stream().findFirst().get();

		listMatches = map.get(countPages);
		request.setAttribute("count", countPages);
		request.setAttribute("listMatches", listMatches);
		getServletContext().getRequestDispatcher("/matches.jsp").forward(request, response);
	}

}
