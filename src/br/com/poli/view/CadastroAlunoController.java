package br.com.poli.view;

import br.com.poli.pessoas.Aluno;
import br.com.poli.services.Cadastro;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroAlunoController {

	@FXML
	private TextField nomeAluno;
	@FXML
	private TextField cpfAluno;
	@FXML
	private TextField matriculaAluno;
	@FXML
	private TextField motivoReserva;
	@FXML
	private Label mensagem;
	@FXML
	private Button btnNovoCadastro;

	public void initialize(){
		btnNovoCadastro.setVisible(false);
	}



	public void voltarParaCadastro(ActionEvent event){
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/Cadastro.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}

	public void cadastrar(ActionEvent event){						//Metodo principal, verifica se as caixas de texto estao vazias, e se há caracteres invalidos.
		Aluno alunos = new Aluno("");								// tambem verifica o limite de alunos cadastrados
		Pesquisa pesquisa = new Pesquisa();
		if(pesquisa.pesquisar(alunos) != null && pesquisa.pesquisar(alunos).length == alunos.getTotalDeAlunos()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Quantidade máxima de alunos atingida!", "Por favor, exclua um aluno existente para cadastrar o novo aluno, ou entre em contato com o desenvolvidor do software.");
		}
		else if(nomeAluno.getText().trim().isEmpty() || cpfAluno.getText().trim().isEmpty() || 
				matriculaAluno.getText().trim().isEmpty() || motivoReserva.getText().trim().isEmpty()){					// caso haja, cria um alerta. (classe alerta)
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, preencha a(s) caixa(s) de texto.");
		}
		else if(Alerta.verificaCaractere(nomeAluno.getText()) || Alerta.verificaCaractere(cpfAluno.getText()) ||
				Alerta.verificaCaractere(matriculaAluno.getText()) || Alerta.verificaCaractere(motivoReserva.getText())){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
					+ " '('     ')'     ':'     '|'    ','  ");
		}
		else{
			Aluno novoAluno = new Aluno(nomeAluno.getText());		// caso esteja tudo certo, cadastra normalmente.
			Cadastro novoCadastro = new Cadastro();

			novoAluno.setCpf(cpfAluno.getText());
			novoAluno.setMatricula(matriculaAluno.getText());
			novoAluno.setMotivo(motivoReserva.getText());
			mensagem.setText(novoCadastro.cadastrar(novoAluno));
			btnNovoCadastro.setVisible(true);
		}
	}

	public void novoCadastro(ActionEvent event){					// recarrega a tela
		Tela telaCadastro = new Tela();
		String caminho = "/br/com/poli/view/CadastroAluno.fxml";
		telaCadastro.mostrarTela(event, caminho);
	}
}
