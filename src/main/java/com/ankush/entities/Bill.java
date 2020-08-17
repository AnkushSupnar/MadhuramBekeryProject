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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int billNo;
	double amount;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "counterId")
	Counter counter;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loginId")
	Login login;
	int customerId;
	LocalDateTime date;
	String payMode;
	String status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.EAGER)
	List<Transaction> transactionList = new ArrayList<>();

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public String toString() {
		return "Bill [billNo=" + billNo + ", amount=" + amount + ", counter=" + counter + ", login=" + login
				+ ", customerId=" + customerId + ", date=" + date + ", payMode=" + payMode + ", status=" + status
				+ ", transactionList=[" + "]";
	}

}
