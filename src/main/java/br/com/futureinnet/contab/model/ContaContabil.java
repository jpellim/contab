package br.com.futureinnet.contab.model;

import java.io.Serializable;
import java.util.UUID;

public class ContaContabil implements Serializable {
 
	private static final long serialVersionUID = 4772959139043240907L;

	private UUID id;
	
	private String codigoContaContabil;
 
	public ContaContabil() {
		super();
	}

	public ContaContabil(UUID id, String codigoContaContabil) {
		super();
		this.id = id;
		this.codigoContaContabil = codigoContaContabil;
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
		ContaContabil other = (ContaContabil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCodigoContaContabil() {
		return codigoContaContabil;
	}

	public void setCodigoContaContabil(String codigoContaContabil) {
		this.codigoContaContabil = codigoContaContabil;
	}
 
}
