package br.com.poli.salas;

public class Salas {
	
	private static int totalDeSalas = 50;								// importante, definirá o valor máximo no arquivo.
	
	private String sala;
	private String capacidade;
	
	private char bloco;
	
	private boolean arCondicionado;
	private boolean projetor;
	
	public Salas(String nome){
		this.sala = nome;
	}
	
	
	public int getTotalDeSalas(){
		return totalDeSalas;
	}
	
	
	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getSala() {
		return sala;
	}
	

	public void setBloco(char bloco) {
		this.bloco = bloco;
	}
	
	public char getBloco() {
		return bloco;
	}
	
	public void setCapacidade(String capacidade) {
		this.capacidade = capacidade;
	}
	
	public String getCapacidade() {
		return capacidade;
	}
	
	
	public void setArCondicionado(boolean ar){
		this.arCondicionado = ar;
	}
	
	public boolean isArCondicionado(){
		return arCondicionado;
	}
	
	
	public void setProjetor(boolean projetor){
		this.projetor = projetor;
	}
	
	public boolean isProjetor(){
		return projetor;
	}

	
	
	
}
