	package com.splitwise.service;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.splitwise.exception.DataNotFoundException;
import com.splitwise.pojo.Customers;
import com.splitwise.pojo.FriendsBalance;
import com.splitwise.pojo.FriendsBalanceList;
import com.splitwise.pojo.FriendsList;
import com.splitwise.pojo.Receipt;
import com.splitwise.pojo.RecevTrans;
import com.splitwise.pojo.RecevTransreceivedBy;
import com.splitwise.pojo.SendReceipt;
import com.splitwise.pojo.SendReceiptTrans;
import com.splitwise.pojo.SendTrans;
import com.splitwise.pojo.Transaction;
import com.splitwise.pojo.moneyborrowed;
import com.splitwise.pojo.moneyspent;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServiceLayer implements ServiceLayerInterface {
	
	SessionFactory sessionFactory;
	
	Query query;
	
	@Autowired
	  public ServiceLayer(EntityManagerFactory factory) {
	    if(factory.unwrap(SessionFactory.class) == null){
	      throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.sessionFactory = factory.unwrap(SessionFactory.class);
	  }
	
	/*//To check user exits for Login page
	public boolean UserAuthentication(Customers customer){
		Session session = sessionFactory.openSession();
		query = session.getNamedQuery("Customers.byemailpass");
		query.setParameter("email", customer.getEmail());
		query.setParameter("password", customer.getPassword());
		List<Customers> list = query.list();
		return list.isEmpty();
	}*/
	//To check if Email is already used for Signup page
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getCustomerById(int)
	 */
	@Override
	public Customers getCustomerById(int Id){
		
		Session session = sessionFactory.openSession();
		query = session.getNamedQuery("Customers.byuserId");
		query.setParameter("userId", Id);
		List<Customers> list = query.list();
		if(list.isEmpty())
		{
			session.close();
		throw new DataNotFoundException("CustomerId "+Id+" doesn't Exist");
		}
		list.get(0).getFriend().size();
		session.close();
		return list.get(0);
	}
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#EmailCheck(java.lang.String)
	 */
	@Override
	public Customers EmailCheck(String email) throws DataNotFoundException{
		System.out.println("Email started");
		Session session = sessionFactory.openSession();
		query = session.getNamedQuery("Customers.byemail");
		query.setParameter("email", email);
		List<Customers> list = query.list();
		if(list.isEmpty())
		{
			session.close();
			throw new DataNotFoundException("Email Id does not Exist");
		}
		list.get(0).getFriend().size();
		session.close();
		System.out.println("email started");
		return list.get(0);
	}
	//To get Customer object by passing Customer
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getCustomer(com.splitwise.pojo.Customers)
	 */
	@Override
	public Customers getCustomer(Customers customer) throws DataNotFoundException{
		Session session = sessionFactory.openSession();
		query = session.getNamedQuery("Customers.byemailpass");
		query.setParameter("email", customer.getEmail());
		query.setParameter("password", customer.getPassword());
		List<Customers> list = query.list();
		if(list.isEmpty())
		{
			session.close();
			throw new DataNotFoundException("Incorrect Credentials");
		}
		list.get(0).getFriend().size();
		session.close();
		return list.get(0);
	}
	//To save a customer after signup
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#saveCustomer(com.splitwise.pojo.Customers)
	 */
	@Override
	@Transactional(propagation = Propagation.MANDATORY, readOnly = false)
	public void saveCustomer(Customers customer){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
		session.close();
		
		//throw new RuntimeException("Roll back should happen");
	}
	//Get the Balances of a Customer on Welcome Page
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getBalances(java.util.List)
	 */
	@Override
	public List<Float> getBalances(List<FriendsBalance> Balances){
		
			List<Float> result = new ArrayList<Float>();
			Float Gave=(float) 0,Recev=(float) 0;
			for(FriendsBalance B : Balances){
				System.out.println(B.getAmount());
				if(B.getAmount()>0)
					Gave = Gave + B.getAmount();
				else
					Recev = Recev +B.getAmount();
			}

			Float net = Gave+Recev;
			result.add(Gave);
			result.add(-1*Recev);
			result.add(net);
			
		return result;
		/*Session session = sessionFactory.openSession();
		List<Float> bal = new ArrayList<Float>();
		Query query = session.getNamedQuery("moneyspent.byuserId");
		query.setParameter("userId", customer.getUserId());
		if(!query.list().isEmpty())
		{
		moneyspent gave = (moneyspent) query.list().get(0);
		bal.add(gave.getAmount());
		}
		else{
			bal.add((float) 0);
		}
		Query query1 = session.getNamedQuery("moneyborrowed.byuserId");
		query1.setParameter("userId", customer.getUserId());
		if(!query1.list().isEmpty())
		{
		moneyborrowed received = (moneyborrowed) query1.list().get(0);
		bal.add(received.getAmount());
		}
		else{
			bal.add((float) 0);
		}
		bal.add(bal.get(0)-bal.get(1));
		session.close();
		*/
		//return bal;
	}
	
	//Returns List of friends with net balances for a customer
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getFriendsBalance(com.splitwise.pojo.Customers)
	 */
	@Override
	public List<FriendsBalance> getFriendsBalance(Customers customer){
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("friendBalanceStoreProcedure");		
		query.setParameter("customerId", customer.getUserId());
		List<FriendsBalance> result = query.list();
		session.close();
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getFriendsList(int)
	 */
	@Override
	public List<FriendsList> getFriendsList(int j){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from FriendsList where Customer_user_Id in( "+j+")" +"or Friend_user_Id in( "+j+")");
		List<FriendsList> friends = (List<FriendsList>) query.list();
		/*Query query = session.getNamedQuery("friendslist.byIds");
		query.setParameter("customerId", customerId);
		query.setParameter("friendId", friendId);
		List<FriendsList> list = (List<FriendsList>) query.list();
		*/
		//System.out.println(list.get(0).getFriendId());
		return friends;
	}
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getTransactions(java.util.List)
	 */
	@Override
	public List<SendTrans> getTransactions(List<FriendsList> list){
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("Transaction.byFriendId");
		query.setParameterList("friendId", list);
		List<Transaction> list1 = query.list();
		System.out.println(list1.get(0).getFriendId().getCustomerUserId().getFriend().size());
		//System.out.println(list1.get(0).getReceiptId().getTransactions().size());
		session.close();
		int count =1;
		List<SendTrans> trans = new ArrayList<SendTrans>(); 
		for(Transaction tran: list1){
			if(tran.getType())
			{
			trans.add(new SendTrans(tran.getTranId(),tran.getReceiptId().getReceiptId(),tran.getAddedBy().getName(),tran.getDescription(),
									tran.getFriendId().getCustomerUserId().getName(),tran.getFriendId().getFriendUserId().getName(),
									tran.getAmount()));
			}
			else{	
				trans.add(new SendTrans(tran.getTranId(),tran.getReceiptId().getReceiptId(),tran.getAddedBy().getName(),tran.getDescription(),
						tran.getFriendId().getFriendUserId().getName(),tran.getAddedBy().getName(),
						tran.getAmount()));
			}
			
			count++;
		}
		System.out.println("Size "+trans.size());
		System.out.println("getTrans Completed");
		return trans;
	}
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getList(java.util.List)
	 */
	@Override
	public <T> FriendsBalanceList<T> getList(List<T> list){
		FriendsBalanceList<T> list1 = new FriendsBalanceList<T>();
		list1.setFriendBalanceList(list);
		return list1;
	}
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#addFriend(com.splitwise.pojo.Customers, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED, readOnly = false)
	public void addFriend(Customers customer,String addfriend) throws DataNotFoundException{
		System.out.println("addfriend started");
		if(customer.getEmail().equalsIgnoreCase(addfriend)){
			throw new DataNotFoundException("Cannot add your self");
		}
		Customers friend = EmailCheck(addfriend);
		customer.getFriend().add(friend);
		friend.getFriend().add(customer);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(customer);
		session.getTransaction().commit();
		session.close();
		System.out.println("addfriend ended");
		
		throw new RuntimeException("Roll back");
	}
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#updateCustomer(com.splitwise.pojo.Customers)
	 */
	@Override
	public void updateCustomer(Customers customer){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("UPDATE customerdetails SET email = ?, "
												+ "name = ?,password = ?"
												+ "WHERE `userId` = ?");
		query.setString(0, customer.getEmail());
		query.setString(1, customer.getName());
		query.setString(2, customer.getPassword());
		query.setInteger(3, customer.getUserId());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#getReceiptDetails(int)
	 */
	@Override
	public SendReceipt getReceiptDetails(int receiptId){
		Session session = sessionFactory.openSession();
		Receipt receipt;
		session.beginTransaction();
		receipt = session.get(Receipt.class,receiptId);
		receipt.getTransactions().size();
		session.close();
		SendReceipt sendReceipt = new SendReceipt(receipt.getReceiptId(),receipt.getTotalAmount());
		int count=1;
		for(Transaction t:receipt.getTransactions()){
			if(count==1){
				sendReceipt.setDescription(t.getDescription());
				sendReceipt.setAddedBy(t.getAddedBy().getName());
				sendReceipt.setAddedById(t.getAddedBy().getUserId());
				sendReceipt.setPaidBy(t.getFriendId().getCustomerUserId().getName());
				sendReceipt.setPaidById(t.getFriendId().getCustomerUserId().getUserId());
				count++;
			}
			SendReceiptTrans st;
			if(t.getType())
			st = new SendReceiptTrans(t.getTranId(), t.getFriendId().getFriendUserId().getName(),t.getFriendId().getFriendUserId().getUserId()
														,t.getAmount());
			else
			st = new SendReceiptTrans(t.getTranId(), t.getAddedBy().getName(),t.getAddedBy().getUserId()
						,t.getAmount());
			sendReceipt.getTrans().add(st);
		}
		return sendReceipt;
	}
	/*public Receipt saveReceipt(float amount){
		Receipt receipt = new Receipt();
		receipt.setTotalAmount(amount);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(receipt);
		session.getTransaction().commit();
		Query query = session.getNamedQuery("Receipt.getMaxId");
		List<Integer> receipts = query.list();
		receipt.setReceiptId(receipts.get(0));
		session.close();
		return receipt;
	}*/
	
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#saveReceipt(com.splitwise.pojo.Receipt)
	 */
	@Override
	public boolean saveReceipt(Receipt receipt){
		System.out.println("Save receipt Started");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(receipt);
		System.out.println("Saved");
		session.flush();
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#addTransactions(com.splitwise.pojo.RecevTrans)
	 */
	@Override
	public Receipt addTransactions(RecevTrans recevTrans){
		Session session = sessionFactory.openSession();
		Receipt receipt = new Receipt();
		receipt.setTotalAmount(recevTrans.getAmount());
		List<RecevTransreceivedBy> receivedBy = recevTrans.getReceivedBy();
		
		int count = 1;
		for(RecevTransreceivedBy RT: receivedBy){
			Transaction transaction = new Transaction();
			transaction.setAddedBy(getCustomerById(recevTrans.getAddedBy()));
			transaction.setAmount(RT.getAmount());
			transaction.setDescription(recevTrans.getDescription());
			transaction.setReceiptId(receipt);
			
			Query query1;
			if(recevTrans.getAddedBy()== RT.getUserId()){
				transaction.setType(false);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+RT.getUserId()+")" +"and Friend_user_Id in( "+recevTrans.getPaidBy()+")");
			}
			else {
				transaction.setType(true);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+recevTrans.getPaidBy()+")" +"and Friend_user_Id in( "+RT.getUserId()+")");
				List<FriendsList> friendId = query1.list();
				
				if(friendId.isEmpty()){
					Customers customer = getCustomerById(recevTrans.getPaidBy());
					Customers customer1 = getCustomerById(RT.getUserId());
					session.beginTransaction();
					addFriend(customer, customer1.getEmail());
					addFriend(customer1, customer.getEmail());
					session.getTransaction().commit();
					query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+recevTrans.getPaidBy()+")" +"and Friend_user_Id in( "+RT.getUserId()+")");
					List<FriendsList> friendId1 = query1.list();
					System.out.println(friendId1.get(0).getFriendId());
				}
				
			}
			
			List<FriendsList> friendId1 = query1.list();
			transaction.setFriendId(friendId1.get(0));
			receipt.getTransactions().add(transaction);
			count++;
		}
		session.close();
		return receipt;
	}
	
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#updateReceipt(com.splitwise.pojo.Receipt)
	 */
	@Override
	public Receipt updateReceipt(Receipt receipt){
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(receipt);
		session.getTransaction().commit();
		session.close();
		return receipt;
	}
	/*public Transaction saveTransaction(RecevTrans recevTrans,Receipt receipt){
		
		List<RecevTransreceivedBy> receivedBy = recevTrans.getReceivedBy();
		Transaction transaction = new Transaction();
		int count = 1;
		for(RecevTransreceivedBy RT: receivedBy){
			transaction.setAddedBy(getCustomerById(recevTrans.getAddedBy()));
			transaction.setAmount(RT.getAmount());
			transaction.setDescription(recevTrans.getDescription());
			transaction.setReceiptId(receipt);
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query1;
			if(recevTrans.getAddedBy()== RT.getUserId()){
				transaction.setType(false);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+RT.getUserId()+")" +"and Friend_user_Id in( "+recevTrans.getPaidBy()+")");
			}
			else {
				transaction.setType(true);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+recevTrans.getPaidBy()+")" +"and Friend_user_Id in( "+RT.getUserId()+")");
				List<FriendsList> friendId = query1.list();
				
				if(friendId.isEmpty()){
					Customers customer = getCustomerById(recevTrans.getPaidBy());
					Customers customer1 = getCustomerById(RT.getUserId());
					addFriend(customer, customer1.getEmail());
					addFriend(customer1, customer.getEmail());
					session.getTransaction().commit();
					session.beginTransaction();
					query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+recevTrans.getPaidBy()+")" +"and Friend_user_Id in( "+RT.getUserId()+")");
					List<FriendsList> friendId1 = query1.list();
					System.out.println(friendId1.get(0).getFriendId());
				}
				
			}
			
			List<FriendsList> friendId1 = query1.list();
			System.out.println("FriendId "+friendId1.get(0).getFriendId());
			transaction.setFriendId(friendId1.get(0));
			session.save(transaction);
			session.getTransaction().commit();
			session.close();
			System.out.println("Coount"+ count);
			count++;
		}
		
		return transaction;
	}*/
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#updateTransaction1(com.splitwise.pojo.SendReceipt, int)
	 */
	@Override
	public Receipt updateTransaction1(SendReceipt sendReceipt,int id){
		Session session = sessionFactory.openSession();
		System.out.println("Service Started");
		List<SendReceiptTrans> updatedTrans = (List<SendReceiptTrans>) sendReceipt.getTrans();
		System.out.println("updatedTrans completed");
		Receipt receipt = new Receipt();
		receipt.setTotalAmount(sendReceipt.getTotalAmount());
		receipt.setReceiptId(id);
		for(SendReceiptTrans RT: updatedTrans){
			Transaction transaction = new Transaction();
			transaction.setTranId(RT.getTranId());
			transaction.setAddedBy(getCustomerById(sendReceipt.getAddedById()));
			transaction.setAmount(RT.getAmount());
			transaction.setDescription(sendReceipt.getDescription());
			transaction.setReceiptId(receipt);
			Query query1;
			if(sendReceipt.getAddedById()== RT.getReceivedById()){
				transaction.setType(false);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+RT.getReceivedById()+")" +"and Friend_user_Id in( "+sendReceipt.getPaidById()+")");
				System.out.println("IF" + RT.getReceivedById()+" "+sendReceipt.getPaidById());
			}
			else {
				transaction.setType(true);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+sendReceipt.getPaidById()+")" +"and Friend_user_Id in( "+RT.getReceivedById()+")");
				System.out.println(sendReceipt.getPaidById()+" "+RT.getReceivedById());
				}
			List<FriendsList> friendId1 = query1.list();
			System.out.print("friendId obtained ");
			System.out.println(friendId1.get(0).getFriendId()) ;
			transaction.setFriendId(friendId1.get(0));
			receipt.getTransactions().add(transaction);
		}
		session.close();
		return receipt;
	}
/* (non-Javadoc)
 * @see com.splitwise.service.ServiceLayerInterface#updateTransaction(com.splitwise.pojo.SendReceipt, com.splitwise.pojo.Receipt)
 */
@Override
public Transaction updateTransaction(SendReceipt sendReceipt,Receipt receipt){
		System.out.println("Service Started");
		List<SendReceiptTrans> updatedTrans = (List<SendReceiptTrans>) sendReceipt.getTrans();
		System.out.println("updatedTrans completed");
		Transaction transaction = new Transaction();
		for(SendReceiptTrans RT: updatedTrans){
			transaction.setTranId(RT.getTranId());
			transaction.setAddedBy(getCustomerById(sendReceipt.getAddedById()));
			transaction.setAmount(RT.getAmount());
			transaction.setDescription(sendReceipt.getDescription());
			transaction.setReceiptId(receipt);
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query1;
			if(sendReceipt.getAddedById()== RT.getReceivedById()){
				transaction.setType(false);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+RT.getReceivedById()+")" +"and Friend_user_Id in( "+sendReceipt.getPaidById()+")");
				System.out.println("IF" + RT.getReceivedById()+" "+sendReceipt.getPaidById());
			}
			else {
				transaction.setType(true);
				query1 = session.createQuery("from FriendsList where Customer_user_Id in( "+sendReceipt.getPaidById()+")" +"and Friend_user_Id in( "+RT.getReceivedById()+")");
				System.out.println(sendReceipt.getPaidById()+" "+RT.getReceivedById());
				}
			List<FriendsList> friendId1 = query1.list();
			System.out.print("friendId obtained ");
			System.out.println(friendId1.get(0).getFriendId()) ;
			transaction.setFriendId(friendId1.get(0));
			session.update(transaction);
			System.out.println("updated sucess");
			session.getTransaction().commit();
			session.close();
		}
		return transaction;
	}
	
	/* (non-Javadoc)
	 * @see com.splitwise.service.ServiceLayerInterface#deleteReceipt(java.lang.Class, int)
	 */
	@Override
	public boolean deleteReceipt(Class<?> type, int receiptId){
		Session session  = sessionFactory.openSession();
		boolean result = false;
		session.beginTransaction();
		Object object = session.load(type, receiptId);
		
		if(object!=null){
			session.delete(object);
			result = true;
		}
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	
//	public void saveTransaction(String Desc,float Amount,int userId,int paidId,int recId){
//		Transaction tran = new Transaction();
//		boolean type = false;
//		int Fri = paidId;
//		if(userId==paidId)
//		{
//			type = true;
//			Fri = recId;
//		}
//		Session session = sessionFactory.openSession();
//		Query query = session.getNamedQuery("Transaction.getMaxId");
//		List<Integer> receipt = query.list();
//		int receiptId;
//		if(receipt.get(0) == null)
//		{
//			receiptId=1;
//		}
//		else
//		{
//			receiptId = receipt.get(0);
//		}
//		Query query1 = session.createQuery("from FriendsList where Customer_userId in( "+userId+")" +"and Friend_userId in( "+Fri+")");
//		List<FriendsList> friendId = query1.list();
//		tran.setAddedBy(getCustomerById(userId));
//		tran.setAmount(Amount);
//		tran.setDescription(Desc);
//		tran.setFriendId(friendId.get(0));
//		tran.setReceiptId(receiptId+1);
//		tran.setType(type);
//		session.beginTransaction();
//		session.save(tran);
//		session.getTransaction().commit();
//		session.close();
//	}
}
