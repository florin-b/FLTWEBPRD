<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/ui/1.11.2/jquery-ui.min.js"></script>

<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">




<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="tagFiliale" uri="/WEB-INF/tlds/listFiliale.tld"%>


<link rel="stylesheet" type="text/css" href="../css/OneMarkerStyle.css">
<link rel="stylesheet" type="text/css" href="../css/MarkerLabelStyle.css">




<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBhGZckc6WAio9WiiLstQTTpVtAvQ7kIEc'></script>
<script type="text/javascript" src='../scripts/markerwithlabel.js'></script>


<link rel="stylesheet" href="../css/styles.css" type="text/css" />

<link rel="stylesheet" href="../css/activ_soferi.css" type="text/css" />
<link rel="stylesheet" href="../css/loadingscreen/loadingScreen.css" type="text/css" />

<link rel="stylesheet" href="../css/common/commonStyles.css" type="text/css" />



<script type="text/javascript" src="../scripts/loadingScreen.js"></script>
<script type="text/javascript" src="../scripts/analizaSmsClienti.js"></script>
<script type="text/javascript" src="../scripts/datePicker.js"></script>
<script type="text/javascript" src="../scripts/mapsBorderou.js"></script>

<style>
div.map_div {
	width: 1000px;
	height: 700px;
	visibility: hidden;
}
</style>

</head>
<body>



	<header>
	<div class="width">
		<h2>Management flota</h2>
		<div class="tagline"></div>
	</div>
	</header>

	<section id="body" class="width clear"> <jsp:include page="navbar.jsp" /> <section id="content" class="column-right">




	<table style="width: 100%;">

		<tr>

			<td style="width: 80%;">
				<div id="contentdiv">


					<table border='0'>

						<tr align='center'>
							<td colspan='4'><b>SMS Clienti</b></td>
						</tr>
						<br>

						<tr id='optionsRow'>


							<td valign="top" class="option_block">Data<br> <br>
								<div id="intervalDiv">
									<p>
										<input type="text" id="datepickerStart">
										<br>Filiala<br>
										<tagFiliale:filiale name="filiale" id="filiale" fili="${sessionScope.user.filiala}"
									tipAcces="${sessionScope.user.tipAcces}"/>
										
									</p>

									<p>
										<button type="submit" id="showDocumente" class="styled-button-1">Afiseaza</button>
									</p>
								</div>
								
							
									
								
							</td>
							<td>
								<div id="sumarActivitate"></div>
							</td>


						</tr>

						<tr>
							<td valign="top" colspan='4'>
								<div id="rezultatActivitate"></div>
							</td>
						</tr>





						<tr>
							<td colspan='4'>
								<div id="hartaTraseu" class='map_div'></div>
							</td>
						</tr>

					</table>


				</div>
			</td>

		</tr>

	</table>
	<footer class="clear">
	<div class="width">


		<p class="left">
			<myTags:footer copyright="${initParam.copyright}" />
		</p>
		<p class="right">Management Flota</p>
	</div>
	</footer>

	<div id="loadingScreen"></div>
</body>
</html>