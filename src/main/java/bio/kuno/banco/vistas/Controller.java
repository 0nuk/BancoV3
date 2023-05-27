package bio.kuno.banco.vistas;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/home/*")
public class Controller extends HttpServlet {
	
	private String context;
	private ActionsFactory af;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Action actual;
		actual = af.getAction(req);
		String vista = actual.execute(req, resp);
		if(vista.startsWith("redirect")) {
			resp.sendRedirect(context + "/home/" + vista.substring(9));
		} else {
			req.getRequestDispatcher(af.VISTA_PRE + vista + af.VISTA_post).forward(req, resp);
		}
	}

	@Override
	public void init() throws ServletException {
		af = new ActionsFactory();
		ServletContext app = getServletContext();
		context = app.getContextPath();
		app.setAttribute("context", context);
		app.setAttribute("home", context + "/home");
		app.setAttribute("css", context + "/css");
		app.setAttribute("images", context + "/images");
		app.setAttribute("js", context + "/js");
	}
}
