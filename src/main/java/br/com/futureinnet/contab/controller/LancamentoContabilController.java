package br.com.futureinnet.contab.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.futureinnet.contab.exception.BusinessException;
import br.com.futureinnet.contab.model.EstatisticaContabil;
import br.com.futureinnet.contab.model.LancamentoContabil;
import br.com.futureinnet.contab.service.LancamentoContabilService;

@RestController
@RequestMapping(value = "/lancamento")
public class LancamentoContabilController {
	
	@Autowired
	LancamentoContabilService service;
	
	 
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LancamentoContabil>> obterTodos() {
	 
		return new ResponseEntity<List<LancamentoContabil>>(service.obterTodos(), HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LancamentoContabil> obterPorId(@PathVariable("id") UUID id) {
	 
		return new ResponseEntity<LancamentoContabil>(service.obterPorId(id), HttpStatus.OK);
	} 
	
	
	@RequestMapping(value = "/estatistica/{conta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstatisticaContabil> obterEstatisticaPorConta(@PathVariable("conta") String codigoConta) throws BusinessException {
	 
		return new ResponseEntity<EstatisticaContabil>(service.obterEstatistica(codigoConta), HttpStatus.OK);
	} 
	
	
	@RequestMapping(value = "/estatistica", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obterEstatisticaGeral() throws BusinessException {
	  
		return new ResponseEntity<EstatisticaContabil>(service.obterEstatistica(), HttpStatus.OK);
	} 
	
	
    // -------------------Criando um Lançamento-------------------------------------------
	 
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> incluirLancamento(@RequestBody LancamentoContabil lancamento, UriComponentsBuilder ucBuilder) {
       
     
        service.incluir(lancamento);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    // -------------------Atualizando um Lançamento-------------------------------------------
    
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarLancamento(@RequestBody LancamentoContabil lancamento, UriComponentsBuilder ucBuilder) throws BusinessException {
       
     
        service.atualizar(lancamento); 
   
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
