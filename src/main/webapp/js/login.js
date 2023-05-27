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