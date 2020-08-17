package com.ankush.dao.dao;

import java.util.List;

import org.hibernate.Session;

import com.ankush.entities.Login;

public interface LoginDao {
	public Session createSession();

	public void closeSession();

	public int saveUser(Login login);

	public Login getLoginByName(String userName);

	public void setLoginStatus(int id, String status);

	public List<String> getAllUserName();

	public List<String> getActiveCounter();

	public Login getloginById(int id);

	public List<Login> getAllLogin();

}
