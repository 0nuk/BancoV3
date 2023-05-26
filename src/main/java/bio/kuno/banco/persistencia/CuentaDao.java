package bio.kuno.banco.persistencia;

import java.util.Set;

import bio.kuno.banco.modelo.Cuenta;

public interface CuentaDao extends GenericoDao<Integer, Cuenta> {

	Set<Cuenta> findByIdCliente(int idCliente);
}
