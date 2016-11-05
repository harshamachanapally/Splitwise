package com.splitwise.pojo;

import java.util.ArrayList;
import java.util.List;

public class RecevTrans {
private int addedBy;
private int paidBy;
private String description;
private int amount;
private List<RecevTransreceivedBy> receivedBy = new ArrayList<RecevTransreceivedBy>();
public int getAddedBy() {
	return addedBy;
}
public void setAddedBy(int addedBy) {
	this.addedBy = addedBy;
}
public int getPaidBy() {
	return paidBy;
}
public void setPaidBy(int paidBy) {
	this.paidBy = paidBy;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public List<RecevTransreceivedBy> getReceivedBy() {
	return receivedBy;
}
public void setReceivedBy(List<RecevTransreceivedBy> receivedBy) {
	this.receivedBy = receivedBy;
}



}
