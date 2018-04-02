package br.com.futureinnet.contab.service;

import java.util.List;
import java.util.UUID;

import br.com.futureinnet.contab.exception.BusinessException;
import br.com.futureinnet.contab.model.EstatisticaContabil;
import br.com.futureinnet.contab.model.LancamentoContabil;

public interface LancamentoContabilService {


	List<LancamentoContabil> obterTodos();
	
	LancamentoContabil obterPorId(UUID id);
	
	List<LancamentoContabil> obterPorContaContabil(String contaContabil) throws BusinessException;
	
	EstatisticaContabil obterEstatistica(String contaContabil) throws BusinessException;
	
	EstatisticaContabil obterEstatistica() throws BusinessException;
	 
	void incluir(LancamentoContabil lancamento);
	
	void atualizar(LancamentoContabil lancamento) throws BusinessException;
}
