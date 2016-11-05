package com.splitwise.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.*;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity 
@Table
@NamedQueries( {
@NamedQuery(name = "Customers.byemail", query = "from Customers where email = :email"),
@NamedQuery(name = "Customers.byemailpass", query = "from Customers where email = :email and password= :password"),
@NamedQuery(name = "Customers.byuserId", query = "from Customers where userId = :userId")})
public class Customers {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private int userId;
	@Pattern(regexp = "[^0-9]*",message="Name should have only characters")
	@NotEmpty(message="Name cannot be empty")
	@Column(name="username",nullable=false)
	private String name;
	@Size(min=4,max=10,message="Password should be between 4 to 10 characters")
	@Column(nullable=false)
	@NotEmpty(message="password cannot be empty")
	private String password;
	@Column(unique=true,nullable=false)
	@Email(message="Incorrect EmailId")
	@NotEmpty(message="Email cannot be empty")
	private String email;
	@ManyToMany(cascade= CascadeType.MERGE,fetch= FetchType.EAGER)	
	@JoinTable(name="FriendsList",joinColumns = @JoinColumn(name="Customer_userId"),
									inverseJoinColumns = @JoinColumn(name="Friend_userId"),
			   uniqueConstraints = @UniqueConstraint(columnNames = {"Friend_userId", "Customer_userid"}))
	@GenericGenerator(name = "sequence-gen", strategy = "sequence")
	@CollectionId(columns = {@Column(name="Friend_Id")}, generator = "sequence-gen", type = @Type(type= "int"))
	private Collection<Customers> friend = new ArrayList<Customers>();
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Customers> getFriend() {
		return friend;
	}
	public void setFriend(Collection<Customers> friend) {
		this.friend = friend;
	}
	public void addfriends(Customers customer,Customers friend){
			customer.getFriend().add(friend);
			friend.getFriend().add(customer);
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public boolean equals(Object obj){
		Customers cust = (Customers) obj;
		if(this.email.equalsIgnoreCase(cust.getEmail())&&this.password.equalsIgnoreCase(cust.getPassword())
				&&this.name.equalsIgnoreCase(cust.name))
		return true;
		return false;
	}*/
	/*public int hashCode(){
		int result;
		result = (int)(this.email.hashCode()+this.name.hashCode()+this.password.hashCode());
		return result;
	}*/
	
}
