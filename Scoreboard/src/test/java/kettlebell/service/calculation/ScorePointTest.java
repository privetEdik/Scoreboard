package kettlebell.service.calculation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import kettlebell.dto.PointDTO;
import kettlebell.storage.ScoreInstance;

class ScorePointTest {

	UUID uuid;
	
	
	@Test
	void testGetResult() {// 41:0
		uuid = UUID.randomUUID();
		ScorePoint point = new ScorePoint();
		for (int i = 1; i <= 3; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(0, uuid));
		}
		assertEquals(Status.WIN_FIRST_PLAYER, point.getPointResult(0, uuid));
	}

	@Test
	void testGetResult1() {// 40:0
		ScorePoint point = new ScorePoint();
		uuid = UUID.randomUUID();
		for (int i = 1; i <= 3; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(0, uuid));
		}
	}

	@Test
	void testGetResult2() {// 0:40
		uuid = UUID.randomUUID();
		ScorePoint point = new ScorePoint();
		for (int i = 1; i <=3; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(1, uuid));
		}
		assertEquals(Status.WIN_SECOND_PLAYER, point.getPointResult(1, uuid));
	}

	@Test
	void testGetResult3() {// 41:41
		ScorePoint point = new ScorePoint();
		uuid = UUID.randomUUID();
		for (int i = 1; i <= 3; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(0, uuid));
		}
		for (int i = 1; i <= 3; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(1, uuid));
		}
		assertEquals(Status.CONTINUE, point.getPointResult(0, uuid));
		assertEquals(Status.CONTINUE, point.getPointResult(1, uuid));
		assertEquals(PointDTO.FORTY, ScoreInstance.INSTANCE.getInstance(uuid).getPoints().get(0));
		assertEquals(PointDTO.FORTY, ScoreInstance.INSTANCE.getInstance(uuid).getPoints().get(1));
	}

	@Test
	void testGetResult4() {// 42:44
		ScorePoint point = new ScorePoint();
		uuid = UUID.randomUUID();
		for (int i = 1; i <= 3; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(0, uuid));
		}
		for (int i = 1; i <= 3; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(1, uuid));
		}
		for (int i = 1; i <= 2; i++) {
			assertEquals(Status.CONTINUE, point.getPointResult(0, uuid));
			assertEquals(Status.CONTINUE, point.getPointResult(1, uuid));
		}
		assertEquals(Status.CONTINUE, point.getPointResult(1, uuid));
		assertEquals(Status.WIN_SECOND_PLAYER, point.getPointResult(1, uuid));
	}

}
