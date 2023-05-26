package bio.kuno.banco.vistas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ActionsFactory {
	private Set<String> urlPublica;
	private Map<String, Action> actions;
	public final String VISTA_PRE = "/WEB-INF/vista/";
	public final String VISTA_post = ".jsp";
	
	public ActionsFactory() {
		creaActions();
	}
	
	public Action getAction(HttpServletRequest req) {
		String clave = req.getMethod() + req.getPathInfo();
		String clave2 = "GET-POST" + req.getPathInfo();
		Action respuesta;
		
		if(urlPublica.contains(req.getPathInfo().substring(1)) || isSesionActiva(req)) {
			if(actions.containsKey(clave)) {
				respuesta = actions.get(clave);
			} else if(actions.containsKey(clave2)) {
				respuesta = actions.get(clave2);
			} else {
				respuesta = actions.get("URL_INCORRECTA");
			} 	
		} else {
			respuesta = actions.get("URL_INCORRECTA");
		}
		return respuesta;
	}
	
	
	private void creaActions() {
		actions = new HashMap<>();
		actions.put("GET-POST/login", new LoginAction());
		actions.put("GET-POST/registro", new RegistroAction());	
		actions.put("GET/registro_ok", new RegistroOkAktion());
		actions.put("GET-POST/listado_clientes", new ListadoClientesAction());
		actions.put("GET-POST/nuevo_cliente", new NuevoClienteAction());
		actions.put("POST/buscar", new BuscarAction());
		actions.put("POST/eliminar", new EliminarAction());
		actions.put("POST/extracto", new ExtractoAction());
		actions.put("GET/logout", new LogoutAction());
		actions.put("URL_INCORRECTA", actions.get("GET/logout"));
		
		urlPublica = new HashSet<>();
		urlPublica.add("login");
		urlPublica.add("logout");
		urlPublica.add("registro");
		urlPublica.add("registro_ok");
	}
	
	private boolean isSesionActiva(HttpServletRequest req) {
		return req.getSession().getAttribute("usr") != null;
	}
}
