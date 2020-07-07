package wizen.rafal.workers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wizen.rafal.workers.dao.WorkTimeDAO;
import wizen.rafal.workers.entity.WorkTime;

@Service
public class WorkTimeServiceImpl implements WorkTimeService{

	private WorkTimeDAO workTimeDAO;
	
	@Autowired
	public WorkTimeServiceImpl (WorkTimeDAO theWorkTimeDAO) {
		workTimeDAO = theWorkTimeDAO;
	}
	
	@Override
	@Transactional
	public List<WorkTime> findAll() {
		return workTimeDAO.findAll();
	}

	@Override
	@Transactional
	public WorkTime getWorkTimeById(int theId) {
		return workTimeDAO.getWorkTimeById(theId);
	}

	@Override
	@Transactional
	public void save(WorkTime theWorkTime) {
		workTimeDAO.save(theWorkTime);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		workTimeDAO.deleteById(theId);
	}
}
