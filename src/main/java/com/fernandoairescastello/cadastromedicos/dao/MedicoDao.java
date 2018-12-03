package com.fernandoairescastello.cadastromedicos.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fernandoairescastello.cadastromedicos.model.Medico;

@Stateless
public class MedicoDao {

    @PersistenceContext(unitName = "cadastroMedicos")
    private EntityManager entityManager;

    public boolean update(Medico medico) {
        boolean ok = false;
        try {
            String cpfSemMascara = medico.getCpf().toString().replaceAll("[\\.-]", "");
            medico.setCpf(cpfSemMascara);
            entityManager.merge(medico);
            ok = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public boolean delete(Medico medico) {
        boolean ok = false;
        try {
            medico = entityManager.find(Medico.class, medico.getMedicoId());
            entityManager.remove(medico);
            ok = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public List<Medico> list() {
        List<Medico> medicos = null;
        try {
            Query query = entityManager.createQuery("SELECT m FROM Medico m");
            medicos = query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return medicos;
    }

    public List<Medico> listByName(String name) {
        List<Medico> medicos = null;
        try {
            Query query = entityManager.createQuery("SELECT m FROM Medico m WHERE UPPER(m.nome) LIKE :name");
            query.setParameter("name", "%" + name.toUpperCase() + "%");
            medicos = query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return medicos;
    }
    
    public boolean existsWithCpf(String cpf) {
        boolean exists = false;
        try {
            Query query = entityManager.createQuery("SELECT COUNT(m.medicoId) FROM Medico m WHERE m.cpf = :cpf");
            query.setParameter("cpf", cpf);
            long count = (long)query.getSingleResult();
            exists = count > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
}
