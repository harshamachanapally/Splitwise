package com.splitwise.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.splitwise.exception.DataNotFoundException;
import com.splitwise.pojo.Customers;
import com.splitwise.pojo.FriendsBalance;
import com.splitwise.pojo.FriendsBalanceList;
import com.splitwise.pojo.FriendsList;
import com.splitwise.pojo.Receipt;
import com.splitwise.pojo.RecevTrans;
import com.splitwise.pojo.SendReceipt;
import com.splitwise.pojo.SendTrans;
import com.splitwise.pojo.Transaction;

public interface ServiceLayerInterface {

	//To check if Email is already used for Signup page
	Customers getCustomerById(int Id);

	Customers EmailCheck(String email) throws DataNotFoundException;

	//To get Customer object by passing Customer
	Customers getCustomer(Customers customer) throws DataNotFoundException;

	//To save a customer after signup
	void saveCustomer(Customers customer);

	//Get the Balances of a Customer on Welcome Page
	List<Float> getBalances(List<FriendsBalance> Balances);

	//Returns List of friends with net balances for a customer
	List<FriendsBalance> getFriendsBalance(Customers customer);

	List<FriendsList> getFriendsList(int j);

	List<SendTrans> getTransactions(List<FriendsList> list);

	<T> FriendsBalanceList<T> getList(List<T> list);

	void addFriend(Customers customer, String addfriend) throws DataNotFoundException;

	void updateCustomer(Customers customer);

	SendReceipt getReceiptDetails(int receiptId);

	boolean saveReceipt(Receipt receipt);

	Receipt addTransactions(RecevTrans recevTrans);

	Receipt updateReceipt(Receipt receipt);

	Receipt updateTransaction1(SendReceipt sendReceipt, int id);

	Transaction updateTransaction(SendReceipt sendReceipt, Receipt receipt);

	boolean deleteReceipt(Class<?> type, int receiptId);

}