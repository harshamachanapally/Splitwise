package com.splitwise.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="receipt")
@NamedQueries({@NamedQuery(name = "Receipt.getMaxId", query = "select max(receiptId) from Receipt")})
public class Receipt {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int receiptId;
	private float totalAmount;
	@OneToMany(mappedBy="receiptId", cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private Collection<Transaction> transactions = new ArrayList<Transaction>();
	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Collection<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Collection<Transaction> transactions) {
		this.transactions = transactions;
	}
}
