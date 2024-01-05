package kettlebell.service;

import java.util.UUID;

import kettlebell.dto.MatchScoreDTO;
import kettlebell.storage.ScoreInstance;

public class ViewAndClearStorageService {
	private UUID uuid;
	
	public ViewAndClearStorageService(UUID uuid) {
		this.uuid = uuid;
	}

	public MatchScoreDTO getModelToView() {
		return new MatchScoreDTO(ScoreInstance.INSTANCE.getInstance(uuid));
	}
	
	public void clear() {
		ScoreInstance.INSTANCE.clearStorage(uuid);
	}
}
