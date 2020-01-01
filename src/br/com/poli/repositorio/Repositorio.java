package br.com.poli.repositorio;

import br.com.poli.arquivos.Arquivo;

public class Repositorio {							// O repositorio é a ponte entre o codigo e o arquivo
	
	public void armazenarAlunos(String informacoes){
		Arquivo arquivo = new Arquivo();
		arquivo.salvarAlunos(informacoes);
	}
	
	public String visualizarAlunos(){
		Arquivo arquivo = new Arquivo();
		String informacoes = arquivo.lerAlunos();
		return informacoes;
	}
	
	public void limparAlunos(){
		Arquivo arquivo = new Arquivo();
		arquivo.deletarAlunos();
	}
	
	public void armazenarCadeiras(String informacoes){
		Arquivo arquivo = new Arquivo();
		arquivo.salvarCadeiras(informacoes);
	}
	
	public String visualizarCadeiras(){
		Arquivo arquivo = new Arquivo();
		String informacoes = arquivo.lerCadeiras();
		return informacoes;
	}
	
	public void limparCadeiras(){
		Arquivo arquivo = new Arquivo();
		arquivo.deletarCadeiras();
	}
	
	public void armazenarSalas(String informacoes){
		Arquivo arquivo = new Arquivo();
		arquivo.salvarSalas(informacoes);
	}
	
	public String visualizarSalas(){
		Arquivo arquivo = new Arquivo();
		String informacoes = arquivo.lerSalas();
		return informacoes;
	}
	
	public void limparSalas(){
		Arquivo arquivo = new Arquivo();
		arquivo.deletarSalas();
	}
	
	public void armazenarTabela(String informacoes){
		Arquivo arquivo = new Arquivo();
		arquivo.salvarTabela(informacoes);
	}

	public String visualizarTabela(){
		Arquivo arquivo = new Arquivo();
		String informacoes = arquivo.lerTabela();
		return informacoes;
	}
	
	public void limparTabela(){
		Arquivo arquivo = new Arquivo();
		arquivo.deletarTabela();
	}
	
	public void armazenarAviso(String informacoes){
		Arquivo arquivo = new Arquivo();
		arquivo.salvarAviso(informacoes);
	}

	public String visualizarAviso(){
		Arquivo arquivo = new Arquivo();
		String informacoes = arquivo.lerAviso();
		return informacoes;
	}
	
	public void limparAviso(){
		Arquivo arquivo = new Arquivo();
		arquivo.deletarAviso();
	}
}
