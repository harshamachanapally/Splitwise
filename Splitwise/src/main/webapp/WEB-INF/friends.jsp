<br>
<input type="email" placeholder="Enter email" ng-model="email" required />
<button ng-click="addfriend()">Add Friend</button>
<br></br>
{{errorpost.errorMessage}}
{{message}}
<br></br>
<table style = "margin-left:35%;">
<thead>
<tr>
	<th>Name</th>
	<th>Email</th>
</tr>
</thead>
<tbody ng-repeat="friend in friends">
<tr>
	<td>{{friend.name}}</td>
	<td>{{friend.email}}</td>
</tr>
</tbody>
</table>



