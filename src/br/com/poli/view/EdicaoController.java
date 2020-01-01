package br.com.poli.view;

import br.com.poli.repositorio.Repositorio;
import javafx.event.ActionEvent;

public class EdicaoController {
	private boolean confirmar = false;
	
	public void resetar(){
		if(!confirmar){
			Alerta alerta = new Alerta();
			alerta.setAlerta("ATENCAO! ", "Os alunos, professores e avisos cadastrados serão apagados. Clique novamente em resetar para confirmar, ou em voltar para cancelar.");
			confirmar = true;
		}else{
			Repositorio repositorio = new Repositorio();
			repositorio.limparAlunos();
			repositorio.limparAviso();
			repositorio.limparCadeiras();
			repositorio.limparTabela();
			
			Alerta alerta = new Alerta();
			alerta.setAlerta("ATENCAO! ", "Os alunos, professores e avisos cadastrados foram deletados.");
			
			confirmar = false;
		}
	}
	
	
	public void	mudarParaEdicaoAluno(ActionEvent event){				//Funciona parecido com o menu
		Tela telaCadastroAluno = new Tela();							// apenas redireciona para o tipo
		String caminho = "/br/com/poli/view/EdicaoAluno.fxml";		// de edicao.
		telaCadastroAluno.mostrarTela(event, caminho);
	}
	
	public void	mudarParaEdicaoAviso(ActionEvent event){				//Funciona parecido com o menu
		Tela telaCadastroAviso = new Tela();							// apenas redireciona para o tipo
		String caminho = "/br/com/poli/view/EdicaoAviso.fxml";		// de edicao.
		telaCadastroAviso.mostrarTela(event, caminho);
	}
	
	public void	mudarParaEdicaoProfessor(ActionEvent event){
		Tela telaCadastroProfessor = new Tela();
		String caminho = "/br/com/poli/view/EdicaoProfessor.fxml";
		telaCadastroProfessor.mostrarTela(event, caminho);
	}
	
	public void	mudarParaEdicaoSala(ActionEvent event){
		Tela telaCadastroSala = new Tela();
		String caminho = "/br/com/poli/view/EdicaoSala.fxml";
		telaCadastroSala.mostrarTela(event, caminho);
	}
	
	public void voltarParaMenu(ActionEvent event){
		Tela telaMenu = new Tela();
		String caminho = "/br/com/poli/view/Menu.fxml";
		telaMenu.mostrarTela(event, caminho);
	}
}	
