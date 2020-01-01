package br.com.poli.pessoas;

public class Professor extends Pessoa {
	
	private static final int numeroMax = 5;
	private static final int totalDeProfessores = 200;					// importante, definirá o valor máximo no arquivo.
	
	private String cadeira[] = new String[numeroMax];
	private String turma[] = new String[numeroMax];
	private String codigo[] = new String[numeroMax];
	
	
	public Professor(String nome){
		super(nome);
		
	}
	
	public int getNumeroMax(){
		return numeroMax;
	}
	
	public int getTotalDeProfessores(){
		return totalDeProfessores;
	}
	
	
	public void setCadeira(String cadeira[]){
		this.cadeira = cadeira;
	}
	
	public String[] getCadeira(){
		return cadeira;
	}
	
	public void setTurma(String turma[]){
		this.turma = turma;
	}
	
	public String[] getTurma(){
		return turma;
	}
	
	public void setCodigo(String codigo[]){
		this.codigo = codigo;
	}
	
	public String[] getCodigo(){
		return codigo;
	}
	
}
