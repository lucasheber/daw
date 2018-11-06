package distribuidora.managebeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import distribuidora.dao.UsuarioDAO;
import distribuidora.modelo.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioMB {

	private Usuario usuario = new Usuario();
	
	public String login() {
		String redirect = "login?faces-redirect=true";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (usuarioDAO.existe(usuario))
			redirect = "login?faces-redirect=true";
		
		return redirect;
		
	}// login
	
	public boolean isLogado() {
		return usuario.getLogin() != null;
	}
	
	public String logout() {
		String redirect = "login?faces-redirect=true";
		this.usuario = new Usuario();
		
		return redirect;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
