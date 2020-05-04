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
<link rel="stylesheet" href="../css/styles.css" type="text/css" />

<link rel="stylesheet" href="../css/activ_soferi.css" type="text/css" />
<link rel="stylesheet" href="../css/loadingscreen/loadingScreen.css" type="text/css" />

<link rel="stylesheet" href="../css/common/commonStyles.css" type="text/css" />

<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBacSk9khZt7CGoqPe9UZFJGQAjWymAmBg'></script>
<script type="text/javascript" src='../scripts/markerwithlabel.js'></script>



<script type="text/javascript" src="../scripts/loadingScreen.js"></script>
<script type="text/javascript" src="../scripts/mapsFunctions.js"></script>
<script type="text/javascript" src="../scripts/pozitieMasini.js"></script>

<style> 
div.map_div {
    width: 1000px;
    height: 700px;
    resize: both;
    overflow: auto;
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


	<table>

		<tr>

			<td>
				<div id="contentdiv">


					<table border='0' style="width: 100%">

						<tr align='center'>
							<td colspan='3'><b>Pozitie masini</b></td>
						</tr>
						<br>

						<tr id='optionsRow'>
							<td valign="top" class="option_block">Filiala<br> <br> <tagFiliale:filiale name="filiale" id="filiale" fili="${sessionScope.user.filiala}"
									tipAcces="${sessionScope.user.tipAcces}"/> <br></td>
							<td valign="top" class="option_block">Masini<br> <br>
								<div id="listMasina"></div>
								<div id="checkGroup" style="display: none">
									<input type="checkbox" name="checkToate" id="checkToate"> <label for="checkToate">Toate</label>
								</div>

							</td>
							<td valign="bottom">
								<button type="submit" id="showPozitie" class="styled-button-1">Afiseaza</button>
							</td>

						</tr>
						<tr>
							<td colspan='3'>
								<table width='100%'>
									<tr>

										<td valign="center" align="left" width='30%'><div id='selectedOptions' style='visibility: invisible' class="info"></div></td>

										<td width='50%' id='refreshGroup' style='visibility: hidden'><input type="checkbox" name="refresh" id="refresh" class="info"><label
											for="refresh" class='checkRefresh'>Actualizeaza la fiecare 3 minute</label></td>

										<td valign="top" align="right" width='20%'><input id="showLess" type="image" src="../images/collapse_button.png"></td>
									</tr>
								</table>
						<tr>
							<td valign="top" colspan='3'>
								<div class='map_div' id="pozitieMasini" ></div>
							</td>

						</tr>



					</table>
				</div>
			</td>

		</tr>

	</table>

	<div id="legend">
		<b>Legenda</b>
	</div>
	<table cellpadding="1">
		<tr>
			<td><img src="../images/red_truck.png"></td>
			<td valign="center">Borderou activ</td>
		</tr>
		<tr>
			<td><img src="../images/green_truck.png"></td>
			<td valign="center">Borderou terminat</td>
		</tr>
		<tr>
			<td><img src="../images/blue_truck.png"></td>
			<td valign="center">Fara borderou</td>
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