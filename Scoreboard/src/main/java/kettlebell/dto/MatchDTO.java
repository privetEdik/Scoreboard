package kettlebell.dto;

import kettlebell.entity.Match;

public class MatchDTO {
	private Long id;
	private String playerFirstName;
	private String playerSecondName;
	private String winner;
	
	public MatchDTO(Match match) {
		this.id = match.getId();
		this.playerFirstName = match.getPlayer1().getName();
		this.playerSecondName = match.getPlayer2().getName();
		this.winner = match.getWinner().getName();
	}
	
	public Long getId() {
		return id;
	}
	public String getPlayerFirstName() {
		return playerFirstName;
	}
	public String getPlayerSecondName() {
		return playerSecondName;
	}
	public String getWinner() {
		return winner;
	}
		
}
