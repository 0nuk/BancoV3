<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Usuario</title>
<link rel="stylesheet" href="${css}/nuevo_cliente.css">
<style type="text/css">
#div_menu {
	background-image: url("${images}/back.png");
	background-size: cover;
}
</style>

<script type="text/javascript">
</script>
</head>
<body>
	<div id="div_menu">
		<div id="div_logout">
			<a href="${home}/logout"><button id="btn_logout">Logout</button></a> 
		</div>
		
		<h2>Nuevo Cliente</h2>
	<div id="div_buscar_nuevo_cliente">	
		<a href="${home}/listado_clientes"><button id="btn_clientes">Clientes</button></a>
		<a href="${home}/nuevo_cliente"><button id="btn_nuevo_cliente">Nuevo Cliente</button></a>
	</div>	
	</div>
	<div id="div_center">
		<div id="div_nuevo_cliente">
			<form id="frm_nuevo_cliente" action="${home}/nuevo_cliente" method="post">
				<input class="input_nuevo_cliente" type="text" name="nombre" placeholder="Nombre">
				<input class="input_nuevo_cliente" type="text" name="apellido1" placeholder="Apellido 1">
				<input class="input_nuevo_cliente" type="text" name="apellido2" placeholder="Apellido 2">
				<input class="input_nuevo_cliente" type="text" name="nif" placeholder="NIF">
				<select class="input_nuevo_cliente" id="genero" name="sexo" id="select_genero">
					<option value="h">Hombre</option>
					<option value="m" selected="selected">Mujer</option>
				</select>
				<input class="input_nuevo_cliente" type="text" name="municipio" placeholder="Municipio">
				<input class="input_nuevo_cliente" type="text" name="provincia" placeholder="Provincia">
				<button id="btn_crear" type="submit">Crear</button>
				<p id="p_error_nuevo_cliente">
					<c:choose>
						<c:when test="${nuevo_cli_error eq 'si'}">Todos los campos se tienen que ser rellenados</c:when>
						<c:when test="${nuevo_cli_error eq 'no'}">Cliente registrado correctamente</c:when>
					</c:choose>
				</p>
			</form>
		</div>
	</div>
</body>
</html>