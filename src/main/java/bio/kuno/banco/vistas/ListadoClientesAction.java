package bio.kuno.banco.vistas;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bio.kuno.banco.modelo.Cliente;
import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.modelo.Tarjeta;
import bio.kuno.banco.negocio.ClienteNegocio;
import bio.kuno.banco.negocio.ClienteNegocioImpl;
import bio.kuno.banco.negocio.CuentaNegocio;
import bio.kuno.banco.negocio.CuentaNegocioImpl;
import bio.kuno.banco.negocio.TarjetaNegocio;
import bio.kuno.banco.negocio.TarjetaNegocioImpl;

public class ListadoClientesAction implements Action{
	private ClienteNegocio clienteNegocio;
	private TarjetaNegocio tarjetaNegocio;
	private CuentaNegocio cuentaNegocio;
	
	public ListadoClientesAction() {
		clienteNegocio = new ClienteNegocioImpl();
		tarjetaNegocio = new TarjetaNegocioImpl();
		cuentaNegocio = new CuentaNegocioImpl();
	}
	
	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		Set<Cliente> clientes = clienteNegocio.getAllClientes();
		req.setAttribute("clientes", clientes);
		return path;
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		String jsonPath = "json";
		if(req.getParameter("id")!=null) {
			int idCliente = Integer.parseInt(req.getParameter("id"));
			Cliente cliente = clienteNegocio.getClienteConCuentas(idCliente);
			Set<Tarjeta> tarjetas = tarjetaNegocio.getTarjetas(idCliente);
			Set<Cuenta> cuentas = cuentaNegocio.getCuentas(cliente);
			Gson Gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String clienteJson = Gson.toJson(cliente);
			String tarjetasJson = Gson.toJson(tarjetas);
			String cuentasJson = Gson.toJson(cuentas);
			String json = "{\"cliente\":"+clienteJson+",\"tarjetas\":"+tarjetasJson+",\"cuentas\":"+cuentasJson+"}";
			System.out.println(json);
			req.setAttribute("json", json);
		}
		return jsonPath;
	}

}
