package bio.kuno.banco.vistas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistroOkAktion implements Action{

	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		return path;
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
