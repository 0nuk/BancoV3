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