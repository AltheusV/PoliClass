package br.com.poli.tabela;

public class DadosTabela {
	
	private String bloco;
	private String sala;
	private String tipo;
	private String nome;
	private String turma;
	private String status;
	
	public DadosTabela(String bloco, String sala, String tipo, String nome, String turma, String status){
		this.bloco = bloco;
		this.sala = sala;
		this.tipo = tipo;
		this.nome = nome;
		this.turma = turma;
		this.status = status;
		
	}
	
	public String getBloco() {
		return bloco;
	}


	public void setBloco(String bloco) {
		this.bloco = bloco;
	}


	public String getSala() {
		return sala;
	}


	public void setSala(String sala) {
		this.sala = sala;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTurma() {
		return turma;
	}


	public void setTurma(String turma) {
		this.turma = turma;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
