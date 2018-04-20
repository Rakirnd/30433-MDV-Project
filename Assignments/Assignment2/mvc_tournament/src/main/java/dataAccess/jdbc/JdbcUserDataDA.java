package dataAccess.jdbc;

import java.util.List;

import dataAccess.dao.UserDataDAI;
import model.userData.UserData;

public class JdbcUserDataDA implements UserDataDAI{

	@Override
	public int insert(UserData ud) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserData findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserData> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData findByUserId(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(int id, UserData ud) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBalance(int id, int balance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
