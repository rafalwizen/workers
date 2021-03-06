package wizen.rafal.workers.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wizen.rafal.workers.entity.WorkTime;

@Repository
public class WorkTimeDAOImpl implements WorkTimeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public WorkTimeDAOImpl (EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<WorkTime> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<WorkTime> theQuery 
			= currentSession.createQuery("from WorkTime", WorkTime.class);
		
		List<WorkTime> workTimes = theQuery.getResultList(); 
		
		return workTimes;
	}

	@Override
	public WorkTime getWorkTimeById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		WorkTime tempWorkTime = currentSession.get(WorkTime.class, theId);
		
		return tempWorkTime;
	}

	@Override
	public void save(WorkTime theWorkTime) {
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(theWorkTime);
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		WorkTime tempWorkTime = currentSession.get(WorkTime.class, theId);
		currentSession.delete(tempWorkTime);
	}
}
