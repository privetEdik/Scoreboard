package kettlebell.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kettlebell.dto.PointDTO;
import kettlebell.model.MatchScoreModel;
import kettlebell.service.calculation.Status;

public enum ScoreInstance {
	INSTANCE;

	private static MatchScoreModel matchScoreModel;
	private static Map<UUID, MatchScoreModel> map;

	public MatchScoreModel getInstance(UUID uuid) {
		if(map == null) {
			map = new HashMap<>();
		}
		if ( map.containsKey(uuid)) {
			return map.get(uuid);
		} else {
			//map = new HashMap<>();
			List<Integer> list1 = new ArrayList<>(List.of(0,0));
			List<Integer> list2 = new ArrayList<>(List.of(0,0));
			List<Integer> list3 = new ArrayList<>(List.of(0,0));
			List<List<Integer>> list = new ArrayList<>();
			list.add(list1);
			list.add(list2);
			list.add(list3);
			//@formatter:off
			matchScoreModel = new MatchScoreModel(
					null,
					null,
					list,
					new ArrayList<PointDTO>(List.of(PointDTO.ZERO,PointDTO.ZERO)),
					new ArrayList<Status>());
			//@formatter:on
			map.put(uuid, matchScoreModel);
		  
			return matchScoreModel;
		}

	}
	
	public void clearStorage(UUID uuid) {
		map.remove(uuid);
	}

}
