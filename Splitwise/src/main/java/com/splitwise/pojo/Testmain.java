package com.splitwise.pojo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;

import com.splitwise.service.ServiceLayer;

public class Testmain {
	public static void main(String[] args) {
		/*ServiceLayer S = new ServiceLayer();
		S.saveTransaction("Trans",100,13,13,1);*/
	}
}
	//public static void main(String[] args) {
/*	float sum = 0;
	FriendsList Friend = new FriendsList();
	try{
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.println("Enter Emailid :");
		Scanner input = new Scanner(System.in);
		String email = input.nextLine();
		session.beginTransaction();
		Query custquery = session.getNamedQuery("Customers.byemail");
		custquery.setString(0,email);
		List<Customers> customerlist = (List<Customers>) custquery.list();
		if(!customerlist.isEmpty()){
			Customers user = new Customers();
			user = customerlist.get(0);
			Collection<Customers> Friend1 = user.getFriend();
			Friend1.size();
			Query query = session.getNamedQuery("moneyspent.byuserId");
			query.setInteger(0,user.getUserId());
			Query query1 = session.getNamedQuery("moneyborrowed.byuserId");
			query1.setInteger(0,user.getUserId());
			List<moneyspent> customer = (List<moneyspent>) query.list();
			List<moneyborrowed> customer1 = (List<moneyborrowed>) query1.list();
			System.out.println("Welcome "+ user.getName()+"!!!");
			if(customer.isEmpty()){
				System.out.println("Money spent is : 0");
			}
			else{
				System.out.println("Money spent is : "+customer.get(0).getAmount());
				sum = sum + customer.get(0).getAmount();
			}
			if(customer1.isEmpty()){
				System.out.println("Money Borrowed is : 0");
			}
			else{
				System.out.println("Money Borrowed is : "+customer1.get(0).getAmount());
				sum = sum + customer1.get(0).getAmount();
			}
			System.out.println("Total NetBalance is :"+sum);
			System.out.println("---------------------------------");
			System.out.println("Friends are :");
			for(Customers c : Friend1){
				System.out.print(c.getName()+"	");
				List<FriendsList> list = Friend.getFriendsId(user, c);
				Iterator<FriendsList> it = list.iterator();
				List<Integer> L = new ArrayList<Integer>(); 
				while(it.hasNext())
				{
					L.add(it.next().getFriend_Id());
				}
				Query q = session.getNamedQuery("transummary.byfriendid");
				q.setParameterList("trans", L);
				List<Double> T1 = (List<Double>) q.list();
				Double sum1 = T1.get(0);
				System.out.println(sum1);
				System.out.println();
			}
		}
		else{
			System.out.println(email + " is not a valid email id");
		}
		session.getTransaction().commit();
		session.close();
				
	}
	
		catch(Exception e){ 
			System.out.println(e.getMessage());
		}
}
}
*/