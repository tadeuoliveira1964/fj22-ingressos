package br.com.caelum.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller
public class SessaoController {
	
	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer idSala){
		
		ModelAndView mav = new ModelAndView("sessao/sessao");
		
		mav.addObject("sala", salaDao.findOne(idSala) );
		mav.addObject("filmes", filmeDao.findAll());
		
		return mav;
	}

	@PostMapping("admin/sessao")
	public String salva(SessaoForm form){
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		
		sessaoDao.save(sessao);
	
		return "sala/"+form.getSalaId()+"sessoes";
	}
	
	
	
	
}
