package br.com.poli.view;

import br.com.poli.pessoas.Professor;
import br.com.poli.services.Cadastro;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroProfessorController {

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
	@FXML
	private Label mensagem;
	@FXML
	private Button btnNovoCadastro;

	private TextField[] cadeiras = new TextField[5];				//quantidade maxima de cadeiras para um professor
	private TextField[] turmas = new TextField[5];
	private TextField[] codigos = new TextField[5];


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
		mensagem.setVisible(false);
		btnNovoCadastro.setVisible(false);
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

	public boolean verificacao(){				// verifica se as caixas de texto estao vazias, e se há caracteres invalidos. 
		Professor professores = new Professor("");					// tambem verifica o limite de professores cadastrados
		Pesquisa pesquisa = new Pesquisa();

		if(pesquisa.pesquisar(professores) != null && pesquisa.pesquisar(professores).length == professores.getTotalDeProfessores()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Quantidade máxima de professores atingida! (200)", "Por favor, exclua um professor existente para cadastrar o novo professor, ou entre em contato com o desenvolvidor do software.");
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

	public void cadastrar(){	// metodo principal, só faz se verificacao() retornar true
		if(verificacao()){
			Professor novoProfessor = new Professor(nomeProfessor.getText());
			Cadastro novoCadastro = new Cadastro();
			String[] novaCadeira = new String[Integer.parseInt(quantidadeDeCadeiras.getValue())];
			String[] novaTurma = new String[Integer.parseInt(quantidadeDeCadeiras.getValue())];
			String[] novoCodigo = new String[Integer.parseInt(quantidadeDeCadeiras.getValue())];

			for(int i=0; i<Integer.parseInt(quantidadeDeCadeiras.getValue()); i++){
				novaCadeira[i] = cadeiras[i].getText();
				novaTurma[i] = turmas[i].getText();
				novoCodigo[i] = codigos[i].getText();
			}

			novoProfessor.setCadeira(novaCadeira);
			novoProfessor.setTurma(novaTurma);
			novoProfessor.setCodigo(novoCodigo);
			limpar();
			mensagem.setText(novoCadastro.cadastrar(novoProfessor));
			mensagem.setVisible(true);
			btnNovoCadastro.setVisible(true);
		}
	}


	public void voltarParaCadastro(ActionEvent event){
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/Cadastro.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}

	public void novoCadastro(ActionEvent event){
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/CadastroProfessor.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}
}
