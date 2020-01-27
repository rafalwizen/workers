package wizen.rafal.workers.dao;

import java.util.List;

import wizen.rafal.workers.entity.WorkTime;

public interface WorkTimeDAO {

	public List<WorkTime> findAll();
}
