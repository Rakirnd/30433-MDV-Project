package model.tournament;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

@SuppressWarnings("unchecked")
public class TournamentDA implements TournamentDAI{

	public int insert(Tournament t) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		//start transaction
		session.beginTransaction();
		
		//Save the Model object
		session.save(t);
		
		//Commit transaction
		session.getTransaction().commit();
		
		//terminate session factory, otherwise program won't end
		session.close();
		
		return t.getId();
		
	}

	@Override
	public Tournament findById(int id) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Tournament> query = session.createQuery("from Tournament where id= :id");
		query.setParameter("id", id);
		Tournament tourn = (Tournament) query.getSingleResult();

		session.close();
		
		return tourn;
		
	}

	@Override
	public List<Tournament> findAll() {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Tournament> query = session.createQuery("from Tournament");
		List<Tournament> tournList = query.getResultList();
		
		session.close();
		
		return tournList;
		
	}
	
	public List<Tournament> findLike(String filter){
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Tournament> query = session.createQuery("from Tournament where name like :fn");
		query.setParameter("fn", "%"+filter+"%");
		List<Tournament> tournList = query.getResultList();
		
		session.close();
		
		return tournList;
		
	}

	public void updateData(int id, Tournament t) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Tournament> query = session.createQuery("update Tournament set prizePool= :prize, "
															+ "name= :name, "
															+ "tournamentType= :type, "
															+ "entryFee= :entryFee, "
															+ "isFinished= :isFinished "
															+ "where id= :id");
		query.setParameter("name", t.getName());
		query.setParameter("type", t.getTournamentType());
		query.setParameter("entryFee", t.getEntryFee());
		query.setParameter("isFinished", t.getIsFinished());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}
	
	public void updatePrize(int id, Tournament t) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Tournament> query = session.createQuery("update Tournament set prizePool= :prize "
															+ "where id= :id");
		
		query.setParameter("prize", t.getPrizePool());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}
	
	public void updateStatus(int id, Tournament t) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Tournament> query = session.createQuery("update Tournament set status= :status "
															+ "where id= :id");
		
		query.setParameter("status", t.getStatus());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}
	
	public void updateWinner(int id, Tournament t) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		TypedQuery<Tournament> query = session.createQuery("update Tournament set winner= :winner "
															+ "where id= :id");
		
		query.setParameter("winner", t.getWinner());
		
		query.setParameter("id", id);
		query.executeUpdate();
		
		session.close();
		
	}

	public void delete(int id) {
		
		
	}

	@Override
	public void update(int id, Tournament t) {
		// TODO Auto-generated method stub
		
	}
	
	

}
