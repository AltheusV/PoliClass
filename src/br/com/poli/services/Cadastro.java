package br.com.poli.services;

import br.com.poli.pessoas.Aluno;
import br.com.poli.pessoas.Professor;
import br.com.poli.repositorio.Repositorio;
import br.com.poli.salas.Salas;

public class Cadastro {
	
	public String cadastrar(Aluno aluno){				// cadastro Alunos

		Repositorio repositorio = new Repositorio();	// cria um novo repositorio

		String informacoesDoAluno = "";

		informacoesDoAluno += aluno.getNome() + " - (";

		informacoesDoAluno += aluno.getCpf() + ":" + aluno.getMatricula() + "|" + aluno.getMotivo(); 

		informacoesDoAluno += ")";

		if (informacoesDoAluno.contains("null")){			// caso alguma informacao nao seja informada corretamente, nao cadastra. 
			return "ERRO AO CADASTRAR ALUNO!";
		}

		else{
			repositorio.armazenarAlunos(informacoesDoAluno);		// o repositorio irá repassar as informacoes para o arquivo
			return "ALUNO CADASTRADO COM SUCESSO!";
		}

	}
	
	public String cadastrar(Professor professor){					// cadastro Professor

		int quantidadeDeCadeiras = professor.getCadeira().length;			// recebe a quantidade de cadeiras de um professor

		Repositorio repositorio = new Repositorio();		// cria um novo repositorio

		String informacoesDoProfessor = professor.getNome() + " - (";


		for (int count=0; count<quantidadeDeCadeiras; count++){

			informacoesDoProfessor += professor.getCadeira()[count] + ":" + professor.getCodigo()[count] + "|" + professor.getTurma()[count] + ","; 

		}

		informacoesDoProfessor += ")";

		if (informacoesDoProfessor.contains("null")){			// caso alguma informacao nao seja informada corretamente, nao cadastra. 
			return "ERRO AO CADASTRAR PROFESSOR!";
		}

		else{
			repositorio.armazenarCadeiras(informacoesDoProfessor);			// o repositorio irá repassar as informacoes para o arquivo
			return "PROFESSOR CADASTRADO COM SUCESSO!";
		}

	}
	
	public String cadastrar(Salas sala){
		Repositorio repositorio = new Repositorio();			// cria um novo repositorio

		String informacoesDaSala = "";

		informacoesDaSala += sala.getSala() + " - (";

		informacoesDaSala += sala.getBloco() + ":" + sala.getCapacidade() + "|" + sala.isArCondicionado() + "," + sala.isProjetor();

		if (informacoesDaSala.contains("null")){				// caso alguma informacao nao seja informada corretamente, nao cadastra. 
			return "ERRO AO CADASTRAR SALA!";
		}
		else{
			repositorio.armazenarSalas(informacoesDaSala + ")");				// o repositorio irá repassar as informacoes para o arquivo
			return "SALA CADASTRADA COM SUCESSO!";
		}
	}
	
	public String cadastrar(String aviso){
		Repositorio repositorio = new Repositorio();
		
		if (aviso.contains("null")){				// caso alguma informacao nao seja informada corretamente, nao cadastra. 
			return "ERRO AO CADASTRAR O AVISO!";
		}
		else{
			repositorio.armazenarAviso(aviso + ")");				// o repositorio irá repassar as informacoes para o arquivo
			return "AVISO CADASTRADO COM SUCESSO!";
		}
		
	}
}
