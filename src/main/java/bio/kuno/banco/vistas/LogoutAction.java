package bio.kuno.banco.vistas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {

	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		return "login";
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
