package wizen.rafal.workers.dao;

import java.util.List;

import wizen.rafal.workers.entity.WorkTime;

public interface WorkTimeDAO {

	public List<WorkTime> findAll();
	public WorkTime getWorkTimeById(int theId);
	public void save(WorkTime theWorkTime);
	public void deleteById(int theId);
}
