var app = angular.module("myApp",["ui.router","angular-storage","ngResource"])

app.service("UserService",function(store){
	var currentUser = {name: 'bill', password: 'abc123'};
	//var currentUser = null;
	this.setCurrentUser = function(user){
		currentUser = user;
		store.set('user',user);
		return currentUser;
	}
	
	this.getCurrentUser = function(){
		/*if(!currentUser){
			return store.get('user');
		}*/
		return currentUser;
	}
})
.service("RestInterceptor",function($rootScope,UserService,$log){
	this.request = function(config){
		$log("RestInterceptor");
		 //config.headers = config.headers || {};
         var authdata = btoa("bill:abc123");
         config.headers.Authorization = 'Basic '+authdata;
        return config;
	}
	//var authdata = Base64.encode(username + ':' + password);
	 service.responseError = function(response) {
	        if (response.status === 401) {
	            $rootScope.$broadcast('unauthorized');
	        }
	        return response;
	    };
})
.service("customerservice",function(){
	var customerid;
	this.setCustomerId = function(id){
		customerid = id;
	}
	this.getCustomerId = function(){
		return customerid;
	}
})
.service("recentReceiptIdservice",function(){
	var receiptId;
	this.setReceiptId = function(id){
		receiptId = id;
	}
	this.getReceiptId = function(){
		return receiptId;
	}
})
.factory("balances",function($http){
	var bal = {};
	bal.balance = function(id1,cb){
		 $http({
	 			method: "GET",
	 			url: "/Splitwise/friendsBalances",
	 			params: {id: id1}
	 			})
	 			 .then(function(response){
					cb(response.data);});
	}
	
	return bal;
})
.factory("friends",function($http){
	var friends = {};
	
	friends.getfriends = function(id1,cb){
		$http({
			method: "GET",
			url: "/Splitwise/friends",
			params: {id: id1}
			})
			.then(function(response){
				cb(response.data);});
	}
	
	return friends;
		
})
.factory('ReceiptDetails',function($resource){
	var data = $resource('/Splitwise/receiptdetails', {
		update: {
			method: 'PUT'
		}
	});
	return data;	
})
.factory('Transaction',function($resource){
	var tran = $resource('/Splitwise/transactions', {
		update: {
			method: 'PUT'
		}
	});
	return tran;	
})
.config(function($stateProvider,$urlMatcherFactoryProvider,$httpProvider,$urlRouterProvider,$locationProvider){
	$urlRouterProvider.otherwise("/balances");
	$urlMatcherFactoryProvider.caseInsensitive(true);
	//$httpProvider.interceptors.push('RestInterceptor');
	$stateProvider
/*	.state('login',{
		url : '/login',
 		templateUrl : '/Splitwises/Login.html',
 		controller : 'loginController',
 		controllerAs : 'loginCtrl'
		})*/
	.state('balances',{
		url : '/balances',
 		templateUrl : '/Splitwises/friendsBalances.html',
 		controller : 'friendsBalancesController',
 		controllerAs : 'friendsBalancesCtrl',
 		data: {
 			customeData1: "Home custome data"
 		},
 		/*resolve: {
 			balances: function($http,customerservice){
 				return $http({
 	  	 			method: "GET",
 	  	 			url: "/Splitwise/friendsBalances",
 	  	 			params: {id: customerservice.getCustomerId()}
 	  	 			})
 	  	 			 .then(function(response){
 	 					return response.data;});
 				,function(reason){
	 					return reason.data;}
  	 	
  	 		}
 		} */
 		
		})
	.state('friends',{
		url : '/friends',
 		templateUrl : '/Splitwises/friends.html',
 		controller : 'friendsController',
 		controllerAs : 'friendsCtrl'
		})
	.state('trasactions',{
		url : '/trasactions',
 		templateUrl : '/Splitwises/trasactions.html',
 		controller : 'trasactionsController',
 		controllerAs : 'trasactionsCtrl'
		})
	.state('receipt',{
		url : '/receipts',
 		templateUrl : '/Splitwises/receiptDetails.html',
 		controller : 'receiptController',
 		controllerAs : 'receiptCtrl'
 			
		})
	.state('editReceipt',{
		url : '/editReceipt',
 		templateUrl : '/Splitwises/editReceipt.html',
 		controller : 'editReceiptController',
 		controllerAs : 'editreceiptCtrl'
 			
		})
	.state('deleteReceipt',{
		url : '/deleteReceipt',
 		templateUrl : '/Splitwises/deleteReceipt.html',
 		controller : 'deleteReceiptController',
 		controllerAs : 'deleteReceiptCtrl'
 			
		})
	.state('addtransaction',{
		url : '/addtransaction',
 		templateUrl : '/Splitwises/addTransaction.html',
 		controller : 'addtransactionController',
 		controllerAs : 'addtransactionCtrl'
 		
	})
	.state('confirmtransaction',{
		url : '/transactionConfirm',
 		templateUrl : '/Splitwises/transactionConfirm.html',
 		controller : 'transactionConfirmController',
 		controllerAs : 'transactionConfirmCtrl'
		})
		
		//$httpProvider.interceptors.push('RestInterceptor');
	})
	.controller("loginController",function($scope){
			
		$scope.submit = function(){
			if($scope.email && $scope.password){
				$http({
					method: "POST",
					url: "/Splitwise/",
					params: {id: customerservice.getCustomerId()},
					data :{'email' : $scope.email}
					})
					.then(function(response){
						$scope.friendpost = response.data;
						friends.getfriends(customerservice.getCustomerId(),function(r){
							$scope.friends = r;
						});},function(reason){
							$scope.errorpost = reason.data;});
			}
		}

		})
	.controller("friendsBalancesController",function(balances,$scope,customerservice){
		
		balances.balance(customerservice.getCustomerId(),function(r){
			$scope.friendsbalances = r;
		});
		})
	.controller("friendsController",function($http,$scope,customerservice,friends){
/*		 var successfunction = function(response){
				$scope.friends = response.data;}
				var errorfunction = function(reason){
				$scope.error = reason.data;}*/		
		friends.getfriends(customerservice.getCustomerId(),function(r){
			$scope.friends = r;
		});
		$scope.message = "";
		$scope.addfriend = function(){
			if($scope.email){
				$http({
					method: "POST",
					url: "/Splitwise/addfriend",
					params: {id: customerservice.getCustomerId()},
					data :{'email' : $scope.email}
					})
					.then(function(response){
						$scope.friendpost = response.data;
						friends.getfriends(customerservice.getCustomerId(),function(r){
							$scope.friends = r;
						});},function(reason){
							$scope.errorpost = reason.data;});
					
				$scope.message = "";
			}
			else{
				$scope.message = "Incorrect Email Id";
				$scope.errorpost = "";
			}
		}
	})
	.controller("trasactionsController",function($http,$scope,customerservice,recentReceiptIdservice,Transaction){
		 var successfunction = function(response){
				$scope.trasactions = response;}
				var errorfunction = function(reason){
				$scope.error = reason;}
				
				$scope.sortColumn = "tranId";
				$scope.sortreverse = false;
				
				$scope.sortBy = function(column){
						$scope.sortreverse = ($scope.sortColumn == column) ? !$scope.sortreverse : false;
						$scope.sortColumn = column;
				};
				$scope.getArrow = function(column){
					if($scope.sortColumn==column){
						return $scope.sortreverse ? 'arrow-down' : 'arrow-up';
					};
					return ''
				}
				
			/*$http({
			method: "GET",
			url: "/Splitwise/transactions",
			params: {id: customerservice.getCustomerId()}
			})
			 .then(successfunction,errorfunction);
			*/
			var data1 = Transaction.get({id: customerservice.getCustomerId()});
			data1.$promise.then(successfunction,errorfunction);
			
			//This is to redirect to a requested receipt id
			$scope.setReceipt = function(id){
				recentReceiptIdservice.setReceiptId(id);
			}
			
	})
	.controller("addtransactionController",function($scope,$location,$http,$rootScope,customerservice,friends,$state,Transaction){
							var errorfunction1 = function(reason){
							$scope.error = reason.data;}
							$scope.customerid1 = customerservice.getCustomerId();
							friends.getfriends(customerservice.getCustomerId(),function(r){
								$scope.friends = r;
								$scope.friends.push( {
								    "userId":customerservice.getCustomerId(),
								    "name": "You",
								    "password": null,
								    "email": "",
								    "friend": []
								  })
							});	

							$scope.sum = 0;
							$scope.senttran = {amount:0};
							$scope.addamount = function(){
								$scope.sum = 0;
								for(var i = 0 ; i < $scope.senttran.receivedBy.length;i++){
									if($scope.senttran.receivedBy[i].amount){
										$scope.sum = $scope.sum + $scope.senttran.receivedBy[i].amount;
										}
								}
							}
							
							$scope.removeReceivedBy = function(received){
								var index = $scope.senttran.receivedBy.indexOf(received);
								$scope.test = received;
								$scope.senttran.receivedBy.splice(index,1);
							}
							$scope.result = "";
							
							$scope.submit = function(){
								if($scope.sum == $scope.senttran.amount && $scope.senttran.description){
								/*			$http({
										method: "POST",
										url: "/Splitwise/transactions",
										data :{'addedBy' : customerservice.getCustomerId(),
											   'paidBy' : $scope.senttran.paidBy,
											   'amount' : $scope.senttran.amount,
											   'description': $scope.senttran.description,
											   'receivedBy': $scope.senttran.receivedBy
											   }
										})
										.then(function(response){
											$rootScope.tranresponse = response.data;},function(reason){
												$rootscope.errorpost = reason.data;});*/
									
											/*var data2 = Transaction.save({addedBy : customerservice.getCustomerId(),
												   paidBy : $scope.senttran.paidBy,
												   amount : $scope.senttran.amount,
												   description: $scope.senttran.description,
												   receivedBy: $scope.senttran.receivedBy
												   });
											
											data2.$promise.then(function(response){
												$rootScope.tranresponse = response;},function(reason){
													$rootscope.errorpost = reason;});*/
											
											
											$scope.senttran = {amount:0};
											$rootScope.confirmation = "Added Successfully";
											$scope.result = "";
											//$location.url("/transactionConfirm");
											$state.go("confirmtransaction");
								}
								else{
									$scope.result = "Balances doesn't match.Press Click / Incorrect details";
								}
							}
							
							$scope.filterpaidfriend = function () {
							    return function (item) {
							        if (item.userId != $scope.senttran.paidBy)
							        {
							        	
							        	return true;
							        }
							        return false;
							    };
							};
							
					 
				 })
	.controller("transactionConfirmController",function($scope,$rootScope){
						
				 })
	.controller("receiptController",function($scope,$http,recentReceiptIdservice,ReceiptDetails){
							var errorfunction1 = function(reason){
							$scope.error = reason;}			
							/*$http({
								method: "GET",
								url: "/Splitwise/receiptdetails",
								params: {id: recentReceiptIdservice.getReceiptId()}
								})
								 .then(function(response){
										$scope.trans = response.data;},errorfunction1);*/
							var data = ReceiptDetails.get({id: recentReceiptIdservice.getReceiptId()});
							
							data.$promise.then(function(response){
								$scope.trans = response;},errorfunction1);
				 
				 })
	.controller("editReceiptController",function($scope,$http,recentReceiptIdservice,$rootScope,$location){
							var errorfunction1 = function(reason){
							$scope.error = reason.data;}
							
							var data = ReceiptDetails.get({id: recentReceiptIdservice.getReceiptId()});
							data.$promise.then(function(response){
								$scope.trans = response;},errorfunction1);
							
							$scope.sum = 0;
							
							$scope.addamount = function(){
								$scope.sum = 0;
									for(var i = 0 ; i < $scope.trans.trans.length;i++){
										$scope.sum = $scope.sum + $scope.trans.trans[i].amount;	
								
								}
								
							}
							
							$scope.submit = function(){
								$scope.check1 = "Loop";
								if($scope.sum == $scope.trans.totalAmount && $scope.trans.description){
									$scope.check = "result";
										$http({
										method: "PUT",
										url: "/Splitwise/updatetransaction",
										params: {receiptId: $stateParams.receiptid},
										data :{'receiptId': $scope.trans.receiptId,
												'totalAmount': $scope.trans.totalAmount,
												'addedById' : $scope.trans.addedById,
											   'paidById' : $scope.trans.paidById,
											   'description': $scope.trans.description,
											   'trans': $scope.trans.trans
											   }
										})
										.then(function(response){
											$rootScope.tranresponse = response.data;},function(reason){
												$rootscope.errorpost = reason.data;});
											/*$rootScope.tranresponse1 = $scope.tranresponse;
											$rootScope.errorpost1 = $scope.errorpost;*/
										$rootScope.confirmation = "Updated Successfully";
											//$location.url("/transactionConfirm")
											$state.go("confirmtransaction");
									
								}
								else{
									$scope.result = "Balances doesn't match.Press Click / Incorrect details";
								}
							}
							
				 })
	.controller("deleteReceiptController",function($scope,$http,recentReceiptIdservice){
		var errorfunction1 = function(reason){
			$scope.error = reason.data;}
		
		$scope.confirm = false;
		$scope.message = "Completed";
		$http({
			method: "DELETE",
			url: "/Splitwise/deletetransaction",
			params: {receiptId: recentReceiptIdservice.getReceiptId()}
			})
			 .then(function(response){
					$scope.confirm = response.data;},errorfunction1);
			
		$scope.check = function(){
			if($scope.confirm){
				$scope.message = "Receipt Id "+recentReceiptIdservice.getReceiptId()+" is deleted sucessfully..."
			}
			else{
				$scope.message = $scope.error;
			}
		}
	})
	.controller("myController",function($scope,$http,customerservice){
					 var successfunction1 = function(response){
							$scope.balances = response.data;}
							var errorfunction1 = function(reason){
							$scope.error = reason.data;}
							$scope.customerid1 = customerservice.getCustomerId();
					 $scope.click = function(id1){
						 if(!customerservice.getCustomerId()){
							 customerservice.setCustomerId(id1);
							
							$http({
								method: "GET",
								url: "/Splitwise/netbalances",
								params: {id: customerservice.getCustomerId()}
								})
								 .then(function(response){
										$scope.balances = response.data;},errorfunction1);
						};
					 }
				 })
				 
				 //http://localhost:8080/Splitwise/netbalances?id=1
				 
				 /*config(function($stateProvider,$urlMatcherFactoryProvider,$urlRouterProvider,$locationProvider){
					 //$urlRouterProvider.otherwise("/home");
					 //$urlMatcherFactoryProvider.caseInsensitive(true);
					 $stateProvider
					 
					 ["ui.router"]
				 })*/