package bio.kuno.banco.vistas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bio.kuno.banco.modelo.Usuario;
import bio.kuno.banco.negocio.UsuarioNegocio;
import bio.kuno.banco.negocio.UsuarioNegocioImpl;
import bio.kuno.banco.util.Util;

public class RegistroAction implements Action{
	private UsuarioNegocio usuarioNegocio;
	
	public RegistroAction() {
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
		String pwd2 = req.getParameter("password_rep");
		String nom = req.getParameter("nombre");
		String cor = req.getParameter("correo");
		HttpSession sesion = req.getSession();
		String vista;
		if(Util.isNotEmpty(usr) && Util.isNotEmpty(pwd) && Util.isNotEmpty(nom) && Util.isNotEmpty(cor)) {
			if(pwd.equals(pwd2)) {
				Usuario usuario = new Usuario();
				usuario.setCorreo(cor);
				usuario.setNombre(nom);
				usuario.setPassword(pwd);
				usuario.setUsuario(usr);
				usuario.setEnabled(true);
				boolean noExiste = usuarioNegocio.guardarUsuario(usuario);
				if(noExiste) {
					sesion.setAttribute("usuario_nuevo", usuario);
					vista = "redirect:registro_ok";
				} else {
					sesion.setAttribute("error", "existe");
					vista = "redirect:" + path;
				}
			} else {
				sesion.setAttribute("error", "password_rep");
				vista = "redirect:" + path;					
			}		
		} else {
			sesion.setAttribute("error", "vacio");
			vista = "redirect:" + path;			
		}
		return vista;
	}

}
