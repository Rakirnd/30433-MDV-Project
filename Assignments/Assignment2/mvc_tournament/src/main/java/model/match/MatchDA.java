package model.match;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

@SuppressWarnings("unchecked")
public class MatchDA implements MatchDAI {

	public int insert(MatchC m) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		//start transaction
		session.beginTransaction();
		
		//Save the Model object
		session.save(m);
		
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session factory, otherwise program won't end
		session.close();
		
		return m.getId();
		
	}

	public MatchC findById(int id) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<MatchC> query = session.createQuery("from MatchC where id= :id");
		query.setParameter("id", id);
		MatchC match = (MatchC) query.getSingleResult();

		session.close();
		
		return match;
		
	}

	public List<MatchC> findAllMatchesByTournamentId(int tid) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<MatchC> query = session.createQuery("from MatchC where tournID= :tid");
		query.setParameter("tid", tid);
		List<MatchC> match = query.getResultList();

		session.close();
		
		return match;
		
	}

	public List<MatchC> findAllMatchesOfPlayer(int pid) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<MatchC> query = session.createQuery("from MatchC where playerOne= :pid1 or playerTwo= :pid2");
		query.setParameter("pid1", pid);
		query.setParameter("pid2", pid);
		List<MatchC> match = query.getResultList();

		session.close();
		
		return match;
		
		
	}

	public List<MatchC> findAll() {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<MatchC> query = session.createQuery("from MatchC");
		List<MatchC> matchList = query.getResultList();
		
		session.close();
		
		return matchList;
		
	}

	public void update(int id, MatchC m) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<MatchC> query = session.createQuery("update MatchC set playerOne= :playerOne, "
															+ "playerTwo= :playerTwo, "
															+ "playerOneGames= :playerOneGames, "
															+ "playerTwoGames= :playerTwoGames, "
															+ "matchFinished= :matchFinished "
															+ "where id= :id");
		query.setParameter("playerOne", m.getPlayerOne());
		query.setParameter("playerTwo", m.getPlayerTwo());
		query.setParameter("playerOneGames", m.getPlayerOneGames());
		query.setParameter("playerTwoGames", m.getPlayerTwoGames());
		query.setParameter("matchFinished", m.getMatchFinished());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}

	public void delete(int id) {
		
		
		
	}

}
