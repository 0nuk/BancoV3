package bio.kuno.banco.vistas;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.modelo.Extracto;
import bio.kuno.banco.modelo.Movimiento;
import bio.kuno.banco.modelo.Tarjeta;
import bio.kuno.banco.negocio.CuentaNegocio;
import bio.kuno.banco.negocio.CuentaNegocioImpl;
import bio.kuno.banco.negocio.ExtractoNegocio;
import bio.kuno.banco.negocio.ExtractoNegocioImpl;

public class ExtractoAction implements Action{
	
	private CuentaNegocio cuentaNegocio;
	private ExtractoNegocio extractoNegocio;
	
	public ExtractoAction() {
		cuentaNegocio = new CuentaNegocioImpl();
		extractoNegocio = new ExtractoNegocioImpl();
	}
	
	@Override
	public String get(String path, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(String path, HttpServletRequest req, HttpServletResponse resp) {
		Extracto extracto = null;
		Cuenta cuenta = null;
		
		if(req.getParameter("mes") != null && req.getParameter("anyo") != null && req.getParameter("idCuenta") != null) {
			int mes = Integer.parseInt(req.getParameter("mes"));
			int anyo = Integer.parseInt(req.getParameter("anyo"));
			int idCuenta = Integer.parseInt(req.getParameter("idCuenta"));
			cuenta = cuentaNegocio.getCuenta(idCuenta);
			extracto = extractoNegocio.generaExtracto(cuenta, anyo, mes);
		}
		if(extracto != null) {
			Map<Tarjeta, Set<Movimiento>> mapTarjetaMovimientos = new LinkedHashMap<>();
			Map<Tarjeta, Double> mapTarjetaTotal = new LinkedHashMap<>();
			double totalTarjeta = 0;
			double totalExtracto = 0;
			for(Tarjeta tarjeta: cuenta.getTarjetas()) {
				List<Movimiento> movimientos = extractoNegocio.getMovimientos(extracto, tarjeta);
				mapTarjetaMovimientos.put(tarjeta, new TreeSet<>(movimientos));
				for(Movimiento movimiento : movimientos) {
					totalTarjeta += movimiento.getImporte();
				}
				mapTarjetaTotal.put(tarjeta, totalTarjeta);
				totalExtracto += totalTarjeta;
				totalTarjeta = 0;
			}
			req.setAttribute("ext", extracto);
			req.setAttribute("totalTotales", totalExtracto);
			req.setAttribute("mapTarTot", mapTarjetaTotal);
			req.setAttribute("mapTarMov", mapTarjetaMovimientos);
			return path;
		} else {
			return "extracto_fail";
		}
	}
	
}
