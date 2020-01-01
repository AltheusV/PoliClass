package br.com.poli.view;


import br.com.poli.services.Cadastro;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroAvisoController {
	
	
	@FXML
	private Label mensagem;
	@FXML
	private TextField aviso;
	@FXML
	private Button btnNovoAviso;
	

	public void initialize(){
		btnNovoAviso.setVisible(false);
	}
	
	
	public void cadastrar(ActionEvent event){
		Pesquisa pesquisa = new Pesquisa();
		
		if(aviso.getText().trim().isEmpty()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, preencha a(s) caixa(s) de texto.");
		}
		else if(Alerta.verificaCaractere(aviso.getText())){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
					+ " '('     ')'     ':'     '|'    ','  ");
		}
		else if(pesquisa.pesquisarAvisos().length==pesquisa.getTotalAvisos()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Quantidade máxima de avisos atingida!", "Por favor, exclua ou edite um aviso existente. Para mais informacoes, entre em contato com o desenvolvidor do software.");
		}
		else{													// caso esteja tudo certo, cadastra normalmente.
			Cadastro novoCadastro = new Cadastro();
			mensagem.setText(novoCadastro.cadastrar(aviso.getText()));
			btnNovoAviso.setVisible(true);
		}
	}
	

	public void voltarParaCadastro(ActionEvent event){
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/Cadastro.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}
	
	public void novoAviso(ActionEvent event){					// recarrega a tela
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/CadastroAviso.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}
}
