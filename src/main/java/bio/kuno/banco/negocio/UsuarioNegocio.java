package bio.kuno.banco.negocio;

import java.io.Serializable;

import bio.kuno.banco.modelo.Usuario;

public interface UsuarioNegocio extends Serializable{
	
	boolean guardarUsuario(Usuario usuario);
	
	Usuario valida(String usuario, String password);
}
