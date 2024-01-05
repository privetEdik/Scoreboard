package kettlebell.service.calculation;

import java.util.List;
import java.util.UUID;

import kettlebell.entity.Player;
import kettlebell.model.MatchScoreModel;
import kettlebell.storage.ScoreInstance;

public class MatchScoreCalculationService {
	private Status statusPoint;
	private Status statusGame = Status.CONTINUE;
	private Status statusMatch = Status.CONTINUE;
	private Integer numberSet;
	private List<Status> listOfVictories;
	private MatchScoreModel model;

	public Status getChangeScore(Integer numberPlayer, UUID uuid) {
		ScorePoint point = new ScorePoint();
		statusPoint = point.getPointResult(numberPlayer, uuid);
		endOrContinuesGame(uuid);

		return statusMatch;
	}

	private void endOrContinuesGame(UUID uuid) {
		if (!statusPoint.equals(Status.CONTINUE)) {
			ScoreGame game = new ScoreGame();
			model = ScoreInstance.INSTANCE.getInstance(uuid);
			listOfVictories = model.getListOfVictories();
			numberSet = listOfVictories.size();
			statusGame = game.getGameResult(statusPoint, uuid, numberSet);
			savingCompletedSets();
		}
	}

	private void savingCompletedSets() {
		if (!statusGame.equals(Status.CONTINUE)) {
			listOfVictories.add(statusGame);
			countSetsPlayedAndDetermineWinner();
		}
	}

	private void countSetsPlayedAndDetermineWinner() {
		if (listOfVictories.size() >= 2) {
			Status winnerFirstSet = listOfVictories.get(0);
			Status winnerSecondSet = listOfVictories.get(1);
			if (listOfVictories.size() == 2) {
				if (winnerFirstSet.equals(winnerSecondSet)) {
					//statusMatch = winnerFirstSet;
					writeToModel(winnerFirstSet);
					statusMatch = Status.END;
				}
			}
			if (listOfVictories.size() == 3) {
				if (winnerFirstSet.equals(listOfVictories.get(2))) {
					//statusMatch = winnerFirstSet;
					writeToModel(winnerFirstSet);
				} else {
					//statusMatch = winnerSecondSet;
					writeToModel(winnerSecondSet);
				}
				statusMatch = Status.END;
			}
			
		}
	}
	
	private void writeToModel(Status status) {
		Player player = status.equals(Status.WIN_FIRST_PLAYER)
				?model.getFirstPlayer()
				:model.getSecondPlayer();
		model.setWinner(player);
	}

}
