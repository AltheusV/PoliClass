package br.com.poli.view;

import br.com.poli.salas.Salas;
import br.com.poli.services.Cadastro;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroSalaController {
	@FXML
	private TextField nomeSala;
	@FXML
	private TextField capacidade;
	@FXML
	private ChoiceBox <String> bloco;
	@FXML
	private CheckBox projetor;
	@FXML
	private CheckBox arCondicionado;
	@FXML
	private Label mensagem;
	@FXML
	private Button btnNovoCadastro;

	public void initialize(){						// ao iniciar, faz uma contagem de blocos
		for (char count = 65; count<76; count++){	// vai do A até K
			bloco.getItems().addAll(String.valueOf(count));			
		}
		btnNovoCadastro.setVisible(false);
	}


	public void voltarParaCadastro(ActionEvent event){
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/Cadastro.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}

	public void cadastrar(){			// metodo principal, verifica se há algum erro, caso haja cria alerta
		Salas salas = new Salas("");					// tambem verifica o limite de salas cadastradas
		Pesquisa pesquisa = new Pesquisa();

		if(pesquisa.pesquisar(salas) != null && pesquisa.pesquisar(salas).length == salas.getTotalDeSalas()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Quantidade máxima de Salas atingida! (50)", "Por favor, exclua uma sala existente para cadastrar a nova sala, ou entre em contato com o desenvolvidor do software.");
		}

		else if(nomeSala.getText().trim().isEmpty() || capacidade.getText().trim().isEmpty() || bloco.getValue() == null){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, preencha todos os dados corretamente.");
		}
		else if(Alerta.verificaCaractere(nomeSala.getText()) || Alerta.verificaCaractere(capacidade.getText())){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
					+ " '('     ')'     ':'     '|'    ','  ");
		}
		else{							// caso nao haja, segue normalmente
			Salas novaSala = new Salas(nomeSala.getText());
			Cadastro novoCadastro = new Cadastro();

			novaSala.setBloco(bloco.getValue().charAt(0));
			novaSala.setCapacidade(capacidade.getText());
			novaSala.setProjetor(projetor.isSelected());
			novaSala.setArCondicionado(arCondicionado.isSelected());
			mensagem.setText(novoCadastro.cadastrar(novaSala));
			btnNovoCadastro.setVisible(true);
		}
	}

	public void novoCadastro(ActionEvent event){
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/CadastroSala.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}
}
