<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>Logon</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


<link rel="stylesheet" type="text/css" href="css/logon/style.css" />
<script src="../scripts/logon/cufon-yui.js" type="text/javascript"></script>
<script src="../scripts/logon/ChunkFive_400.font.js" type="text/javascript"></script>

<script type="text/javascript" src="./scripts/logon/jquery.query-2.1.7.js"></script>

</head>
<body>

	<div class="wrapper">

		<div class="content">

			<br></br> <br></br>
			<div class="title_wrapper">Management Flota</div>
			<br></br>

			<div id="form_wrapper" class="form_wrapper">

				<form method="post" class="login active" action="<%=response.encodeURL("Controller?action=dologin")%>">
					<h3>Login</h3>
					<div>
						<label class="label_style">Utilizator:</label> <input type="text" name="name" class="field_text_style" value="androag" />

					</div>
					<div>
						<label class="label_style">Parola: </label> <input type="password" name="password" class="field_text_style" value="112" />

					</div>
					<div class="bottom">
						<input type="submit" value="Login"></input> <a class="linkform"></a>
					</div>

				</form>

			</div>

			<br></br>
			<div class="erorrs_wrapper">
				<p class="login-error">
					<c:out value="${account.errMessage}" />
				</p>
			</div>

			<div class="clear"></div>
		</div>

	</div>



</body>
</html>