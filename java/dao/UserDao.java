package dao;

import java.util.List;

import model.User;
import util.FileUtil;
import util.IsFileUtil;

public class UserDao implements FunctionDao {
	IsFileUtil fileUtil;
	
	public UserDao() {
		
	}
	
	public void setIsFileUtil(IsFileUtil fileUtil) {
		if(fileUtil == null) fileUtil = new FileUtil();
		else this.fileUtil = fileUtil;
	}
	
	public IsFileUtil getFileUtil() {
		return fileUtil;
	}
	
	@Override
	public boolean addUser(User u) {
		if(u != null) {
			// check user in file
			if(getUser(u.getUserId()) == null) {
				getFileUtil().addUserToFile(u);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		if(u == null) return false;
		// get list user from file
		List<User> usList = getListUser();
		if(usList == null) return false;
		// check user in list
		for(User us:usList) {
			if(us.getUserId().equals(u.getUserId())) {
				usList.remove(us);
				usList.add(u);
				getFileUtil().saveFile(usList);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteUser(User u) {
		if(u == null) return false;
		// check user in list
		User us = getUser(u.getUserId());
		if(us != null) {
			List<User> usList = getListUser();
			usList.remove(u);
			// save file
			getFileUtil().saveFile(usList);
		}
		return false;
	}

	@Override
	public User getUser(String id) {
		User us = null;
		if(id == null) return null;
		// get list user from file
		List<User> usList = getListUser();
		if(usList != null) {
			for(User u:usList) {
				if(u.getUserId().equals(id)) {
					us = u;
					break;
				}
			}
		}
		return us;
	}

	@Override
	public List<User> getListUser() {
		return getFileUtil().getListUser();
	}

}
