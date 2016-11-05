<h1>Friends Balances</h1>

<div >
<table style = "margin-left:40%;">
<thead>
<tr>
	<th>Name</th>
	<th>Amount</th>
</tr>
</thead>
<tbody ng-repeat="bal in friendsbalances">
<tr>
	<td>{{bal.name}}</td>
	<td> <div ng-show="bal.flag" style="color:green; font-size:9px;">{{bal.message}}</div>
		<div ng-hide="bal.flag" style="color:red; font-size:9px;">{{bal.message}}</div>{{bal.amount}} </td>
</tr>
</tbody>
</table>
</div>