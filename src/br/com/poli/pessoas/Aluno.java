package br.com.poli.pessoas;

public class Aluno extends Pessoa{
	
	private static final int totalDeAlunos = 1000;				// importante, definirá o valor máximo no arquivo.
	private String cpf;
	private String matricula;
	private String motivoDaReserva;
	
	public Aluno(String nome){
		super(nome);
	}
	
	public int getTotalDeAlunos(){
		return totalDeAlunos;
	}
	
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	
	public String getCpf(){
		return cpf;
	}
	
	public void setMatricula(String matricula){
		this.matricula = matricula;
	}
	
	public String getMatricula(){
		return matricula;
	}
	
	public void setMotivo(String motivo){
		this.motivoDaReserva = motivo;
	}
	
	public String getMotivo(){
		return motivoDaReserva;
	}
}
