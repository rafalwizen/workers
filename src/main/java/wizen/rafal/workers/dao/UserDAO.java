package wizen.rafal.workers.dao;

import wizen.rafal.workers.entity.User;

public interface UserDAO {

    public User getUserByName(String name);
}
