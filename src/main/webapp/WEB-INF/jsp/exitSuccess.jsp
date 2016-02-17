<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exit Success</title>

</head>
<body>
<jsp:include page="header.jsp" />


<h2>Exit Success</h2>

License Plate ${it.ticket.vehicle.licensePlate} of type ${it.ticket.vehicle.type} has left the parking lot.
</p>
Please <strong>pay!!!</strong> before leaving:
<li>Parking fee: ${it.ticket.parkingCost} PotCoins</li>
<li>Charging fee: ${it.ticket.chargingCost} PotCoins</li>
<li>duration (seconds): ${it.ticket.duration}</li>


<image src="/assets/img/bol_com_handaanwijzing.jpg"></a>
</body>
</html>
