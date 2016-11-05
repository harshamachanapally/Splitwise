package com.splitwise.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.pojo.Customers;
import com.splitwise.pojo.FriendsBalance;
import com.splitwise.pojo.FriendsBalanceList;
import com.splitwise.pojo.FriendsList;
import com.splitwise.pojo.Receipt;
import com.splitwise.pojo.RecevTrans;
import com.splitwise.pojo.RecevTransreceivedBy;
import com.splitwise.pojo.SendReceipt;
import com.splitwise.pojo.SendTrans;
import com.splitwise.pojo.Transaction;
import com.splitwise.service.ServiceLayerInterface;



@RestController
@RequestMapping("/Splitwise")
public class RestServiceController {
	
	@Autowired
	ServiceLayerInterface service;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Customers Login(@RequestBody Customers customer){
		System.out.println("Login");
		Customers customer1 = service.getCustomer(customer);
		return customer1;
	}
	
	
	@RequestMapping(value = "/netbalances",method = RequestMethod.GET)
	public List<Float> getNetBalances(@RequestParam("id") int id){
		System.out.println("NetBalances");
		Customers customer = service.getCustomerById(id); 
		List<FriendsBalance> list = (List<FriendsBalance>) service.getFriendsBalance(customer);
		List<Float> balances = 	service.getBalances(list);
		return balances;
	}
	
	@RequestMapping(value = "/friendsBalances",method = RequestMethod.GET)
	public List<FriendsBalance> getFriendsBalances(@RequestParam("id") int id){
		System.out.println("FriendsBalances");
		Customers customer = service.getCustomerById(id); 
		List<FriendsBalance> friendsBalances = service.getFriendsBalance(customer);
		for(FriendsBalance bal:friendsBalances){
			if(bal.getAmount()>0)
			{
				bal.setFlag(true);
				bal.setMessage("Owes You");
			}
			else if(bal.getAmount()==0){
				bal.setFlag(true);
				bal.setMessage("Setteled");
			}
			else
			{
				bal.setFlag(false);
				bal.setAmount(-1*bal.getAmount());
				bal.setMessage("You Owe");
			}
		}
		return friendsBalances;
	}
	
	/*public void autoSettleUp(@RequestParam("id") int id, @RequestBody List<FriendsBalance> balances){
		
		Customers customer = service.getCustomerById(id); 
		
		//List<FriendsBalance> friendsBalances = service.getFriendsBalance(customer);
		
		Map<Integer,Float> toReceive = new HashMap<Integer,Float>();
		Map<Integer,Float> toGive = new HashMap<Integer,Float>();
		
		for(FriendsBalance friendsBalance: balances){
			if(friendsBalance.getAmount()>0){
				toReceive.put(friendsBalance.getFriendId(), friendsBalance.getAmount());
			}
			else if(friendsBalance.getAmount()<0){
				toGive.put(friendsBalance.getFriendId(), Math.abs(friendsBalance.getAmount()) );
			}
		}
		
		if( !(toReceive.isEmpty()||toGive.isEmpty())){
			List<Receipt> receipts = new ArrayList<Receipt>();
			if(toGive.size()>toReceive.size()){
				for(Integer customerId: toReceive.keySet()){
					List<RecevTrans> recevTrans = new ArrayList<RecevTrans>();
					Customers friend = service.getCustomerById(customerId); 
					List<FriendsBalance> friendsBalances = service.getFriendsBalance(friend);
					for(FriendsBalance friendbalance: friendsBalances){
						if(friendbalance.getAmount()>0 && toGive.containsKey(friendbalance.getFriendId())){
							
							//find the balance which can be settled
							float min = Math.min(friendbalance.getAmount()
														, Math.min(toReceive.get(customerId), toGive.get(friendbalance.getFriendId()))) ;
							toReceive.put(customerId, toReceive.get(customerId)-min);
							toGive.put(friendbalance.getFriendId(), toGive.get(friendbalance.getFriendId())-min);
							Receipt receipt = new Receipt();
							receipt.setTotalAmount(min);
							transactions.add(new Transaction());
							//receipts.add();	
						}
					}
				}
			}
			else{
				for(Integer customerId: toGive.keySet()){
					Customers friend = service.getCustomerById(customerId); 
					List<FriendsBalance> friendsBalances = service.getFriendsBalance(friend);
					for(FriendsBalance friendbalance: friendsBalances){
						if(friendbalance.getAmount()<0 && toReceive.containsKey(friendbalance.getFriendId())){
							
							//find the balance which can be settled
							float min = Math.min( Math.abs(friendbalance.getAmount())
														, Math.min(toReceive.get(customerId), toGive.get(friendbalance.getFriendId()))) ;
							toGive.put(customerId, toGive.get(customerId)-min);
							toReceive.put(friendbalance.getFriendId(), toReceive.get(friendbalance.getFriendId())-min);
							transactions.add(new Transaction());	
						}
					}
				}
			}
			
		}
		
		
	}*/
	
	public void settletransactions(Map<Integer,Float> map1,Map<Integer,Float> map2,Customers customer){
		
	}
	
	@RequestMapping(value = "/friends",method = RequestMethod.GET)
	public List<Customers> getFriends(@RequestParam("id") int id){
		System.out.println("Friends");
		Customers customer = service.getCustomerById(id); 
		List<Customers> friends = (List<Customers>) customer.getFriend();
		List<Customers> friends1 = new ArrayList<Customers>();
		for(Customers frnd:friends){
			Customers c = new Customers();
			c.setUserId(frnd.getUserId());
			c.setName(frnd.getName());
			c.setEmail(frnd.getEmail());
			friends1.add(c);
		}
		return friends1;
	}
	
	@RequestMapping(value = "/addfriend",method = RequestMethod.POST)
	public Customers addfriend(@RequestBody Customers friend,@RequestParam("id") int id){
		System.out.println("addfriend");
		Customers customer = service.getCustomerById(id);
		System.out.println("addfriend1");
		service.addFriend(customer, friend.getEmail());
		System.out.println("addfriend2");
		Customers friend1 = service.EmailCheck(friend.getEmail());
		service.addFriend(friend1, customer.getEmail());
		Customers c = new Customers();
		c.setUserId(friend1.getUserId());
		c.setName(friend1.getName());
		c.setEmail(friend1.getEmail());
		return c;
	}
	
	@RequestMapping(value = "/transactions",method = RequestMethod.GET)
	public List<SendTrans> getTransactions(@RequestParam("id") int id){
		System.out.println("Transactions");
		List<FriendsList> friendslist = service.getFriendsList(id);
		List<SendTrans> transaction = service.getTransactions(friendslist);
		return transaction;
	}
	
	@RequestMapping(value = "/receiptdetails",method = RequestMethod.GET)
	public SendReceipt getReceiptDetails(@RequestParam("id") int id){
		System.out.println("ReceiptId"+id);
		System.out.println("ReceiptDetails Started");
		SendReceipt sendReceipt = (SendReceipt) service.getReceiptDetails(id);
		System.out.println(sendReceipt.getAddedBy());
		System.out.println(sendReceipt.getDescription());
		System.out.println("Added"+sendReceipt.getAddedById());
		System.out.println(sendReceipt.getAddedById()+1);
		System.out.println("Paid"+sendReceipt.getPaidById());
		System.out.println(sendReceipt.getPaidById()+1);
		System.out.println("Receipt"+sendReceipt.getReceiptId());
		System.out.println(sendReceipt.getReceiptId()+1);
		
		System.out.println("ReceiptDetails Ended");
		return sendReceipt;
	}
	
	/*@RequestMapping(value = "/editreceipt",method = RequestMethod.GET)
	public SendReceipt updateReceiptDetails(@RequestParam("id") int id){
		SendReceipt sendReceipt = (SendReceipt) service.getReceiptDetails(id); 
		return sendReceipt;
	}*/
	
	@RequestMapping(value = "/transactions",method = RequestMethod.POST, headers = "Accept=application/json")
	public SendReceipt getAddTransaction(@RequestBody RecevTrans transaction){
		 System.out.println("AddTransaction");
		System.out.println("Added: "+transaction.getAddedBy());
		System.out.println("Amount: "+transaction.getAmount());
		System.out.println("Description: "+transaction.getDescription());
		System.out.println("PaidBy: "+transaction.getPaidBy());
		System.out.println("ReceivedBy: ");
		List<RecevTransreceivedBy>  L = transaction.getReceivedBy();
		for(RecevTransreceivedBy t: L){
			System.out.println(t.getUserId());
		}
		/*
		//int receiptId = service.saveReceipt(transaction.getAmount());
		Receipt receipt = service.saveReceipt(transaction.getAmount());
		service.saveTransaction(transaction, receipt);
*/		
		Receipt receipt = service.addTransactions(transaction);
		service.saveReceipt(receipt);
		return service.getReceiptDetails(receipt.getReceiptId());
	}
	
	@RequestMapping(value = "/updatetransaction",method = RequestMethod.PUT)
	public SendReceipt updateTransaction(@RequestBody SendReceipt sendReceipt,@RequestParam("receiptId") int receiptId ){
		Receipt receipt = new Receipt();
		receipt.setReceiptId(receiptId);
		receipt.setTotalAmount(sendReceipt.getTotalAmount());
		System.out.println("Receip starte");
		service.updateReceipt(receipt);
		System.out.println("Transa starte");
		service.updateTransaction(sendReceipt, receipt);
		/*Receipt receipt = service.updateTransaction1(sendReceipt, receiptId);
		service.saveReceipt(receipt);*/
		return service.getReceiptDetails(receipt.getReceiptId());
	}
	
	@RequestMapping(value = "/deletetransaction",method = RequestMethod.DELETE)
	public boolean deleteReceipt(@RequestParam("receiptId") int receiptId ){
		System.out.println("Delete");
		boolean check = service.deleteReceipt(Receipt.class,receiptId);
		
		return check;
		
	}
}
