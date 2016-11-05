package com.splitwise.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity 
@Table (name = "transaction")
@NamedQueries({@NamedQuery (name = "Transaction.byFriendId", query = "from Transaction where Friend_Id in (:friendId)"),
				@NamedQuery (name = "Transaction.getMaxId", query = "select Max(receiptId)from Transaction")})

public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tranId")
	private int tranId;
	@ManyToOne
	@JoinColumn(name = "ReceiptId")
	private Receipt receiptId;
	@ManyToOne
	@JoinColumn(name = "Friend_Id")
	private FriendsList friendId;
	@ManyToOne
	@JoinColumn(name = "AddedBy")
	private Customers addedBy;
	@Column(name = "Type")
	@NotNull
	private boolean type;
	@Column(name = "Amount")
	@NotNull
	private float amount;	
	@Column(name = "Description")
	@NotEmpty
	private String description;
	@Transient
	private float totalAmount; 
	
	public int getTranId() {
		return tranId;
	}
	public void setTranId(int tranId) {
		this.tranId = tranId;
	}
	public Receipt getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Receipt receiptId) {
		this.receiptId = receiptId;
	}
	public FriendsList getFriendId() {
		return friendId;
	}
	public void setFriendId(FriendsList friendId) {
		this.friendId = friendId;
	}
	public Customers getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(Customers addedBy) {
		this.addedBy = addedBy;
	}
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

	
}
