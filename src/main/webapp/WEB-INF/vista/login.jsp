<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${css}/login.css">
<script type="text/javascript">
	function validaForm(ev) {
		ev.preventDefault();
		var usuario = document.getElementById("input_usuario").value;
		var password = document.getElementById("input_password").value;
		var error = document.getElementById("p_login_error");
		if (!usuario.trim() || !password.trim()) {
			error.textContent = "Todos los campos son obligatorios";
		} else {
			error.textContent = "";
			this.submit();
		}
	}
	window.onload = function() {
		document.getElementById("frm_login").addEventListener("submit", validaForm);
	};
</script>
<title>Login</title>
</head>

<body>
	<div id="div_login">
		<form id="frm_login" action="${home}/login" method="post">
			<input class="input_login" type="text" name="usuario" id="input_usuario" placeholder="Usuario">
			<input class="input_login" type="password" name="password" id="input_password" placeholder="Password">
			<button id="btn_login" type="submit">Login</button>
		</form>
		
		<p id="p_login_error">
			<c:choose>
				<c:when test="${error eq 'login_null'}">Credenciales incorrectas</c:when>
				<c:when test="${error eq 'vacio'}">Asegúrate de tener tu navegador actualizado</c:when>
			</c:choose>
		</p>
		
		<hr>
		<a href="${home}/registro"><button id="btn_registro">Sign in</button></a>
	</div>
</body>
</html>