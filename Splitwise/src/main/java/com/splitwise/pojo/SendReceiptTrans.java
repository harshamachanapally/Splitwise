package com.splitwise.pojo;

public class SendReceiptTrans {
	private int tranId;
	private String receivedBy;
	private int receivedById;
	private float amount;
	
	public SendReceiptTrans() {
		
		
	}

	public SendReceiptTrans(int tranId, String receivedBy,int receivedById, float amount) {
		super();
		this.tranId = tranId;
		this.receivedBy = receivedBy;
		this.amount = amount;
		this.receivedById = receivedById;
	}
	
	public int getTranId() {
		return tranId;
	}
	public void setTranId(int tranId) {
		this.tranId = tranId;
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

	public int getReceivedById() {
		return receivedById;
	}

	public void setReceivedById(int receivedById) {
		this.receivedById = receivedById;
	}
	
	
}
