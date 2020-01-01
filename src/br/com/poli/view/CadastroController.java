package br.com.poli.view;

import javafx.event.ActionEvent;

public class CadastroController {
	
		
	public void	mudarParaCadastroAluno(ActionEvent event){				//Funciona parecido com o menu
		Tela telaCadastroAluno = new Tela();							// apenas redireciona para o tipo
		String caminho = "/br/com/poli/view/CadastroAluno.fxml";		// de cadastro.
		telaCadastroAluno.mostrarTela(event, caminho);
	}
	
	public void	mudarParaCadastroAviso(ActionEvent event){				//Funciona parecido com o menu
		Tela telaCadastroAluno = new Tela();							// apenas redireciona para o tipo
		String caminho = "/br/com/poli/view/CadastroAviso.fxml";		// de cadastro.
		telaCadastroAluno.mostrarTela(event, caminho);
	}
	
	public void	mudarParaCadastroProfessor(ActionEvent event){
		Tela telaCadastroProfessor = new Tela();
		String caminho = "/br/com/poli/view/CadastroProfessor.fxml";
		telaCadastroProfessor.mostrarTela(event, caminho);
	}
	
	public void	mudarParaCadastroSala(ActionEvent event){
		Tela telaCadastroSala = new Tela();
		String caminho = "/br/com/poli/view/CadastroSala.fxml";
		telaCadastroSala.mostrarTela(event, caminho);
	}
	
	public void voltarParaMenu(ActionEvent event){
		Tela telaMenu = new Tela();
		String caminho = "/br/com/poli/view/Menu.fxml";
		telaMenu.mostrarTela(event, caminho);
	}
	
}
