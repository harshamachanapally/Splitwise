package com.splitwise.pojo;

import java.util.ArrayList;
import java.util.Collection;

public class SendReceipt {
	
	private int receiptId;
	private float totalAmount;
	private String description;
	private String addedBy;
	private int addedById;
	private String paidBy;
	private int paidById;
	private Collection<SendReceiptTrans> trans = new ArrayList<SendReceiptTrans>();
	
	public SendReceipt(){
		
	}
	
	public SendReceipt(int receiptId, float totalAmount) {
		this.receiptId = receiptId;
		this.totalAmount = totalAmount;
	}
	
	public int getAddedById() {
		return addedById;
	}

	public void setAddedById(int addedById) {
		this.addedById = addedById;
	}

	public int getPaidById() {
		return paidById;
	}

	public void setPaidById(int paidById) {
		this.paidById = paidById;
	}

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public String getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	public Collection<SendReceiptTrans> getTrans() {
		return trans;
	}
	public void setTrans(Collection<SendReceiptTrans> trans) {
		this.trans = trans;
	}
	
}
