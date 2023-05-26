package bio.kuno.banco.persistencia;

import bio.kuno.banco.modelo.Usuario;

public interface UsuarioDao {
	
	boolean save(Usuario u);
	
	Usuario valida(String usr, String pwd);
	
}
