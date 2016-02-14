<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Parking Lot Entrance</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Parking lot entrance</h2>
<image src="/assets/img/booth.jpg" width=150></a>
</p>

<form:form id="myForm" method="post" action="entrance">
    <fieldset>
        <legend>Parking Lot Entrance</legend>
            <label for="licensePlateInput" >License Plate</label>
                <input type="text" name="licensePlate"
                id="licensePlateInput" placeholder="License Plate" />
            <button>Enter the Parking Lot</button>
    </fieldset>
</form:form>
</body>
</html>
