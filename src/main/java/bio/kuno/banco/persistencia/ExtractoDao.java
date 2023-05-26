package bio.kuno.banco.persistencia;

import bio.kuno.banco.modelo.Cuenta;
import bio.kuno.banco.modelo.Extracto;

public interface ExtractoDao extends GenericoDao<Integer, Extracto> {
	
	Extracto extractoPorFecha(Cuenta c, int anyo, int mes);
}
