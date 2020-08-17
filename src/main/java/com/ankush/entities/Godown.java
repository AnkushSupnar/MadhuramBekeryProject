package com.ankush.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Godown {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	LocalDateTime date;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "godown", fetch = FetchType.EAGER)
	List<GodownTransaction> transactionList = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<GodownTransaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<GodownTransaction> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public String toString() {
		return "Godown [id=" + id + ", date=" + date + "]";
	}

}
