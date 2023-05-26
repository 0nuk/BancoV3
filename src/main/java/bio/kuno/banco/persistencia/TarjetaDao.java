package bio.kuno.banco.persistencia;

import java.util.Set;

import bio.kuno.banco.modelo.Tarjeta;

public interface TarjetaDao extends GenericoDao<Integer, Tarjeta>{
	Set<Tarjeta> findByIdCliente(int idCliente);
}
