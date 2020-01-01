package br.com.poli.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MenuController {									//O menu apenas irá redirecionar 
																// para outras telas
	@FXML														//Dependendo do botao selecionado, o caminho muda
	public void mudarParaCadastro(ActionEvent event){			//invoca o metodo mostrarTela (Classe tela), com o caminho referente a tela
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/Cadastro.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}
	
	@FXML
	public void mudarParaEdicao(ActionEvent event){
		Tela telaPesquisa = new Tela();
		String caminho = "/br/com/poli/view/Edicao.fxml";
		telaPesquisa.mostrarTela(event, caminho);
	}
	
	@FXML
	public void mudarParaPesquisa(ActionEvent event){
		Tela telaPesquisa = new Tela();
		String caminho = "/br/com/poli/view/Pesquisa.fxml";
		telaPesquisa.mostrarTela(event, caminho);
	}
	
	@FXML
	public void mudarParaTabelaSalas(ActionEvent event){
		Tela telaPesquisa = new Tela();
		String caminho = "/br/com/poli/view/TabelaSalas.fxml";
		telaPesquisa.mostrarTela(event, caminho);
	}
}
