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

<script type="text/javascript">
	var mapTarjeta;

	function solicitudEliminar(ev){
		var idCliente = document.getElementById("input_id").value;
		console.log(idCliente);
		var params = "idCliente=" + encodeURIComponent(idCliente);
		var req = new XMLHttpRequest();
		req.open("post", "eliminar");
		req.onreadystatechange = function(){
			if(req.readyState == 4 && req.status == 200) {
				var json = JSON.parse(req.responseText);
				if(json.length == 2) {
					document.getElementById("p_error_eliminar").textContent = json[0].mensaje;
					cargaTabla(json[1]);
				} else {
					document.getElementById("p_error_eliminar").textContent = json[0].mensaje;
				}
			}
		};
		req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		req.send(params);
	}

	function solicitudExtracto(ev){
		var idCuenta = document.getElementById("select_cuenta").value;
		var anyo = document.getElementById("input_anyo").value;
		var mes = document.getElementById("input_mes").value;
		var params = "idCuenta=" + encodeURIComponent(idCuenta) + "&anyo=" + encodeURIComponent(anyo) + "&mes=" + encodeURIComponent(mes);
		var req = new XMLHttpRequest();
		req.open("post", "extracto");
		req.onreadystatechange = function(){
			if(req.readyState == 4 && req.status == 200) {
				document.getElementById("div_extracto").innerHTML = req.responseText;
			}
		};
		req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		req.send(params);
	}
	
	function solicitudCliente(ev) {
		var id = ev.currentTarget.id;
		var params = "id=" + encodeURIComponent(id);
		var req = new XMLHttpRequest();
		req.open("post", "listado_clientes");
		req.onreadystatechange = function() {
			if (req.readyState == 4 && req.status == 200) {
				reset();
				cargaCliente(req);
				cargaCuentas(req);
				cargaTarjetas(req);
			}
		};
		req.setRequestHeader('Content-type',
				'application/x-www-form-urlencoded');
		req.send(params);
	}

	function cargaCliente(req) {
		var json = JSON.parse(req.responseText);
		document.getElementById("input_nombre").value = json.cliente.nombre;
		document.getElementById("input_apellido1").value = json.cliente.apellido1;
		document.getElementById("input_apellido2").value = json.cliente.apellido2;
		document.getElementById("input_nif").value = json.cliente.nif;
		document.getElementById("input_genero").value = json.cliente.sexo;
		document.getElementById("input_municipio").value = json.cliente.municipio
		document.getElementById("input_provincia").value = json.cliente.provincia;
		document.getElementById("input_id").value = json.cliente.id;
	}

	function cargaCuentas(req) {
		var json = JSON.parse(req.responseText);
		var cuentas = json.cuentas;
		document.getElementById('select_cuenta').innerHTML = '';
		for (var i = 0; i < cuentas.length; i++) {
			var option = document.createElement('option');
			option.value = cuentas[i].id;
			option.textContent = cuentas[i].numero;
			document.getElementById('select_cuenta').appendChild(option);
		}
	}

	function cargaTarjetas(req) {
		var json = JSON.parse(req.responseText);
		var tarjetas = json.tarjetas;
		document.getElementById('select_tarjetas').innerHTML = '';
		mapTarjeta = new Map();
		var option_elige = document.createElement('option');
		option_elige.textContent = 'Elige';
		option_elige.hidden = true;
		document.getElementById('select_tarjetas').appendChild(option_elige);
		for (var i = 0; i < tarjetas.length; i++) {
			var option = document.createElement('option');
			mapTarjeta.set(tarjetas[i].id, tarjetas[i]);
			option.value = tarjetas[i].id;
			option.textContent = tarjetas[i].pan;
			document.getElementById('select_tarjetas').appendChild(option);
		}
		document.getElementById('select_tarjetas').addEventListener('change',
				selectTarjeta);
	}

	function selectTarjeta() {
		document.getElementById('div_datos_tarjetas').innerHTML = '';
		var id = document.getElementById('select_tarjetas').value
		var tarjeta = mapTarjeta.get(Number(id));

		var inptMarca = document.createElement('input');
		var lMarca = document.createElement('label');
		inptMarca.value = tarjeta.marca;
		inptMarca.name = 'input_marca';
		inptMarca.readOnly = true;
		lMarca.textContent = 'Marca';
		lMarca.htmlFor = 'input_marca';
		document.getElementById('div_datos_tarjetas').appendChild(lMarca);
		document.getElementById('div_datos_tarjetas').appendChild(inptMarca);

		var inptTipo = document.createElement('input');
		var lTipo = document.createElement('label');
		inptTipo.value = tarjeta.tipo;
		inptTipo.name = 'input_tipo';
		inptTipo.readOnly = true;
		lTipo.textContent = 'Tipo';
		lTipo.htmlFor = 'input_tipo';
		document.getElementById('div_datos_tarjetas').appendChild(lTipo);
		document.getElementById('div_datos_tarjetas').appendChild(inptTipo);

		var inptFecha = document.createElement('input');
		var lFecha = document.createElement('label');
		inptFecha.value = tarjeta.mesVencimiento + '/' + tarjeta.anyoVencimiento;
		inptFecha.name = 'input_fecha';
		inptFecha.readOnly = true;
		lFecha.textContent = 'Fecha Caducidad';
		lFecha.htmlFor = 'input_fecha';
		document.getElementById('div_datos_tarjetas').appendChild(lFecha);
		document.getElementById('div_datos_tarjetas').appendChild(inptFecha);

	}

	function solicitudClientes(ev) {

		var params = "nombre="
				+ encodeURIComponent(document.getElementById('input_buscar').value);
		var req = new XMLHttpRequest();
		req.open("post", "buscar");
		req.onreadystatechange = function() {
			if (req.readyState == 4 && req.status == 200) {
				reset();
				cargaTabla(JSON.parse(req.responseText));
			}
		};
		req.setRequestHeader('Content-type',
				'application/x-www-form-urlencoded');
		req.send(params);
	}

	function cargaTabla(json) {
		var clientes = json;
		document.querySelector('#tabla_clientes tbody').innerHTML = '';
		for (var i = 0; i < clientes.length; i++) {
			insertaFila(clientes[i]);
		}
		document.querySelector('#tabla_clientes tfoot tr td').textContent = 'Cantidad de clientes: ' + clientes.length;
	}

	function insertaFila(cliente) {
		var tr = document.createElement('tr');
		var td = document.createElement('td');
		tr.id = cliente.id;
		tr.addEventListener("click", solicitudCliente);
		tr.appendChild(td);
		td.textContent = cliente.apellido1 + ' ' + cliente.apellido2;

		td = document.createElement('td');
		tr.appendChild(td);
		td.textContent = cliente.nombre;

		td = document.createElement('td');
		tr.appendChild(td);
		td.textContent = cliente.nif;

		document.querySelector('#tabla_clientes tbody').appendChild(tr);
	}

	function reset(){
		elementos = document.querySelectorAll('p input, #select_tarjetas, #select_cuenta');
		for (let i = 0; i < elementos.length; i++) {
			elementos[i].value = '';
 			elementos[i].textContent = '';
 			elementos[i].innerHTML = '';
		}
		document.querySelector('#div_extracto').innerHTML = '';
		document.querySelector('#div_datos_tarjetas').innerHTML = '';
		document.querySelector('#input_nombre').value = '';
		document.querySelector('#p_error_eliminar').textContent = '';
	}

	window.onload = function() {
		var filas = document.getElementsByClassName("filas_cli");
		for (var i = 0; i < filas.length; i++) {
			filas[i].addEventListener("click", solicitudCliente);
		}
		document.getElementById("btn_buscar").addEventListener("click", solicitudClientes);
		document.getElementById("btn_extracto").addEventListener("click", solicitudExtracto);
		document.getElementById("btn_eliminar").addEventListener("click", solicitudEliminar);
	};

</script>
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