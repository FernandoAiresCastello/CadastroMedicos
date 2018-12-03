package com.fernandoairescastello.cadastromedicos.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.fernandoairescastello.cadastromedicos.dao.MedicoDao;
import com.fernandoairescastello.cadastromedicos.model.Medico;

@Named
@SessionScoped
public class MedicoBean implements Serializable {
    
    @EJB
    private MedicoDao dao;
    
    private List<Medico> medicos = null;
    private Medico medicoSelecionado = null;
    private boolean filtro = false;
    private boolean novo = false;
    
    public List<Medico> getMedicos() {
        if (!filtro)
            medicos = dao.list();
        
        return medicos;
    }
    
    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public Medico getMedicoSelecionado() {
        return medicoSelecionado;
    }

    public void setMedicoSelecionado(Medico medicoSelecionado) {
        this.medicoSelecionado = medicoSelecionado;
    }
    
    public boolean getNovo() {
        return novo;
    }
    
    public void setNovo(boolean novo) {
        this.novo = novo;
    }
    
    public String create() {
        medicoSelecionado = new Medico();
        novo = true;
        return "editar_medico?faces-redirect=true";
    }

    public void delete(Medico medico) {
        dao.delete(medico);
    }
    
    public String edit(Medico medico) {
        medicoSelecionado = medico;
        novo = false;
        return "editar_medico?faces-redirect=true";
    }
    
    public String update() {
        dao.update(medicoSelecionado);
        return "lista_medicos?faces-redirect=true";
    }
    
    public String searchByName(String name) {

        if (name.isEmpty()) {
            filtro = false;
            medicos = dao.list();
        }
        else {
            filtro = true;
            medicos = dao.listByName(name);
        }
        return null;
    }
}
