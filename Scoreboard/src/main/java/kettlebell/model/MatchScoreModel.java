package kettlebell.model;

import java.util.List;

import kettlebell.dto.PointDTO;
import kettlebell.entity.Player;
import kettlebell.service.calculation.Status;

public class MatchScoreModel {
	private Player firstPlayer;
	private Player secondPlayer;	

	private List<List<Integer>> sets;

	private List<PointDTO> points;
	
	private List<Status> listOfVictories;
	
	private Player winner;

	public MatchScoreModel() {}
	
	public MatchScoreModel(Player firstPlayer, Player secondPlayer, List<List<Integer>> sets, List<PointDTO> points,
			List<Status> listOfVictories) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.sets = sets;
		this.points = points;
		this.listOfVictories = listOfVictories;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public List<PointDTO> getPoints() {
		return points;
	}

	public void setPoints(List<PointDTO> points) {
		this.points = points;
	}
	public void setPoints(Integer i,PointDTO point) {
		this.points.set(i, point) ;
	}

	
	public List<List<Integer>> getSets() {
		return sets;
	}

	public void setSets(List<List<Integer>> sets) {
		this.sets = sets;
	}

	public List<Status> getListOfVictories() {
		return listOfVictories;
	}

	public void setListOfVictories(List<Status> listOfVictories) {
		this.listOfVictories = listOfVictories;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
}
