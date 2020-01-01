package br.com.poli.view;

import java.util.Collections;

import br.com.poli.pessoas.Aluno;
import br.com.poli.services.Edicao;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EdicaoAlunoController {

	@FXML
	private ComboBox <String> alunos;
	@FXML
	private TextField textoFiltro;
	@FXML
	private TextField nomeAluno;
	@FXML
	private TextField cpfAluno;
	@FXML
	private TextField matriculaAluno;
	@FXML
	private TextField motivoReserva;

	private String nomeFiltro = "";
	private boolean deletar;

	public void initialize(){
		getAlunos();
	}

	public void filtrar(){
		nomeFiltro = textoFiltro.getText();
		limpar();
		getAlunos();
	}
	
	public void limpar(){
		nomeAluno.clear();
		cpfAluno.clear();
		matriculaAluno.clear();
		motivoReserva.clear();
	}

	public void getAlunos(){
		alunos.getItems().clear();
		Aluno aluno = new Aluno(nomeFiltro);
		Pesquisa pesquisa = new Pesquisa();

		Aluno[] listaAlunos = pesquisa.pesquisar(aluno);

		if(listaAlunos != null)
			for(int i =0; i<listaAlunos.length; i++){
				if(listaAlunos[i] == null)
					break;
				alunos.getItems().addAll(listaAlunos[i].getNome());
			}

		Collections.sort(alunos.getItems());
	}

	public void escolherAluno(){
		if(alunos.getValue()!= null){
			Aluno aluno = new Aluno(alunos.getValue());
			Pesquisa pesquisa = new Pesquisa();

			Aluno[] alunoEscolhido = pesquisa.pesquisar(aluno);

			nomeAluno.setText(alunoEscolhido[0].getNome());
			cpfAluno.setText(alunoEscolhido[0].getCpf());
			matriculaAluno.setText(alunoEscolhido[0].getMatricula());
			motivoReserva.setText(alunoEscolhido[0].getMotivo());
		}
	}

	public void alterarInformacoes(boolean delete){			// metodo principal
		if(alunos.getValue() == null){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados inválidos!", "Por favor preencha todas as informacoes corretamente!");
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
			
			Aluno aluno = new Aluno(nomeAluno.getText());
			aluno.setCpf(cpfAluno.getText());
			aluno.setMatricula(matriculaAluno.getText());
			aluno.setMotivo(motivoReserva.getText());

			Edicao edicao = new Edicao();
			edicao.editar(alunos.getValue(), aluno, delete);
			limpar();
			nomeFiltro = "";
			textoFiltro.clear();
			getAlunos();
			
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
