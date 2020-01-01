package br.com.poli.view;

import br.com.poli.services.Pesquisa;
import br.com.poli.tabela.DadosTabela;
import br.com.poli.tabela.Tabela;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class TabelaSalasController {
	@FXML
	private TableView <DadosTabela> tabela;
	@FXML
	private TableColumn <DadosTabela,String> colunaBloco;
	@FXML
	private TableColumn <DadosTabela,String> colunaSala;
	@FXML
	private TableColumn <DadosTabela,String> colunaTipo;
	@FXML
	private TableColumn <DadosTabela,String> colunaNome;
	@FXML
	private TableColumn <DadosTabela,String> colunaTurma;
	@FXML
	private TableColumn <DadosTabela,String> colunaStatus;
	@FXML
	private Label avisos;

	private Animation animacaoBarra;
	private Animation animacaoAviso;
	private int count = 0;

	private boolean rolagemAtivada = false;
	private boolean avisoAtivado = true;

	public void initialize(){
		colunaBloco.setCellValueFactory(new PropertyValueFactory<>("Bloco"));
		colunaSala.setCellValueFactory(new PropertyValueFactory<>("Sala"));
		colunaTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colunaTurma.setCellValueFactory(new PropertyValueFactory<>("Turma"));
		colunaStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
		Tabela.getSalas();

		if(getInformacoes() != null){									// caso as informacoes no arquivo nao estejam vazia, preenche a tabela
			tabela.setItems(getInformacoes());
		}

		avisos.setText("Bons Estudos! ");
	}

	public ObservableList <DadosTabela> getInformacoes(){		// metodo principal, toda vez será chamado ao inicializar
		ObservableList <DadosTabela> informacoes = FXCollections.observableArrayList();
		Tabela tabela = new Tabela();
		DadosTabela dados[] = tabela.getTabela();				// recebe um array de dados

		if(dados != null){
			for(int i =0; i<dados.length; i++){
				if(dados[i] == null){
					break;
				}
				informacoes.add(dados[i]);							// adiciona um por um o array de dados na lista informacoes
			}
		}
		else{
			informacoes = null;
		}
		return informacoes;
	}

	public DadosTabela selecionar(){								// Quando o usuario seleciona uma sala
		int valor = tabela.getSelectionModel().getSelectedIndex();
		if(valor>=0){
			DadosTabela dados = tabela.getItems().get(valor);
			return dados;
		}
		else{
			return null;
		}
	}

	public void liberar(){							// a sala volta a ficar disponivel
		DadosTabela dados = selecionar();
		if(dados!= null){
			Tabela.AtualizarSala(dados, true);		// true, pois a sala foi liberada
			tabela.getItems().clear();
			tabela.setItems(getInformacoes());
		}
		else{
			Alerta alerta = new Alerta();
			alerta.setAlerta("Nenhuma sala selecionada!", "Por favor, selecione uma sala");
		}
	}
	public void rolagemAutomatica(){					// faz a barra de rolagem mexer sozinha
		if(!rolagemAtivada){

			setRolagem();
			animacaoBarra.play();

			setAviso();
			rolagemAtivada = true;

		}
		else{
			animacaoBarra.stop();

			if(avisoAtivado){
				animacaoAviso.stop();
			}

			avisos.setText("Bons Estudos! ");
			rolagemAtivada = false;
			tabela.scrollTo(0);			
		}
	}

	public void setAviso(){
		Pesquisa pesquisa = new Pesquisa();
		String avisoAtual[] = pesquisa.pesquisarAvisos();
		if(avisoAtual.length!=0){
			animacaoAviso = new Timeline(new KeyFrame(Duration.seconds(3),new KeyValue(avisos.translateXProperty(),0)));
			animacaoAviso.setOnFinished(e -> mudarAviso());
			animacaoAviso.play();
		}
		else{
			avisoAtivado = false;
		}
	}

	public void mudarAviso(){
		Pesquisa pesquisa = new Pesquisa();
		String avisoAtual[] = pesquisa.pesquisarAvisos();

		if(avisoAtual.length!=0){
			if(count == avisoAtual.length-1){
				count = 0;
			}
			else{
				count++;
			}

			avisos.setText(avisoAtual[count]);
			setAviso();
		}
	}

	public void setRolagem(){				// define as configuracoes da barra de rolagem
		ScrollBar verticalBar = (ScrollBar) tabela.lookup(".scroll-bar:vertical");
		animacaoBarra = new Timeline(new KeyFrame(Duration.seconds(20),new KeyValue(verticalBar.valueProperty(), verticalBar.getMax())));
		animacaoBarra.setAutoReverse(true);
		animacaoBarra.setCycleCount(Animation.INDEFINITE);
	}

	public void irParaAlteracao(ActionEvent event){			// recebe a sala selecionada, e vai para a tela de alteracao
		int valor = tabela.getSelectionModel().getSelectedIndex();

		if(valor>=0){
			DadosTabela dados = tabela.getItems().get(valor);
			Tela telaMenu = new Tela();
			String caminho = "/br/com/poli/view/Alteracao.fxml";
			telaMenu.mostrarTela(event, caminho, dados.getSala(), dados.getBloco());
		}
		else{
			Alerta alerta = new Alerta();
			alerta.setAlerta("Nenhuma sala selecionada!", "Por favor, selecione uma sala");
		}
	}

	public void voltarParaMenu(ActionEvent event){
		Tela telaMenu = new Tela();
		String caminho = "/br/com/poli/view/Menu.fxml";
		telaMenu.mostrarTela(event, caminho);
	}

}
