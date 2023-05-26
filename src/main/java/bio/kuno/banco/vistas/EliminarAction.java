package bio.kuno.banco.vistas;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bio.kuno.banco.modelo.Cliente;
import bio.kuno.banco.negocio.ClienteNegocio;
import bio.kuno.banco.negocio.ClienteNegocioImpl;

public class EliminarAction implements Action{
	
	private ClienteNegocio clienteNegocio;
	
	public EliminarAction() {
		clienteNegocio = new ClienteNegocioImpl();
	}
	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		String jsonEliminar;
		String json;
		int idCliente = Integer.parseInt(req.getParameter("idCliente"));	
		System.out.println(idCliente);
		if(clienteNegocio.eliminarCliente(idCliente)) {
			jsonEliminar = "{\"mensaje\":\"El cliente fue correctamente eliminado\"}";
			Set<Cliente> clientes = clienteNegocio.getAllClientes();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonClientes = gson.toJson(clientes);
			json = "[" + jsonEliminar+ "," + jsonClientes + "]";
	
		} else {
			jsonEliminar = "[{\"mensaje\":\"El cliente no pudo ser eliminado: tiene cuentas asociadas\"}]";
			json = jsonEliminar;
		}
		System.out.println(json);
		req.setAttribute("json", json);
		return "json";
	}
	
}
