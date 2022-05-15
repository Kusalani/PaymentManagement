$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validatePaymentForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
		url : "PaymentAPI",  
		type : type,  
		data : $("#formPayment").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onPaymentSaveComplete(response.responseText, status);  
		} 
	}); 
});


function onPaymentSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divPaymentGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidPaymentIDSave").val("");  
	$("#formPayment")[0].reset(); 
}


//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidPaymentIDSave").val($(this).data("payid"));     
	$("#cusId").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#telNo").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#date").val($(this).closest("tr").find('td:eq(2)').text());  
	$("#amount").val($(this).closest("tr").find('td:eq(3)').text());
	$("#cardNo").val($(this).closest("tr").find('td:eq(4)').text());     
	$("#cvv").val($(this).closest("tr").find('td:eq(5)').text());  
	$("#cardType").val($(this).closest("tr").find('td:eq(6)').text()); 
	$("#expDate").val($(this).closest("tr").find('td:eq(7)').text()); 
});


//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "PaymentAPI",
		type : "DELETE",
		data : "payId=" + $(this).data("payid"),
		dataType : "text",
		complete : function(response, status)
		{
			onPaymentDeleteComplete(response.responseText, status);   
		}
	}); 
});


function onPaymentDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divPaymentGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

//CLIENT-MODEL================================================================
function validatePaymentForm()
{
	// 	CUS ID
	if ($("#cusId").val().trim() == "")
	{
		return "Insert Customer ID.";
	}
	
	// TEL NO
	if ($("#telNo").val().trim() == "")
	{
		return "Insert Telephone Number.";
	}
	
	// DATE
	if ($("#date").val().trim() == "")
	{
		return "Insert Bill Date.";
	}
	
	// PRICE-------------------------------
	if ($("#amount").val().trim() == "")
	{
		return "Insert Amount.";
	}
	
	// is numerical value
	var tmpAmount = $("#amount").val().trim();
	if (!$.isNumeric(tmpAmount))
	{
		return "Insert a numerical value for Amount.";
	}
	
	// convert to decimal price
	$("#amount").val(parseFloat(tmpAmount).toFixed(2));
	
	// CARD NO------------------------
	if ($("#cardNo").val().trim() == "")
	{
		return "Insert Card No.";
	}
	
	// CVV------------------------
	if ($("#cvv").val().trim() == "")
	{
		return "Insert CVV.";
	}
	

	// CARD TYPE------------------------
	if ($("#cardType").val().trim() == "")
	{
		return "Insert Card Type.";
	}
	// EXPIRE DATE
	if ($("#expDate").val().trim() == "")
	{
		return "Insert Expire Date.";
	}

	
	return true;
}

