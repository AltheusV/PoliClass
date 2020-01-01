package br.com.poli.view;

import br.com.poli.pessoas.Aluno;
import br.com.poli.pessoas.Professor;
import br.com.poli.salas.Salas;
import br.com.poli.services.Pesquisa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class PesquisaController {

	@FXML
	private RadioButton rbAluno;
	@FXML
	private RadioButton rbProfessor;
	@FXML
	private RadioButton rbSala;
	@FXML
	private TextField textoPesquisa;
	@FXML
	private ListView <String> resultadoPesquisa;
	@FXML
	private TextField textoInformacoes;

	private int tipoPesquisa;

	private Aluno[] listaAlunos;
	private Professor[] listaProfessores;
	private Salas[] listaSalas;

	public void initialize(){
		resultadoPesquisa.setVisible(false);
		textoInformacoes.setVisible(false);
	}
	// metodo principal, retorna a pesquisa
	public void pesquisar(){			// verifica se a caixa de texto ta vazia, e se há um caractere invalido
		if(textoPesquisa.getText().trim().isEmpty()){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Dados incorretos!", "Por favor, preencha a caixa de texto.");
		}
		else if(Alerta.verificaCaractere(textoPesquisa.getText())){
			Alerta alerta = new Alerta();
			alerta.setAlerta("Caractere inválido!", "Os seguintes caracteres são inválidos: \n '-'   "
					+ " '('     ')'     ':'     '|'    ','  ");
		}
		else{															// caso nao haja, segue normalmente
			resultadoPesquisa.getItems().clear();
			textoInformacoes.clear();
																		// verifica qual botao foi selecionado
			if(rbAluno.isSelected()){					
				Aluno aluno = new Aluno(textoPesquisa.getText());		// Caso aluno, pesquisa alunos
				Pesquisa pesquisa = new Pesquisa();
				listaAlunos = pesquisa.pesquisar(aluno);

				if(listaAlunos != null){
					for (int i=0; i<listaAlunos.length; i++){
						resultadoPesquisa.getItems().addAll(listaAlunos[i].getNome());
					}
				}
				tipoPesquisa = 1;
			}
			if(rbProfessor.isSelected()){								// Caso professor (cadeiras), pesquisa Cadeiras
				Pesquisa pesquisa = new Pesquisa();
				listaProfessores = pesquisa.pesquisar(textoPesquisa.getText());

				textoPesquisa.setText(textoPesquisa.getText().toUpperCase());

				if (listaProfessores != null){
					for (int i=0; i<listaProfessores.length; i++){
						for(int j=0; j<listaProfessores[i].getCadeira().length; j++){
							if (listaProfessores[i].getCadeira()[j] != null && 
									listaProfessores[i].getCadeira()[j].contains(textoPesquisa.getText()) ){

								resultadoPesquisa.getItems().addAll(listaProfessores[i].getCadeira()[j] + 
										"   |  Turma: " +
										listaProfessores[i].getTurma()[j]);
							}
						}
					}
				}
				tipoPesquisa = 2;

			}
			if(rbSala.isSelected()){									// caso seja sala, pesquisa salas
				Salas sala = new Salas(textoPesquisa.getText());
				Pesquisa pesquisa = new Pesquisa();
				listaSalas = pesquisa.pesquisar(sala);

				if (listaSalas != null){
					for(int i=0; i<listaSalas.length; i++){
						resultadoPesquisa.getItems().addAll(listaSalas[i].getSala());
					}
				}
				tipoPesquisa = 3;
			}
			resultadoPesquisa.setVisible(true);
			textoInformacoes.setVisible(true);
		}
	}

	public void mostrarInformacoes(MouseEvent event){			// O metodo é acionado ao clicar em um retorno da pesquisa
		int valor = resultadoPesquisa.getSelectionModel().getSelectedIndex();

		if(valor >= 0){										
																// define pelo int tipo pesquisa, que é setado no metodo pesquisar()
			if(tipoPesquisa==1){
				textoInformacoes.setText("Reserva: " + listaAlunos[valor].getMotivo()	//aluno selecionado
						+ "     |    " + " CPF: " + listaAlunos[valor].getCpf());
			}
			else if(tipoPesquisa==2){
				String cadeira = resultadoPesquisa.getSelectionModel().getSelectedItem();
				cadeira = cadeira.replace("   |  Turma: ", ":");						// cadeira selecionada
				String checar = "";
																				
				if(listaProfessores != null){											// verifica qual o professor referente a esta cadeira
					for(int i=0; i<listaProfessores.length; i++){
						for(int j=0; j<listaProfessores[i].getCadeira().length; j++){
							if(listaProfessores[i].getCadeira()[j]!=null){
								checar = listaProfessores[i].getCadeira()[j] + ":" +
										listaProfessores[i].getTurma()[j];
								if(checar.equals(cadeira)){
									textoInformacoes.setText("Professor: " + listaProfessores[i].getNome());	
								}
							}
						}
					}
				}
			}
			else if(tipoPesquisa==3){							// sala selecionada
				String ar;
				String projetor;

				if (listaSalas[valor].isArCondicionado()){
					ar = "Possui ar condicionado";
				}
				else{
					ar = "Não possui ar condicionado";
				}
				if (listaSalas[valor].isProjetor()){
					projetor = "Possui projetor";
				}
				else{
					projetor = "Não possui projetor";
				}

				textoInformacoes.setText("Bloco: " + listaSalas[valor].getBloco() + "  |  " + " Capacidade: " + listaSalas[valor].getCapacidade()
						+ "  |  " + ar + "  |  " + projetor);
			}
			else{
				textoInformacoes.setText("");
			}

		}
	}

	public void voltarParaMenu(ActionEvent event){
		Tela telaMenu = new Tela();
		String caminho = "/br/com/poli/view/Menu.fxml";
		telaMenu.mostrarTela(event, caminho);
	}
}
