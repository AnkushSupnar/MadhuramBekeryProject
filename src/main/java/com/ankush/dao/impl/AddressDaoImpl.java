package com.ankush.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.AddressDao;
import com.ankush.entities.Address;
import com.ankush.util.HibernateUtil;

public class AddressDaoImpl implements AddressDao {

	@Override
	public List<Address> getAllAddress() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from Address";
			List<Address> list = session.createNativeQuery(hql, Address.class).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
