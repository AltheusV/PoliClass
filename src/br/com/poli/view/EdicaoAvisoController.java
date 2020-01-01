package br.com.poli.view;

import br.com.poli.services.Edicao;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EdicaoAvisoController {
	@FXML
	private ListView <String> resultadoPesquisa;
	@FXML
	private TextField novoAviso;
	
	private int valor = -1;
	private String[] listaAvisos;
	private boolean deletar;
	

	public void initialize(){
		mostrarAvisos();
	}

	public void mostrarAvisos(){
		resultadoPesquisa.getItems().clear();
		Pesquisa pesquisa = new Pesquisa();
		listaAvisos = pesquisa.pesquisarAvisos();

		if(listaAvisos!= null){
			for (int i=0; i<listaAvisos.length; i++){
				if(listaAvisos[i]!=null){
					resultadoPesquisa.getItems().addAll(listaAvisos[i]);
				}
			}
		}

	}

	public void selecionarAviso(MouseEvent event){			
		valor = resultadoPesquisa.getSelectionModel().getSelectedIndex();
		novoAviso.setText(resultadoPesquisa.getSelectionModel().getSelectedItem());
	}

	
	public void alterarInformacoes(boolean delete){
		if(novoAviso.getText().trim().isEmpty()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, preencha a(s) caixa(s) de texto.");
		}
		else if (Alerta.verificaCaractere(novoAviso.getText())){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
					+ " '('     ')'     ':'     '|'    ','  ");
		}
		else if (valor < 0){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, selecione o aviso");
		}
		else{
			Edicao edicao = new Edicao();
			edicao.editar(resultadoPesquisa.getSelectionModel().getSelectedItem(), novoAviso.getText(), delete);
			novoAviso.clear();
			mostrarAvisos();
			
		}
	}
	
	public void editar(){
		alterarInformacoes(false);
	}

	public void deletar(){
		if(!deletar){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Aviso! ", "O aviso selecionado será deletado. Clique novamente em deletar, para confirmar.");
			deletar = true;
		}
		else{
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
