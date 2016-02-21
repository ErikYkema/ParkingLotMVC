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

<title>Parking Lot Administration</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Parking Lot Administration</h2>
<image src="/assets/img/maintenance.jpg"></a>
</p>


<h2>Plain REST API</h2>
<li><a href="/webapi/restResource/properties">GET parking lot properties</a></li>
<li><a href="/webapi/restResource/vehicles">GET known vehicles</a></li>


<h2>Size of parking lot ${it.parkingLot.properties.name}</h2>
<image src="/assets/img/dimensions.jpg" width=200></a>
</p>
<table cellpadding="15" border="1" style="background-color: #ffffcc;">
<tr>
<th>Type</th><th>Capacity</th><th>Used space</th><th>Free space</th>
</tr>
<c:forEach items="${it.parkingLot.freeSpaceCounter.freeSpace}" var="entry">
    <tr>
    <td>${entry.key.simpleName}</td>
    <td>${it.parkingLot.freeSpaceCounter.maxFreeSpace[entry.key]}</td>
    <td>${it.parkingLot.freeSpaceCounter.maxFreeSpace[entry.key]-entry.value}</td>
    <td>${entry.value}</td>
    </tr>
</c:forEach>
<table>
</p>

<h2>Parked cars:</h2>
<image src="/assets/img/listOfCars.jpg" width=150></a>
</p>
<table cellpadding="15" border="1" style="background-color: #ffffcc;">
<tr>
<th>LicensePlate</th><th>car type</th><th>parking space type</th><th>usage start</th><th>usage duration</th>
</tr>
<c:forEach items="${it.parkingLot.parkingSpaceUsages}" var="usage">
    <tr>
    <td>${usage.key.licensePlate}</td>
    <td>${usage.key.type}</td>
    <td>${usage.value.parkingSpace.type}</td>
    <td>${usage.value.startUsageDateTime}</td>
    <td>${usage.value.parkingDuration}</td>
    </tr>
</c:forEach>
<table>

</p>
<image src="/assets/img/maintenance-2.jpg" width=250></a>
</p>
</body>
</html>
