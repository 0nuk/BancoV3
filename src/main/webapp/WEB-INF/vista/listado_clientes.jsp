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
<link rel="stylesheet" href="${css}/listado_clientes.css">
<style type="text/css">
#div_menu {
	background-image: url("${images}/back.png");
	background-size: cover;
}
</style>
<script src="${js}/listado_clientes.js"></script>
</head>

<body>
	<div id="div_menu" class="menu">
		<div id="div_logout">
			<a href="${home}/logout"><button id="btn_logout">Logout</button></a> 
		</div>
		<h2>Clientes</h2>
		<div id="div_buscar_nuevo_cliente">
			<input id="input_buscar" type="text" name="nombre" placeholder="Nombre">
			<button id="btn_buscar">Buscar</button>
			<a href="${home}/nuevo_cliente"><button id="btn_nuevo_cliente">Nuevo Cliente</button></a>
		</div>
	</div>

		<table id="tabla_clientes">
			<thead>
				<tr>
					<th>Apellidos</th>
					<th>Nombres</th>
					<th>NIF</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cli" items="${clientes}">
					<tr id="${cli.id}" class="filas_cli">
						<td>${cli.getApellidos()}</td>
						<td>${cli.nombre}</td>
						<td>${cli.nif}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4"></td>
				</tr>
			</tfoot>
		</table>

		<div id="div_cliente">
			<h2>Cliente</h2>
			<form id="form_cliente">
				<label class="label_cliente" for="input_nombre">Nombre</label>
				<input id="input_nombre" name="input_nombre" readonly="readonly" class="input_cliente"/>
				<label class="label_cliente" for="input_apellido1">Apellido 1</label>
				<input id="input_apellido1" name="input_apellido1" readonly="readonly" class="input_cliente"/>
				<label class="label_cliente" for="input_apellido2">Apellido 2</label>
				<input id="input_apellido2" name="input_apellido2" readonly="readonly" class="input_cliente"/>
				<label class="label_cliente" for="input_nif">NIF</label>
				<input id="input_nif" name="input_nif" readonly="readonly" class="input_cliente"/>
				<label class="label_cliente" for="input_genero">Género</label>
				<input id="input_genero" name="input_genero" readonly="readonly" class="input_cliente"/>
				<label class="label_cliente" for="input_municipio">Municipio</label>
				<input id="input_municipio" name="input_municipio" readonly="readonly" class="input_cliente"/>
				<label class="label_cliente" for="input_provincia">Provincia</label>
				<input id="input_provincia" name="input_provincia" readonly="readonly" class="input_cliente"/>
				<input type="hidden" name="input_id" id="input_id">
			</form>
			<div class="div_btn">
				<button id="btn_eliminar">Eliminar</button>
				<p id="p_error_eliminar"></p>
			</div>
		</div>
		
		
		<div id="div_tarjetas_cuentas">
			<div id="div_tarjetas">
				<h2>Tarjetas</h2>
				<select name="select_tarjetas" id="select_tarjetas"></select>
				<div id="div_datos_tarjetas"></div>
			</div>
		
			<div id="div_cuentas">
				<h2>Cuentas</h2>
				<select name="select_cuenta" id="select_cuenta"></select> 
				<input class="input_cuentas" type="number" name="mes" placeholder="Mes" value="1" id="input_mes">
				<input class="input_cuentas" type="number" name="anyo" placeholder="Año" id="input_anyo" value="2020"> 
				<div class="div_btn"><button id="btn_extracto">Extracto</button></div>
			</div>
		</div>
			
			<div id="div_extracto"></div>
</body>
</html>