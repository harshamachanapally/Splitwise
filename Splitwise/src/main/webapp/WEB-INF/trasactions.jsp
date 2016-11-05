{{error}}
{{i}}
{{receiptid}}
<br></br>
<!-- Search By: <select ng-model="searchBy" >
<option value="AddedBy">AddedBy</option>
<option value="Paidby">PaidBy</option>
<option value="Receivedby">ReceivedBy</option>
 </select>-->
<input type="text" placeholder="Search By AddedBy" ng-model="search.addedby"></input>
<input type="text" placeholder="Search By PaidBy" ng-model="search.paidby"></input>
<input type="text" placeholder="Search By ReceivedBy" ng-model="search.receivedby"></input>

<!-- <button ng-click="attach()">Search</button> -->
<input type="checkbox" ng-model="perfect"/>ExactMatch
<br>
<br>
<div class="div1">
<table>
<thead>
<tr >
	<th ng-click="sortBy('tranId')">TranId <div ng-class = "getArrow('tranId')"></th>
	<th ng-click="sortBy('receiptId')">ReceipetId <div ng-class = "getArrow('receiptId')"></th>
	<th ng-click="sortBy('addedBy')">AddedBy <div ng-class = "getArrow('addedBy')"></th>
	<th ng-click="sortBy('description')">Description <div ng-class = "getArrow('description')"></th>
	<th ng-click="sortBy('paidBy')">Paid by <div ng-class = "getArrow('paidBy')"></th>
	<th ng-click="sortBy('receivedBy')">Received by <div ng-class = "getArrow('receivedBy')"></th>
	<th ng-click="sortBy('amount')">Amount <div ng-class = "getArrow('amount')"></th>
	<!-- <th ng-click = "sortBy('name')">Name <div ng-class = "getArrow('name')"></div></th> -->
</tr>
</thead>
<tbody ng-repeat="tran in trasactions | orderBy:sortColumn:sortreverse | filter: {addedBy:search.addedby, paidBy:search.paidby,
														receivedBy:search.receivedby}:perfect ">
<tr>
	<td>{{tran.tranId}}</td>
	<!-- <td><a ui-sref="receipt ({receiptid: tran.receiptId})">{{tran.receiptId}}</a></td> -->
	<td ng-click="setReceipt(tran.receiptId)"><a ui-sref="receipt">{{tran.receiptId}}</a></td>
	<td>{{tran.addedBy}}</td>
	<td>{{tran.description}}</td>
	<td>{{tran.paidBy}}</td>
	<td>{{tran.receivedBy}}</td>
	<td>{{tran.amount| currency:"$"}}</td>
</tr>
</tbody>
</table>
</div>