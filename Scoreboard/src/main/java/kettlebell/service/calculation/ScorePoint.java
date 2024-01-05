package kettlebell.service.calculation;

import java.util.List;
import java.util.UUID;

import kettlebell.dto.PointDTO;
import kettlebell.storage.ScoreInstance;

public class ScorePoint {
	private List<PointDTO> list;
	private Integer index1;
	private Integer index2;
	private Integer difference;

	public Status getPointResult(Integer numberPlayer, UUID uuid) {
		list = ScoreInstance.INSTANCE.getInstance(uuid).getPoints();

		int i = list.get(numberPlayer).ordinal();

		list.set(numberPlayer, PointDTO.values()[i + 1]);

		index1 = list.get(0).ordinal();
		index2 = list.get(1).ordinal();

		if ((index1<=3) & (index2<=3)) {
			return Status.CONTINUE;
		}

		if (index1 == 4 & index2 == 4) {
			list.set(0, PointDTO.FORTY);
			list.set(1, PointDTO.FORTY);
			return Status.CONTINUE;
		}

		difference = index1 - index2;

		if (difference <= -2) {
			list.set(0, PointDTO.ZERO);
			list.set(1, PointDTO.ZERO);
			return Status.WIN_SECOND_PLAYER;
		} else if (difference >= 2) {
			list.set(0, PointDTO.ZERO);
			list.set(1, PointDTO.ZERO);
			return Status.WIN_FIRST_PLAYER;
		}

		return Status.CONTINUE;
	}
}
