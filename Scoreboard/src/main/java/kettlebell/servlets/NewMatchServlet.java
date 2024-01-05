package kettlebell.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kettlebell.service.CreatorOngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

import static kettlebell.utils.ValidationName.isNotValidName;

@WebServlet(name = "NewMatchServlet", urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
   private CreatorOngoingMatchesService service;
   private UUID uuid;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nameFirstPlayer = request.getParameter("firstPlayer");
		String nameSecondPlayer = request.getParameter("secondPlayer");

		if (isNotValidName(nameFirstPlayer) || isNotValidName(nameSecondPlayer)|| nameFirstPlayer.equals(nameSecondPlayer)) {
			response.sendRedirect("new-match.jsp");
			return;
		}

		service = new CreatorOngoingMatchesService(nameFirstPlayer, nameSecondPlayer);
		uuid = service.createNewMatchAndFindAccessKey();

		response.sendRedirect("match-score?uuid="+uuid);
	}
}
