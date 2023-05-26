package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.modelo.Extracto;
import bio.kuno.banco.modelo.Movimiento;
import bio.kuno.banco.modelo.Tarjeta;
import bio.kuno.banco.persistencia.ExtractoDao;
import bio.kuno.banco.persistencia.ExtractoDaoImpl;

public class ExtractoNegocioImpl implements ExtractoNegocio, Serializable {

	private static final long serialVersionUID = 1L;
	private ExtractoDao extractoDao;
	
	public ExtractoNegocioImpl() {
		extractoDao = new ExtractoDaoImpl();
	}
	
	@Override
	public Extracto generaExtracto(Cuenta cuenta, int anyo, int mes) {
		return extractoDao.extractoPorFecha(cuenta, anyo, mes);
	}

	@Override
	public List<Movimiento> getMovimientos(Extracto ext, Tarjeta tjta) {
		Extracto extracto = extractoDao.findByIdEager(ext.getId());
		List<Movimiento> movimientos = new ArrayList<>();
		for(Movimiento movimiento : extracto.getMovimientos()) {
			if(movimiento.getTarjeta().equals(tjta)) {
				movimientos.add(movimiento);
			}
		}
		return movimientos;
	}
}
