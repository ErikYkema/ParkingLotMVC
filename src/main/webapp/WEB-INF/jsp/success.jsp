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

            Current state of the parking lot:
            <c:forEach items="${it.usages}" var="usage">
            Key: ${usage.key} Value: ${usage.value}
            </c:forEach>


</body>
</html>
