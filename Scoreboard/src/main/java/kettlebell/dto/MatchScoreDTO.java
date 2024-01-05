package kettlebell.dto;

import kettlebell.model.MatchScoreModel;

public class MatchScoreDTO {
	private String firstName;
	private String secondName;
	private String firstPoint;
	private String secondPoint;
	private Integer set1_1;
	private Integer set1_2;
	private Integer set2_1;
	private Integer set2_2;
	private Integer set3_1;
	private Integer set3_2;
	private String winner;
	
	public MatchScoreDTO(MatchScoreModel model) {
		this.firstName = model.getFirstPlayer().getName();
		this.secondName = model.getSecondPlayer().getName();
		this.firstPoint = model.getPoints().get(0).name();
		this.secondPoint = model.getPoints().get(1).name();
		this.set1_1 = model.getSets().get(0).get(0);
		this.set1_2 = model.getSets().get(0).get(1);
		this.set2_1 = model.getSets().get(1).get(0);
		this.set2_2 = model.getSets().get(1).get(1);
		this.set3_1 = model.getSets().get(2).get(0);
		this.set3_2 = model.getSets().get(2).get(1);
		this.winner = model.getWinner().getName();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public String getFirstPoint() {
		return firstPoint;
	}
	public String getSecondPoint() {
		return secondPoint;
	}
	public Integer getSet1_1() {
		return set1_1;
	}
	public Integer getSet1_2() {
		return set1_2;
	}
	public Integer getSet2_1() {
		return set2_1;
	}
	public Integer getSet2_2() {
		return set2_2;
	}
	public Integer getSet3_1() {
		return set3_1;
	}
	public Integer getSet3_2() {
		return set3_2;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	
}
