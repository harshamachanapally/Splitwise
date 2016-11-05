
id: {{id1}}
<h1>Add Transaction</h1>
<table style = "margin-left:28%;border: 1px solid #E6E6E6;">
<tr>
<td><b>Description: </b></td>
<td><input type="text" placeholder="Enter Description" ng-model="senttran.description" required></td>
</tr>
<tr>
<td><b>Amount: </b></td>
<td><input type="number" ng-model="senttran.amount"></td>
</tr>
<tr>
<td><b>PaidBy:</b></td>
<td>
<select ng-model="senttran.paidBy"	ng-options="friend.userId as friend.name for friend in friends">
</select></td>
</tr>
<tr>
<td><b>ReceivedBy:</b></td>
<td> 
<select ng-model="senttran.receivedBy"
	ng-options="friend.name for friend in friends  | filter:filterpaidfriend()" multiple >
</select></td>
</tr>
</table>
<br></br>
<div ng-repeat="received in senttran.receivedBy">
<table>
<tr>
<td style = "width:100px;"><b>{{received.name}}:</b></td>
<td><input type="number" placeholder="Enter Amount" ng-model="received.amount"/> <button ng-click="removeReceivedBy(received)">remove</button></td>
</tr>
</table>
</div>
<br></br>
<button ng-click="addamount()">Check</button>
Total {{sum}}$ of {{senttran.amount}}$
<br></br>
<button ng-click="submit()">Submit Transaction</button>
<br></br>
{{result}}
<form>
</form>