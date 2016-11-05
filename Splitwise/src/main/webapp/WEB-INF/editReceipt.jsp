{{errorpost}}
{{check}}
<h1>Edit Receipt</h1>
<table style = "margin-left:28%;border: 1px solid #E6E6E6;">
<tr>
<td><b>Description: </b></td>
<td><input type="text"  ng-model="trans.description"></td>
</tr>
<tr>
<td><b>Amount: </b></td>
<td><input type="number" ng-model="trans.totalAmount"></td>
</tr>
<tr>
<td><b>PaidBy:</b></td>
<td>
{{trans.paidBy}}
</tr>
<tr>
<tr>
<td><b>AddedBy:</b></td>
<td>
{{trans.addedBy}}
</td>
</tr>

</table>
<br></br>
<table style="margin-left:28%; text-align: center;">
<thead>
<tr><th colspan="2" >ReceivedBy</th></tr>
</thead>
<tbody ng-repeat="received in trans.trans">
<tr>
<td><b>{{received.receivedBy}}:</b></td> 
<td><input type="number" placeholder="Enter Amount" ng-model="received.amount"/>
<div ng-model="addamount()"></div>
</td>
</tr>
</tbody>
</table>
<br></br>

Total {{sum}}$ of {{trans.totalAmount}}$
<br></br>
<button ng-click="submit()">Submit Transaction</button>
<br></br>
{{result}}
<form>
</form>