package br.com.poli.services;

import br.com.poli.pessoas.Aluno;
import br.com.poli.pessoas.Professor;
import br.com.poli.repositorio.Repositorio;
import br.com.poli.salas.Salas;
import br.com.poli.tabela.Status;

public class Edicao {

	public void editar(String nome, Aluno aluno, boolean deletar){					// recebe o nome do aluno que será editado, e o objeto aluno com as novas informacoes
		Repositorio repositorio = new Repositorio();

		String listaAlunos = repositorio.visualizarAlunos();		// repassa o arquivo para uma string

		repositorio.limparAlunos();				// apaga o arquivo

		String caracteres = "";
		String listaAtualizada = "";
		String alunoComparado = "";

		for(int i=0; i<listaAlunos.length(); i++){

			caracteres += listaAlunos.charAt(i);

			if(listaAlunos.charAt(i) == '('){
				alunoComparado = caracteres;
				alunoComparado = alunoComparado.replace("-", "");
				alunoComparado = alunoComparado.replace("(", "");

				alunoComparado = alunoComparado.toUpperCase();

			}

			if(listaAlunos.charAt(i) == ')'){
				if(nome.equals(alunoComparado)){
					if(!deletar){
						aluno.setNome(aluno.getNome().replace("  ", ""));
						listaAtualizada = aluno.getNome() + " - (" + aluno.getCpf() + ":" + aluno.getMatricula() + "|" + aluno.getMotivo() + ")";
					}
					else{
						listaAtualizada = null;
					}
				}
				else{
					listaAtualizada=caracteres;
				}
				if(listaAtualizada!= null){
					repositorio.armazenarAlunos(listaAtualizada);				//atualiza arquivo
				}
			}

			if(listaAlunos.charAt(i) == '\n'){
				caracteres = "";
			}		
		}
	}

	public void editar(String nome, Salas sala, boolean deletar){				// edita a sala
		Repositorio repositorio = new Repositorio();
		String listaSalas = repositorio.visualizarSalas();		// repassa o arquivo para uma string

		repositorio.limparSalas();								// apaga o arquivo

		String caracteres = "";
		String listaAtualizada = "";
		String salaComparada = "";

		for (int i=0; i<listaSalas.length(); i++){
			caracteres+= listaSalas.charAt(i);

			if(listaSalas.charAt(i) == '('){
				salaComparada = caracteres;
				salaComparada = salaComparada.replace("-", "");
				salaComparada = salaComparada.replace("(", "");

				salaComparada = salaComparada.toUpperCase();
			}

			if(listaSalas.charAt(i) == ')'){
				if(nome.equals(salaComparada)){
					if(!deletar){
						sala.setSala(sala.getSala().replace("  ", ""));
						listaAtualizada = sala.getSala() + " - (" + sala.getBloco() + ":" + sala.getCapacidade() + "|" + sala.isArCondicionado() + "," + sala.isProjetor() + ")";
					}
					else{
						listaAtualizada = null;
					}
				}
				else{
					listaAtualizada=caracteres;
				}
				if(listaAtualizada!= null){
					repositorio.armazenarSalas(listaAtualizada);				//atualiza arquivo
				}
			}

			if(listaSalas.charAt(i) == '\n'){
				caracteres = "";
			}
		}
	}

	public void editarTabela(String nome, Salas sala, boolean deletar){				// edita a tabela
		Repositorio repositorio = new Repositorio();
		String listaTabela = repositorio.visualizarTabela();		// repassa o arquivo para uma string

		repositorio.limparTabela();								// apaga o arquivo

		String caracteres = "";
		String listaAtualizada = "";
		String salaComparada = "";
		nome = nome.replace("  ", "");

		for (int i=0; i<listaTabela.length(); i++){
			caracteres+= listaTabela.charAt(i);

			if(listaTabela.charAt(i) == '('){
				salaComparada = caracteres;
				salaComparada = salaComparada.replace("-", "");
				salaComparada = salaComparada.replace("(", "");
				salaComparada = salaComparada.replace("  ", "");
				salaComparada = salaComparada.toUpperCase();
			}

			if(listaTabela.charAt(i) == ')'){
				if(nome.equals(salaComparada)){
					if(!deletar){
						sala.setSala(sala.getSala().replace("  ", ""));
						listaAtualizada = sala.getSala() + " - (" + sala.getBloco() + "|_:_,_." + Status.DISPONIVEL.name() + ")";
					}
					else{
						listaAtualizada = null;
					}
				}
				else{
					listaAtualizada=caracteres;
				}
				if(listaAtualizada!= null){
					repositorio.armazenarTabela(listaAtualizada);				//atualiza arquivo
				}
			}

			if(listaTabela.charAt(i) == '\n'){
				caracteres = "";
			}
		}
	}

	public void editar(String nome, Professor professor, boolean deletar){
		Repositorio repositorio = new Repositorio();
		String listaProfessores = repositorio.visualizarCadeiras();

		repositorio.limparCadeiras();

		String caracteres = "";
		String listaAtualizada = "";
		String professorComparado = "";

		for (int i=0; i<listaProfessores.length(); i++){

			caracteres += listaProfessores.charAt(i);

			if(listaProfessores.charAt(i) =='('){
				professorComparado = caracteres;
				professorComparado = professorComparado.replace("-", "");
				professorComparado = professorComparado.replace("(", "");

				professorComparado = professorComparado.toUpperCase();
			}

			if(listaProfessores.charAt(i) == ')'){
				if(nome.equals(professorComparado)){
					if(!deletar){
						professor.setNome(professor.getNome().replace("  ", ""));
						String informacoesDoProfessor = professor.getNome() + " - (";

						for (int count=0; count<professor.getCadeira().length; count++){

							informacoesDoProfessor += professor.getCadeira()[count] + ":" + professor.getCodigo()[count] + "|" + professor.getTurma()[count] + ","; 

						}
						informacoesDoProfessor += ")";
						listaAtualizada = informacoesDoProfessor;
					}else{
						listaAtualizada = null;
					}
				}else{
					listaAtualizada = caracteres;
				}
				if(listaAtualizada!= null){
					repositorio.armazenarCadeiras(listaAtualizada);
				}
			}
			if(listaProfessores.charAt(i) == '\n'){
				caracteres = "";
			}
		}
	}

	public void editar(String aviso, String novoAviso, boolean deletar){
		Repositorio repositorio = new Repositorio();
		String listaAvisos = repositorio.visualizarAviso();	

		repositorio.limparAviso();

		String caracteres = "";
		String listaAtualizada = "";
		
		for(int i = 0; i<listaAvisos.length(); i++){
			caracteres += listaAvisos.charAt(i);
			caracteres = caracteres.toUpperCase();
			
			if(listaAvisos.charAt(i)==')'){
				if(aviso.equals(caracteres.replace(")", ""))){
					if(!deletar){
						listaAtualizada = novoAviso+=")";
					}
					else{
						listaAtualizada = null;
					}
				}
				else{
					listaAtualizada = caracteres;
				}

				if(listaAtualizada!= null){
					repositorio.armazenarAviso(listaAtualizada);
				}
			}

			if(listaAvisos.charAt(i) == '\n'){
				caracteres = "";
			}	

		}


	}

}

