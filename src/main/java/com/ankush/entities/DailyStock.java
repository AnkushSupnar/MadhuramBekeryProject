package com.ankush.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String itemName;
	float qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "DailyStock [id=" + id + ", itemName=" + itemName + ", qty=" + qty + "]";
	}

}
