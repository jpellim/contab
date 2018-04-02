package br.com.futureinnet.contab.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.futureinnet.contab.exception.BusinessException;
import br.com.futureinnet.contab.model.EstatisticaContabil;
import br.com.futureinnet.contab.model.LancamentoContabil;
import br.com.futureinnet.contab.repository.LancamentoContabilSingleton;

/**
 * Classe de implementação do serviço de lancamentos contábeis
 * 
 * @author Jane
 *
 */
@Service
public class LancamentoContabilServiceBean implements LancamentoContabilService {
 
	@Override
	public List<LancamentoContabil> obterTodos() {
		 
		return LancamentoContabilSingleton.getInstance().obterTodos();
	}

	@Override
	public LancamentoContabil obterPorId(UUID id) {
		 
		return LancamentoContabilSingleton.getInstance().getById(id);
	}

	@Override
	public List<LancamentoContabil> obterPorContaContabil(String contaContabil) throws BusinessException {
		 
		return LancamentoContabilSingleton.getInstance().obterPorContaContabil(contaContabil);
	}

	@Override
	public void incluir(LancamentoContabil lancamento) {

		if (lancamento.getId() == null) {
			lancamento.setId(UUID.randomUUID());
		}
		
		LancamentoContabilSingleton.getInstance().addToMaps(lancamento);
		
	}

	@Override
	public void atualizar(LancamentoContabil lancamento) throws BusinessException {
		
		LancamentoContabilSingleton.getInstance().atualizar(lancamento);
		
	}
	 
	/**
	 * Coletar estatísticas gerais (de todos os lançamentos)
	 *
	 */
	@Override
	public EstatisticaContabil obterEstatistica() throws BusinessException {

		EstatisticaContabil estatistica = new EstatisticaContabil();
		
		List<LancamentoContabil> lancamentos = this.obterTodos();
		if (lancamentos == null || lancamentos.isEmpty()) {
			throw new BusinessException("Não há lancamentos para coleta de estatística ");
		}
		
		estatistica.setSoma(obterSoma(lancamentos));
		estatistica.setQuantidade(obterQuantidade(lancamentos));
		estatistica.setMedia(obterMedia(estatistica.getSoma(), estatistica.getQuantidade()));
		estatistica.setValorMaximo(obterMax(lancamentos));
		estatistica.setValorMinimo(obterMin(lancamentos));
  
		return estatistica;
	}
	
	
	/**
	 * Coletar estatísticas por conta contábil
	 * 
	 */
 	@Override
	public EstatisticaContabil obterEstatistica(String contaContabil) throws BusinessException {

		EstatisticaContabil estatistica = new EstatisticaContabil();
		
		List<LancamentoContabil> lancamentos = this.obterPorContaContabil(contaContabil);
		
		if (lancamentos == null || lancamentos.isEmpty()) {
			throw new BusinessException("Não há lancamentos para a Conta Contábil " + contaContabil);
		}
		estatistica.setSoma(obterSoma(contaContabil, lancamentos));
		estatistica.setQuantidade(obterQuantidade(contaContabil, lancamentos));
		estatistica.setMedia(obterMedia(estatistica.getSoma(), estatistica.getQuantidade()));
		estatistica.setValorMaximo(obterMax(contaContabil, lancamentos));
		estatistica.setValorMinimo(obterMin(contaContabil, lancamentos));
	 
		return estatistica;
	}
	
 	/*
 	 * Retorna o menor valor de lançamento (por conta contábil)
 	 * 
 	 */
	private BigDecimal obterMin(String codigoConta, List<LancamentoContabil> lancamentos) {
		return lancamentos.stream()   
			 	 .filter(lancamento -> lancamento.getContaContabil().getCodigoContaContabil().equals(codigoConta) )		
			 	 .map(LancamentoContabil::getValorLancamento) 
				 .min(Comparator.naturalOrder())
				 .get();	
	}
 
	/* 
	 * Retorna o menor valor de lançamento
	 * 
	 */
	private BigDecimal obterMin(List<LancamentoContabil> lancamentos) {
		return lancamentos.stream()   
			 	 .map(LancamentoContabil::getValorLancamento)   				
				 .min(Comparator.naturalOrder())
				 .get();	
	}
	 
	/*
	 * Retorna o maior valor de lançamento (por conta contábil) 
	 */
	private BigDecimal obterMax(String codigoConta, List<LancamentoContabil> lancamentos) {
		return lancamentos.stream()   
				 .filter(lancamento -> lancamento.getContaContabil().getCodigoContaContabil().equals(codigoConta) )		
			 	 .map(LancamentoContabil::getValorLancamento)   				
				 .max(Comparator.naturalOrder())
				 .get();	
	}
	
	/*
	 * Retorna o maior valor de lançamento  
	 * 
	 */
	private BigDecimal obterMax(List<LancamentoContabil> lancamentos) {
		return lancamentos.stream()   
			 	 .map(LancamentoContabil::getValorLancamento)   				
				 .max(Comparator.naturalOrder())
				 .get();	
	}

	/*
	 * Retorna a média dos lançamentos 
	 * 
	 */
	private BigDecimal obterMedia(BigDecimal valorTotal, Integer qtde) {

		return valorTotal.divide(new BigDecimal(qtde),2);	 
	}
	 
	/*
	 * Retorna a quantidade de lançamentos 
	 * 
	 */
	private Integer obterQuantidade(List<LancamentoContabil> lancamentos) {
		
		return (int) lancamentos.stream().count();			 
	}
	
	/*
	 * Retorna a quantidade de lançamentos de determinada conta contábil 
	 */
	private Integer obterQuantidade(String codigoConta, List<LancamentoContabil> lancamentos) {
		
		return (int) lancamentos.stream()  
					 .filter(lancamento -> lancamento.getContaContabil().getCodigoContaContabil().equals(codigoConta) )			 			
					 .count();			 
	}
	
	/*
	 * Retorna a somatória do valor de lançamentos
	 * 
	 */
	private BigDecimal obterSoma(List<LancamentoContabil> lancamentos) {
		
		return lancamentos.stream()   
				.map(LancamentoContabil::getValorLancamento)   				
				.reduce(BigDecimal.ZERO, BigDecimal::add);		 
	}
	
	/*
	 * Retorna a somatória do valor dos lançamentos de uma determinada conta contábil 
	 * 
	 */
	private BigDecimal obterSoma(String codigoConta, List<LancamentoContabil> lancamentos) {
 
		return lancamentos.stream()   
				.filter(lancamento -> lancamento.getContaContabil().getCodigoContaContabil().equals(codigoConta) )
				.map(LancamentoContabil::getValorLancamento)   
				.reduce(BigDecimal.ZERO, BigDecimal::add);	 
	}	
}