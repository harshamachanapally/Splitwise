package com.splitwise.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name = "moneyspent")
@NamedQueries({
@NamedQuery(name = "moneyspent.byuserId", query = "from moneyspent where userId = :userId")})
/*,
@NamedQuery(name = "moneyspent.byuseremail", query = "select s from moneyspent s inner join "
									+ "Customers c on s.userId=c.userId where c.email = :email")*/
public class moneyspent {
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
