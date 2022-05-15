<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Credit Card Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery.min.js"></script> 
<script src="Components/payment.js"></script> 

</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col"> 
			<h1>PAYMENT DETAILS</h1>
				<form class="row g-3" id="formPayment" name="formPayment" method="post" action="Payment.jsp">  
					<div class="col-md-6">
						<label class="form-label">Customer ID:</label>  
	 	 				<input id="cusId" name="cusId" type="text"  class="form-control form-control-sm" placeholder="Enter Customer ID" required>
					</div>
					
					<div class="col-md-6">
						<label class="form-label">Telephone No:</label>
						<input id="telNo" name="telNo" type="tel" class="form-control form-control-sm" placeholder="123-45-678"  pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" required>
					</div>    
  					
  					<div class="col-md-6">
	  					<label class="form-label">Bill Date:</label>
	  					<input id="date" name="date" type="text" class="form-control form-control " placeholder="Enter Date" required>
  					</div>
					 
					<div class="col-md-6">
						<label class="form-label">Amount:</label>
					  	<input id="amount" name="amount" type="text" class="form-control form-control-sm" placeholder="Enter Amount" required>
					</div>    
					
					<div class="col-md-6">
						<label class="form-label">Card No:</label>
						<input id="cardNo" name="cardNo" type="text" class="form-control form-control-sm" placeholder="xxxx-xxxx-xxxx-xxxx" pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}" required>
					</div>   
  					
					<div class="col-md-6">
						<label class="form-label">Cvv:</label>
						<input id="cvv" name="cvv" type="text" class="form-control form-control-sm" placeholder="xxx" pattern="[0-9]{3}"required>
					</div>   
  					
  					
  					<div class="col-md-6">
						<label class="form-label">Card Type:</label>
						<input id="cardType" name="cardType" type="text" class="form-control form-control-sm" placeholder="Visa/Master" pattern="[0-9]{3}"required>
					</div>   
  					
  					
  					<div class="col-md-6">
	  					<label class="form-label">Expire Date:</label>
	  					<input id="expDate" name="expDate" type="text" class="form-control form-control-sm" placeholder="Enter Date" required>
  					</div>
  					
  					
  					<div class="col-12">
	  					<input id="btnSave" name="btnSave" type="button" value="Submit Payment" class="btn btn-primary" required>  
						<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
  					</div>
					 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
				<div id="alertError" class="alert alert-danger"></div>
				
				
				<div id="divPaymentGrid">
					<%
						Payment payObj = new Payment();
						out.print(payObj.readPayment());
					%>
				</div>
			</div>
		</div>
</div>

</body>
</html>