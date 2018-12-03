package com.fernandoairescastello.cadastromedicos.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fernandoairescastello.cadastromedicos.model.Especialidade;

@Stateless
public class EspecialidadeDao {

    @PersistenceContext(unitName = "cadastroMedicos")
    private EntityManager entityManager;
    
    public boolean update(Especialidade especialidade) {
        boolean ok = false;
        try {
            entityManager.merge(especialidade);
            ok = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public boolean delete(Especialidade especialidade) {
        boolean ok = false;
        try {
            especialidade = entityManager.find(Especialidade.class, especialidade.getEspecialidadeId());
            entityManager.remove(especialidade);
            ok = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public List<Especialidade> list() {
        List<Especialidade> especialidades = null;
        try {
            Query query = entityManager.createQuery("SELECT e FROM Especialidade e");
            especialidades = query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return especialidades;
    }
}
