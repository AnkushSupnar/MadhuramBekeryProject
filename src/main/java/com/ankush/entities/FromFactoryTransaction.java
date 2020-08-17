package com.ankush.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FromFactoryTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String itemName;
	float qty;
	@ManyToOne()
	@JoinColumn(name = "recieptNo")
	FromFactory factory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return itemName;
	}

	public void setItem(String itemName) {
		this.itemName = itemName;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public FromFactory getFactory() {
		return factory;
	}

	public void setFactory(FromFactory factory) {
		this.factory = factory;
	}

	@Override
	public String toString() {
		return "FromFactoryTransaction [id=" + id + ", itemName=" + itemName + ", qty=" + qty + ", factory=" + factory
				+ "]";
	}

}
