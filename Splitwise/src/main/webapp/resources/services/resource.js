angular.module("myApp",["ui.router","angular-storage"])
.factory('ReceiptDetails1',function($resource){
	var data = $resource('/Splitwise/receiptdetails', {
		update: {
			method: 'PUT'
		}
	});
	
	return data;	
});