package com.splitwise.pojo;

import java.util.List;

import javax.persistence.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.Configuration;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name = "friendslist")
@NamedQuery(name = "friendslist.byIds", query = "from FriendsList where customerUserId in (:customerId,:friendId) "
												+ "and friendUserId in (:customerId,:friendId)")
public class FriendsList {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="Friend_Id")
		private int friendId;
		@ManyToOne
		@JoinColumn(name = "Customer_userId")
		@JsonBackReference
		private Customers customerUserId;
		@ManyToOne
		@JoinColumn(name = "Friend_userId")
		private Customers friendUserId;
		
		public int getFriendId() {
			return friendId;
		}
		public void setFriendId(int friendId) {
			this.friendId = friendId;
		}
		public Customers getCustomerUserId() {
			return customerUserId;
		}
		public void setCustomerUserId(Customers customerUserId) {
			this.customerUserId = customerUserId;
		}
		public Customers getFriendUserId() {
			return friendUserId;
		}
		public void setFriendUserId(Customers friendUserId) {
			this.friendUserId = friendUserId;
		}
		public List<FriendsList> getFriendsId(int j,int i){
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from FriendsList where Customer_userId in( "+j+","+i+")" +"and Friend_userId in( "+j+","+i+")");
			List<FriendsList> friends = (List<FriendsList>) query.list();
			session.getTransaction().commit();
			session.close();
			return friends;
		}
		public FriendsList getOneFriendsId(Customers customer,Customers customer1){
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from FriendsList where Customer_userId in( "+customer.getUserId()+")" +"and Friend_userId in( "+customer1.getUserId()+")");
			List<FriendsList> friends = (List<FriendsList>) query.list();
			session.getTransaction().commit();
			session.close();
			return friends.get(0);
		}
}
