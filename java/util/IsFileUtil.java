package util;

import java.util.List;

import model.User;

public interface IsFileUtil {
	List<User> getListUser();
	boolean addUserToFile(User us);
	boolean saveFile(List<User> usList);
}
