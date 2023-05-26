<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${css}/registro.css">
<script type="text/javascript">
	function validaForm(ev) {
		ev.preventDefault();
		var nombre = document.getElementById("input_nombre").value;
		var usuario = document.getElementById("input_usuario").value;
		var password = document.getElementById("input_password").value;
		var password2 = document.getElementById("input_password_rep").value;
		var correo = document.getElementById("input_correo").value;
		var error = document.getElementById("p_registro_error");
		
		if (!nombre.trim() || !usuario.trim() || !password.trim() || !password2.trim() || !correo.trim()) {
			error.textContent = "Todos los campos son obligatorios";
		} else if (password != password2) {
			error.textContent = "Los passwords no coinciden";
		} else {
			error.textContent = "";
			this.submit();
		}
	}
	
	window.onload = function() {
		document.getElementById("frm_registro").addEventListener("submit",
				validaForm);
	};
</script>
<title>Registro</title>
</head>
<body>
	<div id="div_registro">
		
		<form id="frm_registro" action="${home}/registro" method="post">
			<input class="input_registro" type="text" name="nombre" id="input_nombre" placeholder="Nombre">
			<input class="input_registro" type="text" name="usuario" id="input_usuario" placeholder="Usuario">
			<input class="input_registro" type="email" name="correo" id="input_correo" placeholder="Correo">			
			<input class="input_registro" type="password" name="password" id="input_password" placeholder="Password"> 
			<input class="input_registro" type="password" name="password_rep" id="input_password_rep" placeholder="Repite el Password">
			<button id="btn_registro" type="submit">Sign in</button>
		</form>
		
		<p id="p_registro_error">
			<c:choose>
				<c:when test="${error eq 'existe'}">El usuario o correo ya esta registrado</c:when>
				<c:when test="${error eq 'password_rep' or error eq 'vacio'}">Asegúrate de tener tu navegador actualizado</c:when>
			</c:choose>
		</p>
		
		<hr>
		<a href="${home}/login"><button id="btn_login">Login</button></a>
	</div>
</body>
</html>