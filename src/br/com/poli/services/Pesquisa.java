package br.com.poli.services;

import br.com.poli.pessoas.Aluno;
import br.com.poli.pessoas.Professor;
import br.com.poli.repositorio.Repositorio;
import br.com.poli.salas.Salas;

public class Pesquisa {
	
	private static final int totalAvisos = 10;
	
	public int getTotalAvisos(){
		return totalAvisos;
	}
	
	public Aluno[] pesquisar(Aluno aluno){

		Repositorio repositorio = new Repositorio();

		String listaAlunos = repositorio.visualizarAlunos();

		String nome = aluno.getNome().toUpperCase();

		if (nome.contains("  ")){
			nome = nome.replace("  ", "");
		}
		
		String nomes[] = new String[aluno.getTotalDeAlunos()];
		String dadosDoAluno[] = new String[aluno.getTotalDeAlunos()];
		String alunos[] = new String[aluno.getTotalDeAlunos()];

		int count = 0;
		int quantidade = 0;
		String caracteres = "";

		
		for(int n=0; n<listaAlunos.length(); n++){

			caracteres += listaAlunos.charAt(n);				
			caracteres = caracteres.toUpperCase();

			if(listaAlunos.charAt(n) == '-'){
				if(caracteres.contains(nome)){
					nomes[count] = caracteres;
					caracteres = "";
				}
			}

			if(listaAlunos.charAt(n) == ')' && nomes[count] != null ){
				dadosDoAluno[count] = caracteres;
				count++;
			}

			if(listaAlunos.charAt(n) == '\n'){
				caracteres = "";
			}			
		}

		for(count = 0; count<aluno.getTotalDeAlunos(); count++){
			if (nomes[count] != null)
			{
				alunos[quantidade] = nomes[count] + dadosDoAluno[count];
				quantidade++;
			}
		}

		Aluno[] alunoEscolhido = new Aluno[quantidade];
		
		if (quantidade >= 1)		
		{
			for (int id =0; id < quantidade; id++){
				alunoEscolhido[id] = separaInformacoesAluno(alunos[id]);
			}
		}

		else {
			return null;
		}


		return alunoEscolhido;
	}


	public Aluno separaInformacoesAluno(String informacoes){
		String caracteres = "";

		Aluno aluno = new Aluno("");

		for(int n=0; n<informacoes.length(); n++){
			caracteres+=informacoes.charAt(n);

			if(informacoes.charAt(n) == '('){
				caracteres = caracteres.replace("-", "");
				caracteres = caracteres.replace("(", "");
				aluno.setNome(caracteres);		
				caracteres = "";
			}


			if (informacoes.charAt(n) == ':'){					

				caracteres = caracteres.replace(":", "");
				aluno.setCpf(caracteres);
				caracteres = "";
			}

			if (informacoes.charAt(n) == '|'){

				caracteres = caracteres.replace("|", "");		
				aluno.setMatricula(caracteres);
				caracteres = "";
			}

			if (informacoes.charAt(n) == ')'){
				caracteres = caracteres.replace(")", "");
				aluno.setMotivo(caracteres);
				caracteres = "";
			}
		}
		return aluno;
	}
	
	public Professor[] pesquisar(Professor professor) {

		
		Repositorio repositorio = new Repositorio();	

		String listaProfessores = repositorio.visualizarCadeiras(); // Recebe a lista de todos os professores
																	// cadastrados do arquivo, que passa pro repositorio

		String nome = professor.getNome().toUpperCase();
		
		if (nome.contains("  ")){
			nome = nome.replace("  ", "");
		}

		String nomes[] = new String[professor.getTotalDeProfessores()];
		String cadeirasDoProfessor[] = new String[professor.getTotalDeProfessores()];
		String professores[] = new String[professor.getTotalDeProfessores()];

		int count = 0;
		int quantidade = 0;
		String caracteres = "";

		for (int n = 0; n < listaProfessores.length(); n++) {

			caracteres += listaProfessores.charAt(n);
			caracteres = caracteres.toUpperCase();

			if (listaProfessores.charAt(n) == '-') {
				if (caracteres.contains(nome)) {
					nomes[count] = caracteres;
					caracteres = "";
				}
			}
			
			if (listaProfessores.charAt(n) == ')' && nomes[count] != null) {
				cadeirasDoProfessor[count] = caracteres;
				count++;
			}

			if (listaProfessores.charAt(n) == '\n') {
				caracteres = "";
			}
		}

		for (count = 0; count < professor.getTotalDeProfessores(); count++) {
			if (nomes[count] != null) {
				professores[quantidade] = nomes[count] + cadeirasDoProfessor[count];
				quantidade++;
			}
		}
		
		Professor[] professorEscolhido = new Professor[quantidade];

		if (quantidade >= 1) 
		{
			for (int id = 0; id < quantidade; id++) {
				professorEscolhido[id] = separaInformacoesProfessor(professores[id]);
			}
		} else { 
			return null;
			
		}
		
		return professorEscolhido;

	}
	
	public Professor[] pesquisar(String cadeira){

		Repositorio repositorio = new Repositorio();
		String listaProfessores = repositorio.visualizarCadeiras();

		cadeira = cadeira.toUpperCase();

		if (cadeira.contains("  ")){
			cadeira = cadeira.replace("  ", "");
		}

		Professor professor = new Professor("");

		String nomes[] = new String[professor.getTotalDeProfessores()];
		String cadeirasDoProfessor[] = new String[professor.getTotalDeProfessores()];
		String professores[] = new String[professor.getTotalDeProfessores()];

		int count = 0;
		int quantidade = 0;
		String caracteres = "";

		for (int n=0; n<listaProfessores.length(); n++){

			caracteres += listaProfessores.charAt(n);
			caracteres = caracteres.toUpperCase();

			if (listaProfessores.charAt(n) == '-'){
				nomes[count] = caracteres;
				caracteres = "";
			}

			if (listaProfessores.charAt(n) == ')') {
				if(caracteres.contains(cadeira)){
				cadeirasDoProfessor[count] = caracteres;
				count++;
				}
			}

			if (listaProfessores.charAt(n) == '\n') {
				caracteres = "";
			}
		}


		for (count = 0; count < professor.getTotalDeProfessores(); count++) {
			if (nomes[count] != null) {
				professores[quantidade] = nomes[count] + cadeirasDoProfessor[count];
				quantidade++;
			}
		}

		Professor[] professorEscolhido = new Professor[quantidade];

		if (quantidade >= 1) 
		{
			for (int id = 0; id < quantidade; id++) {
				professorEscolhido[id] = separaInformacoesProfessor(professores[id]);
			}
		} else { 
			return null;

		}

		return professorEscolhido;
	}

	public Professor separaInformacoesProfessor(String informacoes) {		//Este método separa as cadeiras e 
		int count = 0;												// turmas de um determinado professor
		String caracteres = "";										// cadastrado

		Professor professor = new Professor("");

		String[] cadeira = new String[professor.getNumeroMax()];
		String[] turma = new String[professor.getNumeroMax()];
		String[] codigo = new String[professor.getNumeroMax()];

		for (int n = 0; n < informacoes.length(); n++) {

			caracteres += informacoes.charAt(n);

			if (informacoes.charAt(n) == '(') { // o que tiver antes de (
				// é professor.
				caracteres = caracteres.replace("-", "");
				caracteres = caracteres.replace("(", "");
				professor.setNome(caracteres);
				caracteres = "";
			}

			if (informacoes.charAt(n) == ':') { // o que tiver antes de :
				// é a cadeira
				caracteres = caracteres.replace(":", "");

				cadeira[count] = caracteres;
				professor.setCadeira(cadeira);
				caracteres = "";
			}

			if (informacoes.charAt(n) == '|') { // o que tiver antes de |
				caracteres = caracteres.replace("|", ""); // é o codigo da cadeira
				codigo[count] = caracteres;
				professor.setCodigo(codigo);
				caracteres = "";
			}

			if (informacoes.charAt(n) == ',') { // o que tiver antes da ,
				caracteres = caracteres.replace(",", ""); // é a turma
				turma[count] = caracteres;
				professor.setTurma(turma);
				caracteres = "";
				count++;
			}
		}
		return professor;
	}
	
	public Salas[] pesquisar(Salas sala){

		Repositorio repositorio = new Repositorio();
		String listaSalas = repositorio.visualizarSalas();


		String nome = sala.getSala().toUpperCase();

		if (nome.contains("  ")){
			nome = nome.replace("  ", "");
		}
		
		String nomes[] = new String[sala.getTotalDeSalas()];
		String dadosDaSala[] = new String[sala.getTotalDeSalas()];
		String salas[] = new String[sala.getTotalDeSalas()];

		int count = 0;
		int quantidade = 0;
		String caracteres = "";

		for(int n=0; n<listaSalas.length(); n++){

			caracteres += listaSalas.charAt(n);
			caracteres = caracteres.toUpperCase();

			if(listaSalas.charAt(n) == '-'){

				if(caracteres.contains(nome)){
					nomes[count] = caracteres;
					caracteres = "";

				}
			}

			if(listaSalas.charAt(n) == ')' && nomes[count] != null){
				dadosDaSala[count] = caracteres;
				count++;
			}

			if(listaSalas.charAt(n) == '\n'){
				caracteres = "";
			}
		}
		for (count = 0; count<sala.getTotalDeSalas(); count++){
			if (nomes[count] != null){
				salas[quantidade] = nomes[count] + dadosDaSala[count];
				quantidade++;
			}
		}
		
		Salas[] salaEscolhida = new Salas[quantidade];

		if (quantidade >= 1){ 					
			for(int id=0; id<quantidade; id++)							
			{
				salaEscolhida[id] = separaInformacoesSala(salas[id]);
			}
		}
		else{
			return null;
		}
	
			return salaEscolhida;
		}


	public Salas separaInformacoesSala(String informacoes){

		String caracteres = "";

		Salas sala = new Salas("");

		for(int n=0; n<informacoes.length(); n++){
			caracteres+=informacoes.charAt(n);

			if(informacoes.charAt(n) == '('){
				caracteres = caracteres.replace("-", "");
				caracteres = caracteres.replace("(", "");
				sala.setSala(caracteres);		
				caracteres = "";
			}

			if (informacoes.charAt(n) == ':'){					
				sala.setBloco(informacoes.charAt(n-1));
				caracteres = "";
			}

			if (informacoes.charAt(n) == '|'){

				caracteres = caracteres.replace("|", "");		
				sala.setCapacidade(caracteres);
				caracteres = "";
			}

			if (informacoes.charAt(n) == ','){
				caracteres = caracteres.replace(",", "");

				if(caracteres.contains("TRUE")){
					sala.setArCondicionado(true);
				}
				else{
					sala.setArCondicionado(false);
				}
				caracteres = "";
			}

			if (informacoes.charAt(n) == ')'){
				caracteres = caracteres.replace(")", "");

				if(caracteres.contains("TRUE")){
					sala.setProjetor(true);
				}
				else{
					sala.setProjetor(false);
				}
				caracteres = "";
			}

		}
		return sala;
	}
	
	public String[] pesquisarAvisos(){
		
		Repositorio repositorio = new Repositorio();
		String listaAvisos = repositorio.visualizarAviso();
		String[] avisos = new String[totalAvisos];
		
		String caracteres = "";
		int count = 0;

		for (int i = 0; i<listaAvisos.length(); i++){
			
			caracteres += listaAvisos.charAt(i);				
			caracteres = caracteres.toUpperCase();
			
			if(listaAvisos.charAt(i) == ')' ){
				caracteres = caracteres.replace(")", "");
				avisos[count] = caracteres;
				count++;
			}

			if(listaAvisos.charAt(i) == '\n'){
				caracteres = "";
			}	
			
		}
		
		String[] avisosPesquisados = new String[count];
		
		for(int i = 0; i<count; i++){
			avisosPesquisados[i] = avisos[i];
		}
		
		return avisosPesquisados;
	}
		
}
