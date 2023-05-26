package bio.kuno.banco.vistas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bio.kuno.banco.modelo.Cliente;
import bio.kuno.banco.modelo.Sexo;
import bio.kuno.banco.negocio.ClienteNegocio;
import bio.kuno.banco.negocio.ClienteNegocioImpl;
import bio.kuno.banco.util.Util;

public class NuevoClienteAction implements Action{
	private ClienteNegocio clienteNegocio;
	
	public NuevoClienteAction() {
		clienteNegocio = new ClienteNegocioImpl();
	}
	
	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		if(req.getSession().getAttribute("nuevo_cli_error") != null) {
			req.setAttribute("nuevo_cli_error", req.getSession().getAttribute("nuevo_cli_error"));
			req.getSession().setAttribute("nuevo_cli_error", null);
		}
		return path;
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession sesion = req.getSession();
		String nombre = req.getParameter("nombre");
		String apellido1 = req.getParameter("apellido1");
		String apellido2 = req.getParameter("apellido2");
		String nif = req.getParameter("nif");
		String municipio = req.getParameter("municipio");
		String provincia = req.getParameter("provincia");
		String sexo = req.getParameter("sexo");
		Cliente cliente = new Cliente();
		if (Util.isNotEmpty(nombre) && Util.isNotEmpty(apellido1) && Util.isNotEmpty(nif) && Util.isNotEmpty(municipio) && Util.isNotEmpty(provincia) && Util.isNotEmpty(sexo)) {
			cliente.setNombre(nombre);
			cliente.setApellido1(apellido1);
			cliente.setApellido2(apellido2);
			cliente.setNif(nif);
			cliente.setMunicipio(municipio);
			cliente.setProvincia(provincia);
			if(sexo.equals("h")) {
				cliente.setSexo(Sexo.H);					
			} else {
				cliente.setSexo(Sexo.M);
			}
			cliente = clienteNegocio.actualizarCliente(cliente);
			sesion.setAttribute("nuevo_cli_error", "no");
		} else {
			sesion.setAttribute("nuevo_cli_error", "si");
		}
		return "redirect:" + path; 
	}

}
