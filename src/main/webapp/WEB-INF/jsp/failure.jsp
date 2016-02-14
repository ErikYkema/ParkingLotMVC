<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entrance Denied</title>

</head>
<body>
<jsp:include page="header.jsp" />

<h3 >Parking Entrance request failure</h3>
Attempted to park license Plate ${it.vehicle.licensePlate} of type ${it.vehicle.type}.
</p>
<strong>But ${it.message}</strong>
</p>
<image src="/assets/img/cantparkhere.jpg"></a>

</body>
</html>