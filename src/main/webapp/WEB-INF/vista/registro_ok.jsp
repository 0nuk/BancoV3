<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Bienvenido</title>
	<link rel="stylesheet" type="text/css" href="${css}/registro_ok.css">
</head>

<body>
	<div id="div_registro_ok">
		<h1>Felicitaciones</h1>
		<p class="p_registro_ok"><c:out value="${usuario_nuevo.nombre}"/> te has registrado con éxito</p>
		<p class="p_registro_ok">Tu usuario es: <c:out value="${usuario_nuevo.usuario}"/></p>
		
		<a href="${home}/login"><button id="btn_login">Login</button></a>	
	</div>
</body>
</html>