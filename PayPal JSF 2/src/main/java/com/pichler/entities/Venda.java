package com.pichler.entities;

/*
 * Venda é a associacao de um cliente e um produto. Neste exemplo uma venda só pode ocorrer de um produto por vez para simplicar o exemplo.
 * 
 * */
public class Venda {
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}
	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	private Cliente cliente;
	private Produto produto;
}
