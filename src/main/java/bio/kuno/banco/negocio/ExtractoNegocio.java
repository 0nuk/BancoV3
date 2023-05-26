package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.List;

import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.modelo.Extracto;
import bio.kuno.banco.modelo.Movimiento;
import bio.kuno.banco.modelo.Tarjeta;

public interface ExtractoNegocio extends Serializable{
	
	Extracto generaExtracto(Cuenta cuenta, int anyo, int mes);
	
	List<Movimiento> getMovimientos(Extracto ext, Tarjeta tjta);
}
