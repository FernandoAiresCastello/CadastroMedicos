package com.fernandoairescastello.cadastromedicos.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="especialidade", schema="public")
public class Especialidade {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="especialidade_id", unique=true, nullable=false, updatable=false)
	private Integer especialidadeId;
	
	@Column(length=5, nullable=false)
	private String sigla;
	
	@Column(length=100, nullable=false)
	private String descricao;
	
	public Integer getEspecialidadeId() {
		return especialidadeId;
	}
	
	public void setEspecialidadeId(Integer especialidadeId) {
		this.especialidadeId = especialidadeId;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
	    return String.format("%s - %s", sigla, descricao); 
	}

    @Override
    public int hashCode() {
        return Objects.hash(descricao, especialidadeId, sigla);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Especialidade other = (Especialidade) obj;
        return Objects.equals(descricao, other.descricao) && Objects.equals(especialidadeId, other.especialidadeId)
                && Objects.equals(sigla, other.sigla);
    }
}
