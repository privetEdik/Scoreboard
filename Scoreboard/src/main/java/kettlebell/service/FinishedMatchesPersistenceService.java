package kettlebell.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import kettlebell.dto.MatchDTO;
import kettlebell.entity.Match;
import kettlebell.entity.Player;
import kettlebell.model.MatchScoreModel;
import kettlebell.repository.MatchRepository;
import kettlebell.repository.PlayerRepository;
import kettlebell.storage.ScoreInstance;

import static kettlebell.utils.ValidationName.isNotValidName;

public class FinishedMatchesPersistenceService {
	private static final Integer NUMBER_OF_MATCHES_SHOWN_ON_THE_PAGE = 3;
	private MatchRepository matchRepository;
	private List<MatchDTO> listDTO;
	private List<Match> list;
	private Player player;
	private PlayerRepository playerRepository;
	private Match match;
	private MatchScoreModel model;

	public FinishedMatchesPersistenceService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public Map<String, List<MatchDTO>> findCountPagesAndPageMatchesByName(Optional<String> page, Optional<String> name) {
		Integer numberPages;
		playerRepository = new PlayerRepository();
		Map<String, List<MatchDTO>> map = new HashMap<String, List<MatchDTO>>();
		if (page.isEmpty() || page.get().isBlank()) {
			numberPages = 0;
		} else {
			numberPages = Integer.valueOf(page.get());
		}

		Integer countAllMatchesForTable = 0;
		Optional<Player> pOptional = Optional.empty();
		if ((!isNotValidName(name.get()))) {
			player = new Player(name.get());

			pOptional = playerRepository.findByName(player);
			if (pOptional.isPresent()) {
				player = pOptional.get();

				countAllMatchesForTable = matchRepository.findCountMatchesByNamePlayers(player.getId()).intValue();

				list = matchRepository.findPageMatchesByNumberPageAndByNamePlayer(player.getName(),
						numberPages * NUMBER_OF_MATCHES_SHOWN_ON_THE_PAGE, NUMBER_OF_MATCHES_SHOWN_ON_THE_PAGE);

			}
		}

		if (pOptional.isEmpty()) {
			list = matchRepository.findAllMatchesByNumberPage(numberPages * NUMBER_OF_MATCHES_SHOWN_ON_THE_PAGE,
					NUMBER_OF_MATCHES_SHOWN_ON_THE_PAGE);
			countAllMatchesForTable = matchRepository.findCountAllMatches().intValue();
		}
		listDTO = list.stream().map((s) -> new MatchDTO(s)).collect(Collectors.toList());
		map.put(calculateNumberOfPagesOfMatchesPlayed(countAllMatchesForTable), listDTO);
		return map;
	}

	private String calculateNumberOfPagesOfMatchesPlayed(Integer countAllMatchesForTable) {
		return String
				.valueOf((int) Math.ceil(Double.valueOf(countAllMatchesForTable) / NUMBER_OF_MATCHES_SHOWN_ON_THE_PAGE));
	}
	
	public void saveMatch(UUID uuid) {
		model = ScoreInstance.INSTANCE.getInstance(uuid);
		match = new Match();
		match.setPlayer1(model.getFirstPlayer());
		match.setPlayer2(model.getSecondPlayer());
		match.setWinner(model.getWinner());
		matchRepository.save(match);
	}
}
