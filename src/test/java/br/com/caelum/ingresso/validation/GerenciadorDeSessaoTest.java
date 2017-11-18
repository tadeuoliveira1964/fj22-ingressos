package br.com.caelum.ingresso.validation;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.business.GerenciadorDeSessao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {

	private Filme rogueOne;
	private Sala sala3D;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;
	
	@Before
	public void preparaSessoes(){
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		this.sala3D = new Sala("Sala 3D");
		
		this.sessaoDasDez = new Sessao(sala3D,rogueOne, LocalTime.parse("10:00:00"));
		this.sessaoDasTreze = new Sessao(sala3D,rogueOne, LocalTime.parse("13:00:00"));
		this.sessaoDasDezoito = new Sessao(sala3D,rogueOne, LocalTime.parse("18:00:00"));
		
	}
	
	@Test
	public void deveAdicionarSEListaDaSessaoEstiverVazia(){
		List<Sessao> sessoes = Collections.emptyList();
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		boolean cabe = gerenciador.cabe(sessaoDasDez);
		
		Assert.assertTrue(cabe);
		
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario(){
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessaoDasDez));		
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente(){
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(sala3D,rogueOne,sessaoDasDez.getHorario().minusHours(1));
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente(){
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(sala3D,rogueOne,sessaoDasDez.getHorario().plusHours(1));
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes(){
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Assert.assertTrue(gerenciador.cabe(sessaoDasTreze));
	}
	
}
