package test;

import java.util.ArrayList;
import java.util.List;

import model.User;
import util.IsFileUtil;

public class FileUtilMock implements IsFileUtil {
	List<User> uList = new ArrayList<User>();
	public FileUtilMock() {
		uList = userList();
	}
	
	public void addList(List<User> list) {
		this.uList = list;
	}
	
	public void AddUserToList(User user) {
		uList.add(user);
	}

	
	public List<User> getUList() {
		return uList;
	}
	@Override
	public List<User> getListUser() {
		// TODO Auto-generated method stub
		return getUList();
	}

	@Override
	public boolean addUserToFile(User us) {
		AddUserToList(us);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean saveFile(List<User> usList) {
		addList(usList);
		return true;
	}

	// data test
	private List<User> userList() {
		List<User> usList = new ArrayList<User>();
		User us1 = new User("1234567890123456", "12345678");
		User us2 = new User("2345678901234567", "ngocngoc");
		us2.setState(true);
		User us3 = new User("3456789012345678", "ngoc1234");
		User us4 = new User("4567890123456789", "tung1234");
		usList.add(us1);
		usList.add(us2);
		usList.add(us3);
		usList.add(us4);	
		return usList;
	}
}
