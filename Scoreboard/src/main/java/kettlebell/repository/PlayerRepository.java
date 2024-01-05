package kettlebell.repository;

import java.util.List;
import java.util.Optional;

import kettlebell.entity.Player;

public class PlayerRepository extends BaseRepository<Long, Player> {

	public PlayerRepository() {
		super(Player.class);		
	}

	public Optional<Player> findByName(Player entity) {
		getSession().getTransaction().begin();
		String name = entity.getName();
		 Player player = null;
		 try {
			 
			 List<Player> list = getSession().
				createQuery("from Player where name=:playerName",Player.class).
				setParameter("playerName", name).getResultList();
			 player = list.get(0);
			 
		 }catch (Exception e) {
			 System.out.println(e.getMessage() + e.getClass());
			
		}
		 getSession().getTransaction().commit();
		 return Optional.ofNullable(player);
	}
}
