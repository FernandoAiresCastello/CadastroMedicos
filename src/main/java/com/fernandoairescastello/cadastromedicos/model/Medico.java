package com.fernandoairescastello.cadastromedicos.model;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "medico", schema = "public")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medico_id", unique = true, nullable = false, updatable = false)
    private Integer medicoId;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 1, nullable = false)
    private String sexo;

    @Column(length = 13, nullable = false)
    private String crm;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, crm, email, especialidade, medicoId, nome, sexo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Medico other = (Medico) obj;
        return Objects.equals(cpf, other.cpf) && Objects.equals(crm, other.crm) && Objects.equals(email, other.email)
                && Objects.equals(especialidade, other.especialidade) && Objects.equals(medicoId, other.medicoId)
                && Objects.equals(nome, other.nome) && Objects.equals(sexo, other.sexo);
    }
}
