package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;

public class UserUtil {
	
	/**
	 * Check userId.
	 * @param id userId
	 * @return true if userId valid
	 */
	public static boolean checkUserId(String id) {
		if(id == null) return false;
		String regex = "\\d{16}";
		Pattern p = Pattern.compile(regex);
		Matcher mc = p.matcher(id);
		return mc.matches();
	}
	
	/**
	 * Check password.
	 * @param password
	 * @return true if password valid
	 */
	public static boolean checkPassword(String password) {
		if(password == null) return false;
		String regex = "\\w{8}";
		Pattern p = Pattern.compile(regex);
		Matcher mc = p.matcher(password);
		return mc.matches();
	}
	
	/**
	 * Check user blocked.
	 * @param us user
	 * @return true if user blocked.
	 */
	public static boolean isBlocked(User us) {
		return (us.getCountLoginFail() == 3);
	}
	
}
