package com.ankush.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GodownStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String ItemName;
	float qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "GodownStock [id=" + id + ", ItemName=" + ItemName + ", qty=" + qty + "]";
	}
}
