package com.splitwise.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name = "moneyborrowed")
/*@NamedQueries({
@NamedQuery(name = "moneyborrowed.byuserId", query = "from moneyborrowed where userId=:userId"),
@NamedQuery(name = "moneyborrowed.byuseremail", query = "select s from moneyborrowed s inner join "
												+ "Customers c on s.userId=c.userId where c.email = :email")}) */
public class moneyborrowed {
	@Id
	private int userId;
	private float Amount;
	public int getUserId() {
		return userId;
	}
	public float getAmount() {
		return Amount;
	}
	
}
