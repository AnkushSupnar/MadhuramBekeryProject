package com.ankush.view.home;

import java.io.File;

import com.ankush.entities.Address;

public class Backup {

	private String path;

	public Backup(String path) {
		this.path = path;
	}
	public static void main(String[] args) {
	}

	public int writeAddressBackup(Address add) {
		try {
			String file = path + "AddressBackUp.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
