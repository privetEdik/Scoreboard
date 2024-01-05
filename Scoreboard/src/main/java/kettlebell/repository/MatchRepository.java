package kettlebell.repository;

import java.util.List;

import kettlebell.entity.Match;

public class MatchRepository extends BaseRepository<Long, Match> {

	public MatchRepository() {
		super(Match.class);
	}

	public Long findCountAllMatches() {
		getSession().getTransaction().begin();
		String hql = "Select count(*) from Match";
		Long count = getSession().createQuery(hql, Long.class).getSingleResult();

		getSession().getTransaction().commit();
		return count;
	}

	public Long findCountMatchesByNamePlayers(Long id) {
		getSession().getTransaction().begin();
		String hql = "Select count(*) from Match m where m.player1.id = :idParam or m.player2.id = :idParam";

		Long count = getSession().createQuery(hql, Long.class).setParameter("idParam", id).getSingleResult();

		getSession().getTransaction().commit();
		return count;

	}

	public List<Match> findPageMatchesByNumberPageAndByNamePlayer(String name, Integer firstResult, Integer maxResult) {
		getSession().getTransaction().begin();
		//@formatter:off
		List<Match> list = getSession()
				.createQuery("from Match m where m.player1.name = :name or m.player2.name = :name order by m.id asc",Match.class)
				.setParameter("name", name)
				.setFirstResult(firstResult)
				.setMaxResults(maxResult)
				.getResultList();
		//@formatter:on
		getSession().getTransaction().commit();
		return list;
	}
	public List<Match> findAllMatchesByNumberPage(Integer firstResult, Integer maxResult) {
		getSession().getTransaction().begin();
		//@formatter:off
		List<Match> list = getSession()
				.createQuery("from Match m order by m.id asc",Match.class)
				.setFirstResult(firstResult)
				.setMaxResults(maxResult)
				.getResultList();
		//@formatter:on
		getSession().getTransaction().commit();
		return list;
	}
}