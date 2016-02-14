<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entrance Success</title>

</head>
<body>

<h1>Entrance Success</h1>


			<ul class="nav navbar-nav navbar-right">
				<li><a href="/">Home</a></li>
				<li><a href="entrance">Entrance</a></li>
			</ul>

            License Plate ${it.vehicle.licensePlate} of type ${it.vehicle.type} was granted access to the parking lot.
</p>
            <h2>Current state of the parking lot:</h2>
</p>
<table cellpadding="15" border="1" style="background-color: #ffffcc;">
<tr>
<th>LicensePlate</th><th>car type</th><th>parking space type</th><th>usage start</th><th>usage duration</th>
</tr>
<c:forEach items="${it.usages}" var="usage">
    <tr>
    <td>${usage.key.licensePlate}</td>
    <td>${usage.key.type}</td>
    <td>${usage.value.parkingSpace.type}</td>
    <td>${usage.value.startUsageDateTime}</td>
    <td>${usage.value.parkingDuration}</td>
    </tr>
</c:forEach>
<table>

</body>
</html>
