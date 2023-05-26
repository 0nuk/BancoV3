package bio.kuno.banco.vistas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bio.kuno.banco.modelo.Usuario;
import bio.kuno.banco.negocio.UsuarioNegocio;
import bio.kuno.banco.negocio.UsuarioNegocioImpl;
import bio.kuno.banco.util.Util;

public class LoginAction implements Action{
	private UsuarioNegocio usuarioNegocio;
	public LoginAction() {
		usuarioNegocio = new UsuarioNegocioImpl();
	}
	
	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		return path;
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		String usr = req.getParameter("usuario");
		String pwd = req.getParameter("password");
		HttpSession sesion = req.getSession();
		String vista;
		if(Util.isNotEmpty(pwd) && Util.isNotEmpty(usr)) {			
			Usuario usuario = usuarioNegocio.valida(usr, pwd);
			if(usuario != null) {
				sesion.setAttribute("usr", usuario);
				vista = "redirect:listado_clientes";
			} else {
				sesion.setAttribute("error", "login_null");
				vista = "redirect:" + path;
			}
		} else {
			sesion.setAttribute("error", "vacio");
			vista = "redirect:" + path;
		}
		return vista;
	}

}
