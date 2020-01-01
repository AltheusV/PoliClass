package br.com.poli.view;

import java.util.Collections;

import br.com.poli.salas.Salas;
import br.com.poli.services.Edicao;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EdicaoSalaController {
	@FXML
	private ComboBox <String> salas;
	@FXML
	private ChoiceBox <String> bloco;
	@FXML
	private CheckBox projetor;
	@FXML
	private CheckBox arCondicionado;
	@FXML
	private TextField textoFiltro;
	@FXML
	private TextField nomeSala;
	@FXML
	private TextField capacidade;

	private String nomeFiltro = "";
	private boolean deletar;


	public void initialize(){									// ao iniciar, faz uma contagem de blocos
		getSalas();												// vai do A até K
		for (char count = 65; count<76; count++){	
			bloco.getItems().addAll(String.valueOf(count));			
		}
	}
	public void filtrar(){
		nomeFiltro = textoFiltro.getText();
		getSalas();
	}


	public void limpar(){
		nomeSala.clear();
		capacidade.clear();
		projetor.setSelected(false);
		arCondicionado.setSelected(false);
		bloco.getItems().clear();

		for (char count = 65; count<76; count++){	
			bloco.getItems().addAll(String.valueOf(count));			
		}

		nomeFiltro = "";
		textoFiltro.clear();
		getSalas();
	}

	public void getSalas(){
		salas.getItems().clear();
		Salas sala = new Salas(nomeFiltro);
		Pesquisa pesquisa = new Pesquisa();

		Salas[] listaSalas = pesquisa.pesquisar(sala);

		if(listaSalas != null)
			for(int i =0; i<listaSalas.length; i++){
				if(listaSalas[i] == null)
					break;
				salas.getItems().addAll(listaSalas[i].getSala());
			}

		Collections.sort(salas.getItems());
	}

	public void escolherSala(){
		if(salas.getValue()!= null){
			Salas sala = new Salas(salas.getValue());
			Pesquisa pesquisa = new Pesquisa();

			Salas[] salaEscolhida = pesquisa.pesquisar(sala);

			nomeSala.setText(salaEscolhida[0].getSala().replace("  ", ""));
			capacidade.setText(salaEscolhida[0].getCapacidade());
			projetor.setSelected(salaEscolhida[0].isProjetor());
			arCondicionado.setSelected(salaEscolhida[0].isArCondicionado());
		}
	}

	public void alterarInformacoes(boolean delete){		// metodo principal
		if(salas.getValue() == null){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados inválidos!", "Por favor preencha todas as informacoes corretamente!");
		}		
		else if(bloco.getValue() == null || nomeSala.getText().trim().isEmpty() || capacidade.getText().trim().isEmpty()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, preencha todos os dados corretamente.");
		}
		else if(Alerta.verificaCaractere(nomeSala.getText()) || Alerta.verificaCaractere(capacidade.getText())){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
					+ " '('     ')'     ':'     '|'    ','  ");
		}
		else{
			Salas sala = new Salas(nomeSala.getText());
			sala.setBloco(bloco.getValue().charAt(0));
			sala.setCapacidade(capacidade.getText());
			sala.setProjetor(projetor.isSelected());
			sala.setArCondicionado(arCondicionado.isSelected());

			Edicao edicao = new Edicao();
			edicao.editar(salas.getValue(), sala, delete);
			edicao.editarTabela(salas.getValue(), sala, delete);
			
			limpar();
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
			bloco.setValue("A");
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
