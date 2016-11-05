
<!--<a ui-sref="trasactions ({id: customerid})" ui-sref-active="activeState">Go back to Transactions</a> -->
<a ui-sref="deleteReceipt ({receiptid: trans.receiptId})">delete Receipt</a>
<a ui-sref="editReceipt">Edit Receipt</a>


<h2>Receipt Id: {{trans.receiptId}}</h2>
<h2>Total Amount: {{trans.totalAmount}}</h2>  
<h2>Description: {{trans.description}}</h2>
<h2>Paid By: {{trans.paidBy}}</h2>
<h2>Added By: {{trans.addedBy}}</h2> 

<table>
<thead>
	<th>TranId</th>
	<th>ReceivedBy</th>
	<th>Amount</th>
</thead>
<tbody>
	<tr ng-repeat="tran in trans.trans">
		<td>{{tran.tranId}}</td>
		<td>{{tran.receivedBy}}</td>
		<td>{{tran.amount}}</td>
	</tr>
</tbody>
</table>