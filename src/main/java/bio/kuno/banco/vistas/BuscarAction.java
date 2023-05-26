package bio.kuno.banco.vistas;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bio.kuno.banco.modelo.Cliente;
import bio.kuno.banco.negocio.ClienteNegocio;
import bio.kuno.banco.negocio.ClienteNegocioImpl;

public class BuscarAction implements Action{
	private ClienteNegocio clienteNegocio;
	
	public BuscarAction() {
		clienteNegocio = new ClienteNegocioImpl();
	}
	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		String nombre = req.getParameter("nombre");
		req.getSession().setAttribute("nombre", nombre);
		Set<Cliente> clientes = clienteNegocio.getClientes(nombre);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(clientes);
		req.setAttribute("json", json);
		return "json";
	}

}
