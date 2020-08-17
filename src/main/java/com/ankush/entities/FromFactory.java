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
public class FromFactory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	LocalDateTime date;
	@OneToOne(cascade = CascadeType.ALL)
	Counter counter;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "factory", fetch = FetchType.EAGER)
	List<FromFactoryTransaction> transactions = new ArrayList<>();
	String status;

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

	public List<FromFactoryTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<FromFactoryTransaction> transactions) {
		this.transactions = transactions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FromFactory [id=" + id + ", date=" + date + ", counter=" + counter + ", status=" + status + "]";
	}
}
