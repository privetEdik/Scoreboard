package kettlebell.service.calculation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class ScoreGameTest {

	@Test
	void testGetGameResult_Six_Zero() {
		ScoreGame game = new ScoreGame();
		UUID uuid = UUID.randomUUID(); 
		for (int i = 1; i < 6; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_FIRST_PLAYER, uuid, 1));
		}
		assertEquals(Status.WIN_FIRST_PLAYER, game.getGameResult(Status.WIN_FIRST_PLAYER, uuid, 1));
	}
	@Test
	void testGetGameResult_Zero_Six() {
		ScoreGame game = new ScoreGame();
		UUID uuid = UUID.randomUUID(); 
		for (int i = 1; i < 6; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
		}
		assertEquals(Status.WIN_SECOND_PLAYER, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
	}
	@Test
	void testGetGameResult_Six_Six() {
		ScoreGame game = new ScoreGame();
		UUID uuid = UUID.randomUUID(); 
		for (int i = 1; i < 6; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_FIRST_PLAYER, uuid, 1));
		}
		for (int i = 1; i < 6; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
		}
		assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_FIRST_PLAYER, uuid, 1));
		assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
	}
	@Test
	void testGetGameResult_Five_Seven() {
		ScoreGame game = new ScoreGame();
		UUID uuid = UUID.randomUUID(); 
		for (int i = 1; i < 6; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_FIRST_PLAYER, uuid, 1));
		}
		for (int i = 1; i < 7; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
		}
		assertEquals(Status.WIN_SECOND_PLAYER, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
	}
	@Test
	void testGetGameResult_Seven_Nine() {
		ScoreGame game = new ScoreGame();
		UUID uuid = UUID.randomUUID(); 
		for (int i = 1; i < 8; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_FIRST_PLAYER, uuid, 1));
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
		}

		assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
		assertEquals(Status.WIN_SECOND_PLAYER, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
	}
	@Test
	void testGetGameResult_Ten_Nine() {
		ScoreGame game = new ScoreGame();
		UUID uuid = UUID.randomUUID(); 
		for (int i = 1; i < 10; i++) {
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_FIRST_PLAYER, uuid, 1));
			assertEquals(Status.CONTINUE, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
		}
		assertEquals(Status.WIN_SECOND_PLAYER, game.getGameResult(Status.WIN_SECOND_PLAYER, uuid, 1));
	}

}
