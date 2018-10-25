package br.com.upabicas.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.upabicas.dao.DAO;
import br.com.upabicas.modelo.Medico;

@ViewScoped
@ManagedBean
public class MedicoBeans {

	private Medico medico = new Medico();
	private List<Medico> medicos = new ArrayList<>();
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public boolean insert () {
		
		if (getMedico().getId() != null)
			return update();
		
		DAO<Medico> dao = new DAO<>(Medico.class);
		
		boolean result = dao.insert(getMedico());
		
		setMedico(new Medico());
		getMedicos();
		
		return result;
	}
	
	public boolean remove (Medico medico) {
		DAO<Medico> dao = new DAO<>(Medico.class);
		
		boolean result = dao.remove(medico);
		
		getMedicos();
		
		return result;
	}
	
	public boolean update () {
		DAO<Medico> dao = new DAO<>(Medico.class);
		
		boolean result = dao.update(getMedico());
		
		setMedico(new Medico());
		getMedicos();
		
		return result;
	}
	
	public void cancel () {
		setMedico(new Medico());
	}
	

	public List<Medico> getMedicos () {
		DAO<Medico> dao = new DAO<>(Medico.class);
		
		return this.medicos = dao.list();
	}
	
}
