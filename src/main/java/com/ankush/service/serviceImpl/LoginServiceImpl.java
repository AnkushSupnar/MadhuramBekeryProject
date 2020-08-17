package com.ankush.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.LoginDao;
import com.ankush.dao.impl.LoginDaoImpl;
import com.ankush.entities.Login;
import com.ankush.service.service.LoginService;
import com.ankush.util.HibernateUtil;

public class LoginServiceImpl implements LoginService {

	private LoginDao dao;
	private Session session;

	public LoginServiceImpl() {
		dao = new LoginDaoImpl();
	}
	@Override
	public Session createSession() {
		return this.session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void closeSession() {
		this.session.close();

	}

	@Override
	public int saveUser(Login login) {

		return dao.saveUser(login);
	}

	@Override
	public Login getLoginByName(String userName) {
		return dao.getLoginByName(userName);
	}

	@Override
	public void setLoginStatus(int id, String status) {
		dao.setLoginStatus(id, status);

	}

	@Override
	public List<String> getAllUserName() {
		return dao.getAllUserName();
	}

	@Override
	public List<String> getActiveCounter() {
		try {

			List<String> loginCounterList = dao.getActiveCounter();
			List<String> counterList = new CounterServiceImpl().getAllCounterName();
			List<String> uniquList = new ArrayList<>();
			for (String counter : counterList) {
				if (!loginCounterList.contains(counter)) {
					System.out.println("Not Contain " + counter);
					uniquList.add(counter);
				}
			}
			// return uniquList;
			return counterList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Login getloginById(int id) {
		return dao.getloginById(id);
	}

	@Override
	public List<Login> getAllLogin() {
		return dao.getAllLogin();
	}

}
