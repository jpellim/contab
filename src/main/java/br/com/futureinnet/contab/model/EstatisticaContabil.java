package br.com.futureinnet.contab.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EstatisticaContabil implements Serializable {
 
	private static final long serialVersionUID = 5708203976129560313L;

	private ContaContabil contaContabil;
	 
	private BigDecimal soma;
	
	private BigDecimal valorMinimo;
	
	private BigDecimal valorMaximo;
 
	private BigDecimal media;
	
	private Integer quantidade;
	
	
	public BigDecimal getSoma() {
		return soma;
	}

	public void setSoma(BigDecimal soma) {
		this.soma = soma;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public BigDecimal getMedia() {
		return media;
	}

	public void setMedia(BigDecimal media) {
		this.media = media;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ContaContabil getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}

}
