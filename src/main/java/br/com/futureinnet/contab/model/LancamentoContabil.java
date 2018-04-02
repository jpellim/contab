package br.com.futureinnet.contab.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class LancamentoContabil implements Serializable {
  
	private static final long serialVersionUID = 4295489990572139346L;

	private UUID id;
	
	private ContaContabil contaContabil;
	  
	private Date dataLancamento;
	 
	private BigDecimal valorLancamento;
	 
 
	public LancamentoContabil() {
		super();
	 
	}

	public LancamentoContabil(UUID id, ContaContabil contaContabil, Date dataLancamento, BigDecimal valorLancamento) {
		super();
		this.id = id;
		this.contaContabil = contaContabil;
		this.dataLancamento = dataLancamento;
		this.valorLancamento = valorLancamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LancamentoContabil other = (LancamentoContabil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ContaContabil getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}


}
