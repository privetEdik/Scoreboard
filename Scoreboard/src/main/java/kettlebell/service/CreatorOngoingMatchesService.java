package kettlebell.service;

import java.util.UUID;

import kettlebell.entity.Player;
import kettlebell.model.MatchScoreModel;
import kettlebell.repository.PlayerRepository;
import kettlebell.storage.ScoreInstance;

public class CreatorOngoingMatchesService {
	private String firstName;
	private String secondName;
	private PlayerRepository playerRepository;
	
	public CreatorOngoingMatchesService(String firstName, String secondName) {
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public UUID createNewMatchAndFindAccessKey() {
		Player playerFirst = new Player(firstName);
		Player playerSecond= new Player(secondName);
		playerRepository = new PlayerRepository();
		playerFirst = playerRepository.save(playerFirst);
		playerSecond = playerRepository.save(playerSecond);
		UUID uuid = UUID.randomUUID();
		MatchScoreModel dto = ScoreInstance.INSTANCE.getInstance(uuid);
		dto.setFirstPlayer(playerFirst);
		dto.setSecondPlayer(playerSecond);
		dto.setWinner(new Player());
		return uuid;
	}
}
