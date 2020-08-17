package com.ankush.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GodownTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String itemName;
	float qty;
	@ManyToOne()
	@JoinColumn(name = "recieptNo")
	Godown godown;

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

	public Godown getGodown() {
		return godown;
	}

	public void setGodown(Godown godown) {
		this.godown = godown;
	}

	@Override
	public String toString() {
		return "GodownTransaction [id=" + id + ", itemName=" + itemName + ", qty=" + qty + ", godown=" + godown + "]";
	}
}
