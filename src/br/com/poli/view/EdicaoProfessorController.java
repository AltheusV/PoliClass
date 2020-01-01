package br.com.poli.view;

import java.util.Collections;

import br.com.poli.pessoas.Professor;
import br.com.poli.services.Edicao;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EdicaoProfessorController {

	@FXML
	private ComboBox <String> professores;
	@FXML
	private TextField textoFiltro;
	@FXML
	private TextField nomeProfessor;
	@FXML
	private TextField turma1; @FXML private TextField turma2; @FXML private TextField turma3; @FXML private TextField turma4; @FXML private TextField turma5;
	@FXML								
	private TextField cadeira1;	@FXML private TextField cadeira2; @FXML private TextField cadeira3; @FXML private TextField cadeira4; @FXML private TextField cadeira5;
	@FXML
	private TextField codigo1; @FXML private TextField codigo2; @FXML  private TextField codigo3; @FXML private TextField codigo4; @FXML private TextField codigo5;	
	@FXML
	private ComboBox <String> quantidadeDeCadeiras;

	private TextField[] cadeiras = new TextField[5];				//quantidade maxima de cadeiras para um professor
	private TextField[] turmas = new TextField[5];
	private TextField[] codigos = new TextField[5];

	private String nomeFiltro = "";
	private boolean deletar;

	public void initialize(){					//ao iniciar, repassa cada textField para um array de textFields, para facilitar decorrer do codigo.
		cadeiras[0] = cadeira1; 	turmas[0] = turma1;		codigos[0] = codigo1;
		cadeiras[1] = cadeira2;		turmas[1] = turma2;		codigos[1] = codigo2;
		cadeiras[2] = cadeira3;		turmas[2] = turma3;		codigos[2] = codigo3;
		cadeiras[3] = cadeira4;		turmas[3] = turma4;		codigos[3] = codigo4;
		cadeiras[4] = cadeira5;		turmas[4] = turma5;		codigos[4] = codigo5;

		for (int i=0; i<5; i++){
			cadeiras[i].setVisible(false);
			turmas[i].setVisible(false);
			codigos[i].setVisible(false);
			quantidadeDeCadeiras.getItems().addAll(Integer.toString(i+1));
		}
		getProfessores();
	}

	public void selecionarQuantidade(){
		int valor = Integer.parseInt(quantidadeDeCadeiras.getValue());
		limpar();
		for (int i =0; i<valor; i++){			// Só fica visivel  até a quantidade selecionada
			cadeiras[i].setVisible(true);
			turmas[i].setVisible(true);
			codigos[i].setVisible(true);
		}
	}

	public void limpar(){						// esse metodo apenas retira da tela os textFields
		for (int i =0; i<5; i++){				// deixa invisivel*
			cadeiras[i].setVisible(false);
			turmas[i].setVisible(false);
			codigos[i].setVisible(false);
		}
	}


	public void filtrar(){
		nomeFiltro = textoFiltro.getText();
		nomeProfessor.clear();
		limpar();
		getProfessores();
	}

	public void getProfessores(){
		professores.getItems().clear();
		Professor professor = new Professor(nomeFiltro);
		Pesquisa pesquisa = new Pesquisa();

		Professor[] listaProfessores = pesquisa.pesquisar(professor);

		if(listaProfessores != null)
			for(int i =0; i<listaProfessores.length; i++){
				if(listaProfessores[i] == null)
					break;
				professores.getItems().addAll(listaProfessores[i].getNome());
			}

		Collections.sort(professores.getItems());
	}

	public void escolherProfessor(){

		for (int i =0; i<5; i++){				
			cadeiras[i].clear();;
			turmas[i].clear();;
			codigos[i].clear();;
		}

		if(professores.getValue()!=null){
			Professor professor = new Professor(professores.getValue());
			Pesquisa pesquisa = new Pesquisa();

			Professor[] professorEscolhido = pesquisa.pesquisar(professor);

			if (professorEscolhido != null){
				limpar();
				nomeProfessor.setText(professorEscolhido[0].getNome());
				for (int i =0; i< professorEscolhido[0].getCadeira().length; i++){
					if (professorEscolhido[0].getCadeira()[i] == null)
						break;
					turmas[i].setText(professorEscolhido[0].getTurma()[i]);
					cadeiras[i].setText(professorEscolhido[0].getCadeira()[i]);
					codigos[i].setText(professorEscolhido[0].getCodigo()[i]);
				}
			}
		}
	}

	public boolean verificacao(){

		if(professores.getValue() == null){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados inválidos!", "Por favor preencha todas as informacoes corretamente!");
			return false;
		}
		else if(nomeProfessor.getText().trim().isEmpty() || quantidadeDeCadeiras.getValue()==null){	// caso haja, cria um alerta. (classe alerta)
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, preencha os dados corretamente.");
			return false;
		}

		else if (Alerta.verificaCaractere(nomeProfessor.getText())){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
					+ " '('     ')'     ':'     '|'    ','  ");
			return false;
		}
		else
		{
			for(int i=0; i<Integer.parseInt(quantidadeDeCadeiras.getValue()); i++){
				if(cadeiras[i].getText().trim().isEmpty() || turmas[i].getText().trim().isEmpty() ||
						codigos[i].getText().trim().isEmpty()){
					Alerta alerta = new Alerta();
					alerta.setAlerta("Dados incorretos!", "Por favor, preencha todas as informacoes referentes a(s) cadeira(s).");
					return false;
				}
			}
			for(int i=0; i<Integer.parseInt(quantidadeDeCadeiras.getValue()); i++){
				if(Alerta.verificaCaractere(cadeiras[i].getText()) || Alerta.verificaCaractere(turmas[i].getText()) ||
						Alerta.verificaCaractere(codigos[i].getText())){
					Alerta alerta = new Alerta();
					alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
							+ " '('     ')'     ':'     '|'    ','  ");
					return false;
				}
			}
		}
		return true;			// caso esteja tudo certo, retorna true
	}

	public void alterarInformacoes(boolean delete){
		if (verificacao()){
			Professor professor = new Professor(nomeProfessor.getText());

			String[] novaCadeira = new String[Integer.parseInt(quantidadeDeCadeiras.getValue())];
			String[] novaTurma = new String[Integer.parseInt(quantidadeDeCadeiras.getValue())];
			String[] novoCodigo = new String[Integer.parseInt(quantidadeDeCadeiras.getValue())];

			for(int i=0; i<Integer.parseInt(quantidadeDeCadeiras.getValue()); i++){
				novaCadeira[i] = cadeiras[i].getText();
				novaTurma[i] = turmas[i].getText();
				novoCodigo[i] = codigos[i].getText();
			}

			professor.setCadeira(novaCadeira);
			professor.setTurma(novaTurma);
			professor.setCodigo(novoCodigo);

			Edicao edicao = new Edicao();
			edicao.editar(professores.getValue(), professor, delete);
			
			limpar();
			nomeProfessor.clear();
			nomeFiltro = "";
			textoFiltro.clear();
			getProfessores();

		}
	}

	public void editar(){
		alterarInformacoes(false);
	}

	public void deletar(){
		if(!deletar){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Aviso! ", "Todas as informacoes serão perdidas. Clique novamente em deletar, para confirmar.");
			deletar = true;
		}
		else{
			quantidadeDeCadeiras.setValue("1");
			alterarInformacoes(deletar);
			deletar = false;
		}
	}

	public void voltarParaEdicao(ActionEvent event){
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/Edicao.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}
}
