package dao;

import java.util.List;

import model.User;

public interface FunctionDao {
	
	boolean addUser(User u);
	boolean updateUser(User u);
	boolean deleteUser(User u);
	User getUser(String id);
	List<User> getListUser();
	
}
