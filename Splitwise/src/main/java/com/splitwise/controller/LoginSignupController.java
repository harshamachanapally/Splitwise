package com.splitwise.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.splitwise.pojo.Customers;
import com.splitwise.pojo.FriendsBalance;
import com.splitwise.pojo.FriendsBalanceList;
import com.splitwise.pojo.FriendsList;
import com.splitwise.pojo.Transaction;
import com.splitwise.service.ServiceLayer;
import com.splitwise.service.ServiceLayerInterface;
@Controller
@SessionAttributes({"customerID"})
@RequestMapping(value="/Splitwises")
public class LoginSignupController {
	
	@Autowired
	ServiceLayerInterface service;
	
	@RequestMapping(value="/Login.html",method = RequestMethod.GET)
	public ModelAndView Login() {	
		System.out.println("Login");
		ModelAndView model = new ModelAndView("Login1");	
		return model;
	}
	
/*	@RequestMapping(value="/welcome.html", method = RequestMethod.POST)
	public ModelAndView Welcome (@ModelAttribute("customer") Customers customer ){
				
		Customers customer1 = service.getCustomer(customer);
		String Error = "";
		//ModelAndView model = new ModelAndView("Welcome");
		if(customer.getEmail()==null||customer.getPassword()==null||customer1==null)
		{
			Error = "Incorrect EmailId or Password.Enter Again!!!";
			ModelAndView model = new ModelAndView("Login");
			model.addObject("Error", Error);
			return model;
		}
		String path = "redirect:"+"/welcome/"+customer.getUserId()+".html";
		return new ModelAndView(path);
	}*/
	
	@RequestMapping(value="/welcome/{id}.html", method = RequestMethod.GET)
	public ModelAndView Welcome (@PathVariable("id") int id){
				System.out.println("Welcome");
		ModelAndView model = new ModelAndView("Welcome");
		model.addObject("customerId", id);
		return model;
	}
	
	@RequestMapping(value="/friendsBalances.html", method = RequestMethod.GET)
	public ModelAndView FriendsBalances (){
		ModelAndView model = new ModelAndView("friendsBalances");
		return model;
	}
	
	@RequestMapping(value="/trasactions.html", method = RequestMethod.GET)
	public ModelAndView Trasactions(){
		ModelAndView model = new ModelAndView("trasactions");
		return model;
	}
	
	@RequestMapping(value="/friends.html", method = RequestMethod.GET)
	public ModelAndView friends(){
		ModelAndView model = new ModelAndView("friends");
		return model;
	}
	
	@RequestMapping(value="/receiptDetails.html", method = RequestMethod.GET)
	public ModelAndView receiptDetails(){
		ModelAndView model = new ModelAndView("receiptDetails");
		return model;
	}
	
	@RequestMapping(value="addTransaction.html", method = RequestMethod.GET)
	public ModelAndView addTransaction(){
		ModelAndView model = new ModelAndView("addTransaction");
		return model;
	}
	
	@RequestMapping(value="transactionConfirm.html", method = RequestMethod.GET)
	public ModelAndView transactionConfirm(){
		ModelAndView model = new ModelAndView("transactionConfirm");
		return model;
	}
	
	@RequestMapping(value="editReceipt.html", method = RequestMethod.GET)
	public ModelAndView editReceipt(){
		ModelAndView model = new ModelAndView("editReceipt");
		return model;
	}
	@RequestMapping(value="deleteReceipt.html", method = RequestMethod.GET)
	public ModelAndView deleteReceipt(){
		ModelAndView model = new ModelAndView("deleteReceiptConfirm");
		return model;
	}
	@RequestMapping(value="/Logout.html",method = RequestMethod.GET)
	public ModelAndView Logout(){
		/*this.unique = UUID.randomUUID().toString();
		System.out.println(this.unique);*/
		ModelAndView model = new ModelAndView("Login");	
		return model;
	}
	
	
	@RequestMapping(value="/SignupConfirmation.html", method = RequestMethod.POST)
	public ModelAndView SignupConfirm(@Valid @ModelAttribute("customer") Customers customer,BindingResult result){
		String Error="";
		if(result.hasErrors())
		{
			ModelAndView model = new ModelAndView("Signup");
			return model;
		}
		System.out.println("Email is "+customer.getEmail());
		if(service.EmailCheck(customer.getEmail())!=null)
		{
			Error = "EmailId already exists. Try Another";
			ModelAndView model = new ModelAndView("Signup");
			model.addObject("Error", Error);
			return model;
		}
		service.saveCustomer(customer);
		String success = "Sing In is successful "+customer.getName()+"!!!";
		ModelAndView model = new ModelAndView("SignupConfirmation");
		model.addObject("success", success);
		return model;
	}
	
	/*@RequestMapping(value="/welcome.html", method = RequestMethod.POST)
	public ModelAndView Welcome (@Valid @ModelAttribute("customer") Customers customer,BindingResult result ){
		String Error="";
		Customers customer1 = service.getCustomer(customer);
		//redirectAttrs.addFlashAttribute("customer1", customer1);
		if(customer.getEmail()==null||customer.getPassword()==null||customer1==null)
		{
			Error = "Incorrect EmailId or Password.Enter Again!!!";
			ModelAndView model = new ModelAndView("Login");
			model.addObject("Error", Error);
			return model;
		}
*/
	
	@RequestMapping(value="/Welcome.html", method = RequestMethod.POST)
	public ModelAndView RefreshWelcome(@ModelAttribute("customer1") Customers customer1){
		
		
		ModelAndView model = new ModelAndView("Welcome");
		//required
		FriendsBalanceList<Customers> list1 = service.getList((List<Customers>) customer1.getFriend());
		FriendsBalanceList<FriendsBalance> list = service.getList(service.getFriendsBalance(customer1));
		List<Float> bal = service.getBalances(list.getFriendBalanceList());
		model.addObject("bal", bal);
		model.addObject("customer1",customer1);
		model.addObject("name", customer1.getName());
		model.addObject("balanceList", list);
		model.addObject("FriendsList", list1);
		return model;
	}
	@RequestMapping(value="/EditInfo.html", method = RequestMethod.POST)
	public ModelAndView EditProfile(@Valid @ModelAttribute("customer1") Customers customer1){
		ModelAndView model = new ModelAndView("EditProfile");
		model.addObject("command", customer1);
		return model;
	}
	@RequestMapping(value="/submission.html", method = RequestMethod.POST)
	public ModelAndView UpdateProfile(@ModelAttribute Customers customer1,BindingResult result){
		if (result.hasErrors()){
			ModelAndView model = new ModelAndView("EditProfile");
			return model;
		}
		ModelAndView model = new ModelAndView("showeditProfile");
		System.out.println(customer1.getName());
		System.out.println(customer1.getEmail());
		System.out.println(customer1.getPassword());
		System.out.println(customer1.getUserId());
		service.updateCustomer(customer1);
		model.addObject("show", customer1);
		return model;
	}
	
	
	@RequestMapping(value="/addtransaction.html", method = RequestMethod.POST)
	public ModelAndView Transaction(@Valid @ModelAttribute("customer1") Customers customer1){
		FriendsBalanceList<Customers> friendslist = service.getList((List<Customers>) customer1.getFriend());
		ModelAndView model = new ModelAndView("AddTransaction");
		model.addObject("FirendsList", friendslist);
		return model;
	}
	@RequestMapping(value="/submittransaction.html", method = RequestMethod.POST)
	public ModelAndView Transaction(@ModelAttribute("customer1") Customers customer1,@RequestParam("description")String description,@RequestParam("paidId")String paidId1,
										@RequestParam("Amount")String Amount1,@RequestParam("receivedId")String receiveId1){
	
		int Amount = Integer.parseInt(Amount1);
		int paidId = Integer.parseInt(paidId1);
		int receiveId = Integer.parseInt(receiveId1);
		//service.saveTransaction(description,(float) Amount,customer1.getUserId(), paidId, receiveId);
		ModelAndView model = new ModelAndView("submittransaction");
		return model;
	}
	
	@RequestMapping(value="/addfriend.html", method = RequestMethod.POST)
	public ModelAndView AddFriend(@Valid @ModelAttribute("customer1") Customers customer1,
													@RequestParam("emailId") String email){
		String message="";
		ModelAndView model = new ModelAndView("Welcome");
		List<Customers> c = (List<Customers>) customer1.getFriend();
		Customers Customer1 = service.getCustomer(customer1);
		model.addObject("customer1", customer1);
		
		for(Customers cust: c){
			if(cust.getEmail().equals(email)){
			message = "Both are already friends";
			model.addObject("Message", message);
			return model;
			}
		}
		if(service.EmailCheck(email)==null||email.trim().equals("")){
			message = "Email Id does not exist";
		}
		else if(customer1.getEmail().equalsIgnoreCase(email)) 
		{
			message = "You cannot had yourself as friend";
		}
		else{
			service.addFriend(customer1, email);
			Customers friend = service.EmailCheck(email);
			service.addFriend(friend, customer1.getEmail());
			message = "Added to Friends list";
			FriendsBalanceList<Customers> friendslist = service.getList((List<Customers>) customer1.getFriend());
			//Required
			model.addObject("FriendsList", friendslist);
		}
		
		model.addObject("Message", message);
		return model;
	}
	
	@RequestMapping(value="/gototransactions.html", method = RequestMethod.POST)
	public ModelAndView GotoTransaction(@Valid @ModelAttribute("customer1") Customers customer1){
		
		FriendsBalanceList<Customers> friendslist = service.getList((List<Customers>) customer1.getFriend());
		ModelAndView model = new ModelAndView("CheckTransactions");
		model.addObject("FriendsList", friendslist);
		return model;
	}
	/*@RequestMapping(value="/checktransactions.html", method = RequestMethod.POST)
	public ModelAndView CheckTransaction(@Valid @ModelAttribute("customer1") Customers customer1,@RequestParam("friendId") int friend){
		
		ModelAndView model = new ModelAndView("CheckTransactions");
		List<FriendsList> friendslist = service.getFriendsList(customer1.getUserId(), friend);
		List<Transaction> transaction = service.getTransactions((friendslist));
		FriendsBalanceList<Transaction> trans = new FriendsBalanceList<Transaction>();
		trans.setFriendBalanceList(transaction);
		List<Customers> friends = (List<Customers>) customer1.getFriend();
		FriendsBalanceList<Customers> list = new FriendsBalanceList<Customers>();
		list.setFriendBalanceList(friends);
		for(Customers friend1: friends){
			if(friend1.getUserId()==friend)
				model.addObject("friend1",friend1);
		}
		
		String t = "Transactions";
		String r = "ReceipetId";
		String a = "AddedBy";
		String A = "Amount($)";
		String d = "Description";
		String p = "PaidBy";
		model.addObject("Trans", trans);
		model.addObject("TransactionHeader", t);
		model.addObject("Receipt", r);
		model.addObject("AddedBy", a);
		model.addObject("Amount", A);
		model.addObject("Description", d);
		model.addObject("PaidBy", p);
		model.addObject("FriendsList", list);
		return model;
	}
	*/
	@RequestMapping(value="/Signup.html", method = RequestMethod.GET)
	public ModelAndView Signup(){
		
		ModelAndView model = new ModelAndView("Signup");
		return model;
	}
	
	
	/*public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}*/
}
