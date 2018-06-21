<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
welcome <b>${uid}</b>
<form action="bank.htm">
	UserId : <input type="text" name="uid">
	BankIFSC : <input type="text" name="ifsc">
	<input type="submit" value = "Submit"><br>
	
<!-- 	<input type="button" name="UpdateDetails" value="Update Details Link"> -->
</form>
</body>
</html>