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
import javax.persistence.OneToOne;

@Entity
public class ToCounter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	LocalDateTime date;
	@OneToOne(cascade = CascadeType.ALL)
	Counter counter;
	String status;
	int fromFactoryId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "toCounter", fetch = FetchType.EAGER)
	List<ToCounterTransaction> transactions = new ArrayList<>();

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

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFromFactoryId() {
		return fromFactoryId;
	}

	public void setFromFactoryId(int fromFactoryId) {
		this.fromFactoryId = fromFactoryId;
	}

	public List<ToCounterTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<ToCounterTransaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "ToCounter [id=" + id + ", date=" + date + ", counter=" + counter + ", status=" + status
				+ ", fromFactoryId=" + fromFactoryId + "]";
	}

}
