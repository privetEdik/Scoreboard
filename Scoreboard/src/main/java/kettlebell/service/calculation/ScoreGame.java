package kettlebell.service.calculation;

import java.util.List;
import java.util.UUID;

import kettlebell.storage.ScoreInstance;

public class ScoreGame {
	private List<Integer> set;
	private Integer winGamesPlayer1;
	private Integer winGamesPlayer2;
	private Integer difference = 0;
		
	public Status getGameResult(Status status,UUID uuid,Integer numberSet) {
		Integer numberPlayer = status.equals(Status.WIN_FIRST_PLAYER)?0:1;
		
		set = ScoreInstance.INSTANCE.getInstance(uuid).getSets().get(numberSet);
		
		int winGamesPlayer = set.get(numberPlayer);
		set.set(numberPlayer, winGamesPlayer+1);
		
		winGamesPlayer1 = set.get(0);
		winGamesPlayer2 = set.get(1);
		
		if(winGamesPlayer1+winGamesPlayer2<6) {
			return Status.CONTINUE;
		}
		
		if((winGamesPlayer1>=6)|(winGamesPlayer2>=6)) {
			difference = winGamesPlayer1-winGamesPlayer2;
		}
		
		if(difference<=-2||winGamesPlayer2==10) {
			return Status.WIN_SECOND_PLAYER;
		}else if(difference>=2||winGamesPlayer1==10) {
			return Status.WIN_FIRST_PLAYER;
		}		
		return Status.CONTINUE;
	}
}
