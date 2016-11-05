package com.splitwise.pojo;


import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "friendBalanceStoreProcedure",
	query = "CALL getFriendsBalance(:customerId)",
	resultClass = FriendsBalance.class)})
@Table(name="FriendsBalance")
public class FriendsBalance implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9097516953321465918L;
	@Id
	private int friendId;
	private String name;
	private float amount;
	@Transient
	private String message;
	@Transient
	private boolean flag;
	
	
	
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
