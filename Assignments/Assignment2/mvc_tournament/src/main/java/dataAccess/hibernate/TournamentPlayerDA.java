package dataAccess.hibernate;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dataAccess.dao.TournamentPlayerDAI;
import model.tournamentPlayer.TournamentPlayer;
import util.HibernateUtil;

@SuppressWarnings("unchecked")
public class TournamentPlayerDA implements TournamentPlayerDAI {

	public int insert(TournamentPlayer tp) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		//start transaction
		session.beginTransaction();
		
		//Save the Model object
		session.save(tp);
		
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session factory, otherwise program won't end
		session.close();
		
		return tp.getId();
		
	}

	public TournamentPlayer findById(int id) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<TournamentPlayer> query = session.createQuery("from TournamentPlayer where id= :id");
		query.setParameter("id", id);
		TournamentPlayer tp = (TournamentPlayer) query.getSingleResult();

		session.close();
		
		return tp;
		
	}

	public List<TournamentPlayer> findAllPlayersByTournamentId(int tid) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<TournamentPlayer> query = session.createQuery("from TournamentPlayer where tournamentID= :tid");
		query.setParameter("tid", tid);
		List<TournamentPlayer> tp = query.getResultList();

		session.close();
		
		return tp;
		
	}

	@Override
	public List<TournamentPlayer> findAllTournamentsByPlayerId(int pid) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<TournamentPlayer> query = session.createQuery("from TournamentPlayer where playerID= :pid");
		query.setParameter("pid", pid);
		List<TournamentPlayer> tp = query.getResultList();

		session.close();
		
		return tp;
		
	}

	public void delete(int tpid) {
		
		
	}

	@Override
	public void update(int id, TournamentPlayer g) {
		// TODO Auto-generated method stub
		
	}

}
