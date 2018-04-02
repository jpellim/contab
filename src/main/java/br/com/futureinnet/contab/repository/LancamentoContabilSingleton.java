package br.com.futureinnet.contab.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.futureinnet.contab.exception.BusinessException;
import br.com.futureinnet.contab.model.LancamentoContabil;

/**
 * 
 * Implementação do repository de Lançamentos Contábeis
 * em memória
 * 
 * 
 * @author Jane
 *
 */
public class LancamentoContabilSingleton {
 
 
    private static final LancamentoContabilSingleton instance = new LancamentoContabilSingleton();

    private static Map<UUID, LancamentoContabil> syncMapLancamentos = Collections.synchronizedMap(new HashMap<UUID, LancamentoContabil>());
 
    private static Map<String, List<LancamentoContabil>> syncMapContas = Collections.synchronizedMap(new HashMap<String, List<LancamentoContabil>>());

    
    private LancamentoContabilSingleton(){}

    public static LancamentoContabilSingleton getInstance(){
        return instance;
    }
    
    public List<LancamentoContabil> obterTodos() {
       return new ArrayList<LancamentoContabil>(syncMapLancamentos.values());
    }
    
    public LancamentoContabil getById(UUID id) {
    	
    	return syncMapLancamentos.get(id);
    	
    }
    
    public List<LancamentoContabil> obterPorContaContabil(String contaContabil) throws BusinessException {
    	 
    	if (syncMapContas == null) {
    		throw new BusinessException("Não há lançamentos no sistema para coletar Estatísticas.");
    	}
    	
    	if (syncMapContas.get(contaContabil)!=null) { 
    		return new ArrayList<LancamentoContabil>(syncMapContas.get(contaContabil));
    	} else {
    		throw new BusinessException("Conta Contábil não encontrada.");
    	}
    }
    
    public void addToMaps(LancamentoContabil lancamento) {
    	
    	syncMapLancamentos.put(lancamento.getId(), lancamento);
    	
    	List<LancamentoContabil> lista = syncMapContas.get(lancamento.getContaContabil().getCodigoContaContabil());
    	
    	if (lista == null) {
    		lista = new ArrayList<LancamentoContabil>();
    	}
    	lista.add(lancamento);
    	
    	syncMapContas.put(lancamento.getContaContabil().getCodigoContaContabil(), lista);
    }
     
    
    public void atualizar(LancamentoContabil lancamento) throws BusinessException {
    	
    	LancamentoContabil lanc = syncMapLancamentos.get(lancamento.getId());
    	
    	if (lanc == null) {
    		throw new BusinessException("Lançamento não encontrado no sistema " + lancamento.getId());
    	}
    	
    	lanc.setContaContabil(lancamento.getContaContabil());
    	lanc.setDataLancamento(lancamento.getDataLancamento());
    	lanc.setValorLancamento(lancamento.getValorLancamento());
    }
}