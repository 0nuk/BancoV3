package bio.kuno.banco.negocio;

import java.io.Serializable;

import bio.kuno.banco.modelo.Usuario;
import bio.kuno.banco.persistencia.UsuarioDao;
import bio.kuno.banco.persistencia.UsuarioDaoImpl;

public class UsuarioNegocioImpl implements UsuarioNegocio, Serializable{
	private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;
	
	public UsuarioNegocioImpl() {
		usuarioDao = new UsuarioDaoImpl();
	}

	@Override
	public boolean guardarUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario valida(String usuario, String password) {
		return usuarioDao.valida(usuario, password);
	}

}
