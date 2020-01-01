package br.com.poli.view;
import java.util.Collections;


import br.com.poli.pessoas.Aluno;
import br.com.poli.pessoas.Professor;
import br.com.poli.services.Pesquisa;
import br.com.poli.tabela.DadosTabela;
import br.com.poli.tabela.Status;
import br.com.poli.tabela.Tabela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AlteracaoController {
	private String sala;
	private String bloco;
	private String nome;
	private String tipo;
	private String turma;
	private Status status;
	private String nomeFiltro = "";

	@FXML
	private RadioButton rbAluno;
	@FXML
	private RadioButton rbProfessor;
	@FXML
	private ComboBox <String> listaPessoas;
	@FXML
	private ComboBox <String> listaCadeiras;
	@FXML
	private Label labelPessoas;
	@FXML
	private Label labelCadeiras;
	@FXML
	private TextField textoFiltro;
	@FXML
	private TextField salaSelecionada;
	@FXML
	private TextField blocoSelecionado;




	public void setSala(String sala){
		this.sala = sala;
	}

	public String getSala(){
		return sala;
	}
	public void setBloco(String bloco){
		this.bloco = bloco;
	}

	public String getBloco(){
		return bloco;
	}



	public void initialize(){
		getInformacoes();
	}


	public void alterar(ActionEvent event){								//metodo principal, atualiza na tabela
		if(nome==null || turma == null || status == null || tipo == null){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Informacoes incorretas!", "Por favor preencha todas as informacoes.");
		}
		else{
			DadosTabela dados = new DadosTabela(getBloco(),getSala(),tipo,nome,turma,status.name());
			Tabela.AtualizarSala(dados, false);	
			voltarParaTabela(event);				// false pois a sala será utilizada, (nao estara disponivel)
		}
	}
	
	public void filtrar(){
		nomeFiltro = textoFiltro.getText();
		getInformacoes();
	}

	public void getInformacoes(){
		listaPessoas.getItems().clear();
		listaCadeiras.getItems().clear();

		salaSelecionada.setVisible(false);
		blocoSelecionado.setVisible(false);
		
		turma = null;
		tipo = null;
		status = null;
		
																//verifica o tipo selecionado
		if(rbAluno.isSelected()){
			labelPessoas.setText("Selecione o aluno: ");
			labelCadeiras.setVisible(false);
			listaCadeiras.setVisible(false);
			getAlunos();										// vai mostrar todos os alunos
		}
		else{
			labelPessoas.setText("Selecione o professor: ");
			labelCadeiras.setText("Selecione a cadeira: ");
			labelCadeiras.setVisible(true);
			listaCadeiras.setVisible(true);
			getProfessores();									// vai mostrar todos os professores
		}
	}

	public void getAlunos(){
		Aluno aluno = new Aluno(nomeFiltro);
		Pesquisa pesquisa = new Pesquisa();

		Aluno[] listaAlunos = pesquisa.pesquisar(aluno);

		if(listaAlunos != null)
			for(int i =0; i<listaAlunos.length; i++){
				if(listaAlunos[i] == null)
					break;
				listaPessoas.getItems().addAll(listaAlunos[i].getNome());
			}

		Collections.sort(listaPessoas.getItems());
	}

	public void getProfessores(){
		Professor professor = new Professor(nomeFiltro);
		Pesquisa pesquisa = new Pesquisa();

		Professor[] listaProfessores = pesquisa.pesquisar(professor);


		if (listaProfessores != null)
			for (int i = 0; i < listaProfessores.length; i++) {
				if(listaProfessores[i] == null)
					break;
				listaPessoas.getItems().addAll(listaProfessores[i].getNome());
			}

		Collections.sort(listaPessoas.getItems());

	}

	public void escolherPessoa(){								// ao usuario selecionar o aluno/professor
		nome = listaPessoas.getValue();

		if(nome != null){
			if(rbProfessor.isSelected()){
				getCadeiras(nome);								// precisa selecionar a cadeira
				status = Status.AULA;
				
			}
			else{
				getMotivo(nome);								// pega o motivo da reserva
				status = Status.RESERVA;
				turma = "_";
				mostrarSala();
			}
		}


	}

	public void getCadeiras(String nome){					// recebe todas as cadeiras lecionadas pelo professor
		listaCadeiras.getItems().clear();

		Professor professor = new Professor(nome);

		Pesquisa pesquisa = new Pesquisa();

		Professor[] listaProfessores = pesquisa.pesquisar(professor);

		if (listaProfessores != null){
			for (int i =0; i< listaProfessores[0].getCadeira().length; i++){
				if (listaProfessores[0].getCadeira()[i] == null)
					break;
				listaCadeiras.getItems().addAll(listaProfessores[0].getCadeira()[i] + " - TURMA: " + listaProfessores[0].getTurma()[i]);
			}
		}
	}

	public void getMotivo(String nome){									// recebe o motivo da reserva
		Aluno aluno = new Aluno(nome);

		Pesquisa pesquisa = new Pesquisa();

		Aluno[] alunoEscolhido = pesquisa.pesquisar(aluno);

		tipo = alunoEscolhido[0].getMotivo();
	}
	
	public void escolherCadeira(){						//quando o usuario selecionar a cadeira
		String informacoes = listaCadeiras.getValue();
		String caracteres = "";
		
		if(informacoes != null){
			for (int i = 0; i<informacoes.length(); i++){
				caracteres += informacoes.charAt(i);
				if(informacoes.charAt(i) == '-'){
					caracteres = caracteres.replace("-", "");
					tipo = caracteres;
					caracteres = "";
				}
				if(informacoes.charAt(i) == ':'){
					caracteres = "";
				}
			}
			turma = caracteres.replace(" ", "");
		}
		
		mostrarSala();
	}
	
	public void mostrarSala(){								//apenas mostra a sala selecionada anteriormente
		salaSelecionada.setText(sala);
		blocoSelecionado.setText(bloco);
		salaSelecionada.setVisible(true);
		blocoSelecionado.setVisible(true);
	}

	public void voltarParaTabela(ActionEvent event){
		Tela telaMenu = new Tela();
		String caminho = "/br/com/poli/view/TabelaSalas.fxml";
		telaMenu.mostrarTela(event, caminho);
	}
}
