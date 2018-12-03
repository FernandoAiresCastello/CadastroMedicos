package com.fernandoairescastello.cadastromedicos.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.fernandoairescastello.cadastromedicos.dao.EspecialidadeDao;
import com.fernandoairescastello.cadastromedicos.model.Especialidade;

@Named
@SessionScoped
public class EspecialidadeBean implements Serializable {

    @EJB
    private EspecialidadeDao dao;

    private List<Especialidade> especialidades = null;
    private Especialidade especialidadeSelecionada = null;

    public List<Especialidade> getEspecialidades() {
        especialidades = dao.list();
        return especialidades;
    }
    
    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public Especialidade getEspecialidadeSelecionada() {
        return especialidadeSelecionada;
    }

    public void setEspecialidadeSelecionada(Especialidade especialidadeSelecionada) {
        this.especialidadeSelecionada = especialidadeSelecionada;
    }
    
    public String create() {
        especialidadeSelecionada = new Especialidade();
        return "editar_especialidade?faces-redirect=true";
    }

    public void delete(Especialidade especialidade) {
        dao.delete(especialidade);
    }
    
    public String edit(Especialidade especialidade) {
        especialidadeSelecionada = especialidade;
        return "editar_especialidade?faces-redirect=true";
    }
    
    public String update() {
        dao.update(especialidadeSelecionada);
        return "lista_especialidades?faces-redirect=true";
    }
}
