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
<image src="assets/img/bol_com_logo.png"></a>
<h1>Parking lot entrance</h1>
<image src="/assets/img/booth.jpg"></a>
</p>


				<li><a href="/">Home</a></li>
				<li class="active"><a href="entrance">Entrance</a></li>

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
