package dataAccess.hibernate;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dataAccess.dao.GameDAI;
import model.game.Game;
import util.HibernateUtil;

@SuppressWarnings("unchecked")
public class GameDA implements GameDAI{
	
	public int insert(Game g) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		//start transaction
		session.beginTransaction();
		
		//Save the Model object
		session.save(g);
		
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session
		session.close();
		
		return g.getId();
		
	}
	
	public Game findById(int id) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Game> query = session.createQuery("from Game where id= :id");
		query.setParameter("id", id);
		Game game = (Game) query.getSingleResult();

		session.close();
		
		return game;
		
	}

	public void update(int id, Game g) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Game> query = session.createQuery("update Game set playerOneScore= :playerOneScore, "
															+ "playerTwoScore= :playerTwoScore, gameFinished= :gameFinished where id= :id");
		query.setParameter("playerOneScore", g.getPlayerOneScore());
		query.setParameter("playerTwoScore", g.getPlayerTwoScore());
		query.setParameter("gameFinished", g.getGameFinished());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}

	public List<Game> findAllGamesByMatchId(int mid) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Game> query = session.createQuery("from Game where MatchID= :mid");
		query.setParameter("mid", mid);
		List<Game> game = query.getResultList();

		session.close();
		
		return game;
		
	}

	@Override
	public void delete(int id) {

		
		
	}


}
