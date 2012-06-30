package com.pichler.dto;

public class MedicamentoDTO {
	private String principioAtivo, referencia, generico, similar;
	public String getGenerico() {
		return generico;
	}

	public void setGenerico(String generico) {
		this.generico = generico;
	}

	public String getSimilar() {
		return similar;
	}

	public void setSimilar(String similar) {
		this.similar = similar;
	}

	private Long id;

	/**
	 * @return the principioAtivo
	 */
	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	/**
	 * @param principioAtivo the principioAtivo to set
	 */
	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String toString(){
		return principioAtivo;
	}

	public void setId(Long id) {
		this.id = id;
		
	}

	public Long getId() {
		return id;
	}
}
