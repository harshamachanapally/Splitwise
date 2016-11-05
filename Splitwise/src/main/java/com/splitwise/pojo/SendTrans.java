package com.splitwise.pojo;

public class SendTrans {
private int tranId;
private int receiptId;
private String addedBy;
private String description;
private String paidBy;
private String receivedBy;
private float amount;

public String getAddedBy() {
	return addedBy;
}

public void setAddedBy(String addedBy) {
	this.addedBy = addedBy;
}



public SendTrans(int tranId, int receiptId, String addedBy, String description, String paidBy, String receivedBy,
		float amount) {
	super();
	this.tranId = tranId;
	this.receiptId = receiptId;
	this.addedBy = addedBy;
	this.description = description;
	this.paidBy = paidBy;
	this.receivedBy = receivedBy;
	this.amount = amount;
}

public SendTrans(){
	
}

public int getTranId() {
	return tranId;
}

public void setTranId(int tranId) {
	this.tranId = tranId;
}



public int getReceiptId() {
	return receiptId;
}

public void setReceiptId(int receiptId) {
	this.receiptId = receiptId;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getPaidBy() {
	return paidBy;
}

public void setPaidBy(String paidBy) {
	this.paidBy = paidBy;
}

public String getReceivedBy() {
	return receivedBy;
}

public void setReceivedBy(String receivedBy) {
	this.receivedBy = receivedBy;
}

public float getAmount() {
	return amount;
}

public void setAmount(float amount) {
	this.amount = amount;
}



}
