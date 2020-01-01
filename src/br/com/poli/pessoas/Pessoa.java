package br.com.poli.pessoas;

public abstract class Pessoa {
	
	private String nome;
	
	public Pessoa(String nome){
		this.nome = nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
}
