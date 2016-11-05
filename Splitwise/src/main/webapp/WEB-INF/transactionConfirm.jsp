<h1>{{confirmation}}</h1>
<h2>Receipt Id: {{tranresponse.receiptId}}</h2>
<h2>Total Amount: {{tranresponse.totalAmount}}</h2>  
<h2>Description: {{tranresponse.description}}</h2>
<h2>Paid By: {{tranresponse.paidBy}}</h2>
<h2>Added By: {{tranresponse.addedBy}}</h2> 

<table>
<thead>
	<th>TranId</th>
	<th>ReceivedBy</th>
	<th>Amount</th>
</thead>
<tbody>
	<tr ng-repeat="tran in tranresponse.trans">
		<td>{{tran.tranId}}</td>
		<td>{{tran.receivedBy}}</td>
		<td>{{tran.amount}}</td>
	</tr>
</tbody>
</table>

