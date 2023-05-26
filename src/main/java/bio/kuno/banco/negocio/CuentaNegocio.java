package bio.kuno.banco.negocio;

import java.io.Serializable;
import java.util.Set;

import bio.kuno.banco.modelo.Cliente;
import bio.kuno.banco.modelo.Cuenta;

public interface CuentaNegocio extends Serializable{
	
	Cuenta getCuenta(int id);
	
	Set<Cuenta> getCuentas(Cliente cliente);
}
