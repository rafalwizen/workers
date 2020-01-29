package wizen.rafal.workers.service;

import java.util.List;

import wizen.rafal.workers.entity.WorkTime;

public interface WorkTimeService {

	public List<WorkTime> findAll();
	public Object getWorkTimeById(int theId);
	public void save(WorkTime theWorkTime);
}
