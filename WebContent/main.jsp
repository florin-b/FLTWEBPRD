<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Management Flota</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
</head>

<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>

<body>



	<header>
	<div class="width">
		<h2>Management flota</h2>
		<div class="tagline"></div>
	</div>
	</header>
	<section id="body" class="width clear"> <jsp:include page="/auth/navbar.jsp" /> <section id="content" class="column-right"> Sunteti logat
	ca <c:out value="${user.userName}" /> </section> </section>

	<footer class="clear">
	<div class="width">
		<p class="left">
			<myTags:footer copyright="${initParam.copyright}" />
		</p>
		<p class="right">Management Flota</p>
	</div>
	</footer>


</body>
</html>