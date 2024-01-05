package kettlebell.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kettlebell.dto.MatchScoreDTO;
import kettlebell.repository.MatchRepository;
import kettlebell.service.FinishedMatchesPersistenceService;
import kettlebell.service.ViewAndClearStorageService;
import kettlebell.service.calculation.MatchScoreCalculationService;
import kettlebell.service.calculation.Status;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UUID uuid;
	private Integer numberPlayer;
	private MatchScoreDTO dto;
	private MatchScoreCalculationService calculationService;
	private ViewAndClearStorageService viewAndClearStorageService;
	private FinishedMatchesPersistenceService matchesPersistenceService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		uuid = UUID.fromString(request.getParameter("uuid"));
		dto = new ViewAndClearStorageService(uuid).getModelToView();
		getServletContext().setAttribute("dto", dto);
		getServletContext().setAttribute("uuid", uuid);
		getServletContext().setAttribute("status", Status.CONTINUE);

		response.sendRedirect("match_score.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPlayer = request.getParameter("idPlayer");
		Status status = Status.CONTINUE;
		if (idPlayer == null || idPlayer.isEmpty()) {
			response.sendRedirect("match_score.jsp");
			return;
		} else if (idPlayer.equals("0") || idPlayer.equals("1")) {
			numberPlayer = Integer.parseInt(idPlayer);

			uuid = (UUID) getServletContext().getAttribute("uuid");

			calculationService = new MatchScoreCalculationService();
			status = calculationService.getChangeScore(numberPlayer, uuid);
			viewAndClearStorageService = new ViewAndClearStorageService(uuid);
			dto = viewAndClearStorageService.getModelToView();
			

			if (status.equals(Status.END)) {
				matchesPersistenceService = new FinishedMatchesPersistenceService(new MatchRepository());
				matchesPersistenceService.saveMatch(uuid);
				viewAndClearStorageService.clear();
			}
			
			getServletContext().setAttribute("dto", dto);
			getServletContext().setAttribute("status", status);
			response.sendRedirect("match_score.jsp");
			return;
		}

	}

}
