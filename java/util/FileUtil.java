package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class FileUtil implements IsFileUtil{
	private static final String FILE_NAME = "user.txt";
	// read file
	@Override
	public List<User> getListUser() {
		
		List<User> usList = null;
		try(InputStream in = new FileInputStream(FILE_NAME);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			usList = new ArrayList<> ();
			String line;
			
			while((line = reader.readLine()) != null) {
				User us = getUser(line);
				if(us != null) usList.add(us);		
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return usList;	
	}
	
	// add new user to file	
	@Override
	public boolean addUserToFile(User us) {
		
		List<User> usList = getListUser();
		if(us == null) return false;
		// list user null
		if(usList == null) usList = new ArrayList<> ();
		// check userId in list
		for(User u : usList) {
			if(u.getUserId().equals(us.getUserId())) return false;
		}
		usList.add(us);
		// save file
		return saveFile(usList);
	}
	
	@Override
	public boolean saveFile(List<User> usList) {
		
		if(usList != null) {
			try(OutputStream out = new FileOutputStream(FILE_NAME);
					BufferedWriter wt = new BufferedWriter(new OutputStreamWriter(out))) {
				for(User u:usList) {
					wt.write(u.toString());
				}
				wt.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private User getUser(String line) {
		User user = null;
		if(line != null) {
			String [] users = line.split("\s+"); 
			if(users.length == 4) {
				String id = users[0];
				String password = users[1];
				int countLoginFail = Integer.parseInt(users[2]);
				boolean state = Boolean.parseBoolean(users[3]);
 
				user = new User(id, password);
				user.setCountLoginFail(countLoginFail);
				user.setState(state);
			}
		}
		return user;
	}
}
