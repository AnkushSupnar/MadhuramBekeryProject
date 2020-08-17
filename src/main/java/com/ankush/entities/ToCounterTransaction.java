package com.ankush.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ToCounterTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String itemName;
	float qty;
	@ManyToOne()
	@JoinColumn(name = "recieptNo")
	ToCounter toCounter;

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

	public ToCounter getToCounter() {
		return toCounter;
	}

	public void setToCounter(ToCounter toCounter) {
		this.toCounter = toCounter;
	}

	@Override
	public String toString() {
		return "ToCounterTransaction [id=" + id + ", itemName=" + itemName + ", qty=" + qty + ", toCounter=" + toCounter
				+ "]";
	}
	

}
