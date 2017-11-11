package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {

	private Integer salaId;
	private Integer filmeId;
	private LocalTime horario;
	
	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao){
		Sala sala = salaDao.findOne(salaId);
		Filme filme = filmeDao.findOne(filmeId);
		
		return new Sessao(sala,filme, horario);
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	
	
}
