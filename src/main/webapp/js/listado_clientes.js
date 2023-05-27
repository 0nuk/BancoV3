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
