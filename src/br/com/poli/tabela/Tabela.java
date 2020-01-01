package br.com.poli.tabela;


import br.com.poli.repositorio.Repositorio;
import br.com.poli.salas.Salas;
import br.com.poli.services.Pesquisa;

public class Tabela {

	public static void AtualizarSala(DadosTabela dados, boolean liberar){
		Repositorio repositorio = new Repositorio();
		String listaSalas = repositorio.visualizarTabela();		// repassa o arquivo para uma string

		repositorio.limparTabela();				// apaga o arquivo

		Salas sala = new Salas(""); 
		String salaComparada = "";

		String caracteres = "";
		String listaAtualizada = "";


		for(int i=0; i<listaSalas.length(); i++){
			caracteres += listaSalas.charAt(i);
			salaComparada += listaSalas.charAt(i);

			if(listaSalas.charAt(i) == '('){
				salaComparada = salaComparada.replace("-", "");
				salaComparada = salaComparada.replace("(", "");
				salaComparada = salaComparada.replace("  ", "");
				sala.setSala(salaComparada);		
			}

			if(listaSalas.charAt(i)==')'){
				if(sala.getSala().equals(dados.getSala())){					// caso a sala selecionada for a mesma no arquivo
					caracteres = dados.getSala() + " - (" + dados.getBloco();
					if(liberar){														// caso true, a sala foi liberada
						caracteres += "|_:_,_." + Status.DISPONIVEL.name() + ")";
					}else{																// caso false, a sala vai ser utilizada
						caracteres += "|" + dados.getTipo() + ":" + dados.getTurma() + "," + dados.getNome()
						+ "." + dados.getStatus() + ")";
						caracteres = caracteres.replace("  ", "");
						if(caracteres.contains(" :")){
							caracteres = caracteres.replace(" :", ":");
						}
					}
				}
				listaAtualizada=caracteres;
				repositorio.armazenarTabela(listaAtualizada);			//atualiza arquivo
			}
			if(listaSalas.charAt(i) == '\n'){
				caracteres = "";
				salaComparada = "";
			}
		}
	}

	public static void getSalas(){								//metodo repassa as salas cadastradas no arquivo salas, para o arquivo da tabela
		String informacoes;

		Salas sala = new Salas("");
		Pesquisa pesquisa = new Pesquisa();
		Repositorio repositorio = new Repositorio();
		Salas[] listaSalas = pesquisa.pesquisar(sala);

		if (repositorio.visualizarTabela().contains("ERRO3")){			//Cria o arquivo da tabela pela primeira vez
																		//caso nao exista e caso haja um arquivo de salas.
			if(listaSalas != null){
				for (int i=0 ; i < listaSalas.length ; i++){				// preenche com as salas existentes
					if (listaSalas[i] != null){
						listaSalas[i].setSala(listaSalas[i].getSala().replace("  ", ""));
						informacoes = listaSalas[i].getSala() + " - (" + listaSalas[i].getBloco();
						informacoes += "|_:_,_." + Status.DISPONIVEL.name() + ")";
						repositorio.armazenarTabela(informacoes);
					}
				}
			}
		}
		else{
			String tabela = repositorio.visualizarTabela();			//Caso já exista, irá verificar
			String salaComparada = "";								// se foi adicionado salas novas
			int valor = 0;
			int quantidadeSalasNaLista = 0;

			for(int i=0; i<listaSalas.length; i++){
				if(listaSalas[i] != null){
					valor = i;			
				}
			}

			for(int i=0; i<tabela.length(); i++){
				salaComparada += tabela.charAt(i);

				if(tabela.charAt(i) == '('){
					salaComparada = salaComparada.replace("-", "");
					salaComparada = salaComparada.replace("(", "");
					sala.setSala(salaComparada);						//define a ultima sala da tabela
					quantidadeSalasNaLista++;							// define qtd de salas do arquivo
																		// existente na tabela
				}

				if(tabela.charAt(i) == '\n'){
					salaComparada = "";
				}		
			}
			if(!sala.getSala().equals(listaSalas[valor].getSala())){		//se a ultima sala do arquivo de salas for diferente da ultima sala do arquivo da tabela, ou seja, criou-se uma nova sala
				for(; quantidadeSalasNaLista<valor+1; quantidadeSalasNaLista++){
					if(listaSalas[quantidadeSalasNaLista]!=null){			// adiciona essa nova sala no arquivo da tabela
						informacoes = listaSalas[quantidadeSalasNaLista].getSala() + " - (" + listaSalas[quantidadeSalasNaLista].getBloco();
						informacoes += "|_:_,_." + Status.DISPONIVEL.name() + ")";
						repositorio.armazenarTabela(informacoes);

					}
				}
			}
		}
	}

	public DadosTabela[] getTabela(){					// funciona como uma pesquisa, le o arquivo de tabela
		Repositorio repositorio = new Repositorio();		// e repassa como um array de dados

		String tabela = repositorio.visualizarTabela();
		Salas sala = new Salas("");

		int count = 0;
		String caracteres = "";
		DadosTabela[] dados = new DadosTabela[sala.getTotalDeSalas()];

		if(!tabela.contains("ERRO3")){
			for (int i = 0; i<tabela.length(); i++){

				caracteres += tabela.charAt(i);

				if(tabela.charAt(i) == '('){
					dados[count] = new DadosTabela("","","","","","");
					caracteres = caracteres.replace("-", "");
					caracteres = caracteres.replace("(", "");
					caracteres = caracteres.replace("  ", "");
					dados[count].setSala(caracteres);
					caracteres = "";
				}
				if(tabela.charAt(i) == '|'){
					caracteres = caracteres.replace("|", "");
					dados[count].setBloco(caracteres);
					caracteres = "";
				}
				if(tabela.charAt(i) == ':'){
					caracteres = caracteres.replace(":", "");
					dados[count].setTipo(caracteres);
					caracteres = "";
				}
				if(tabela.charAt(i) == ','){
					caracteres = caracteres.replace(",", "");
					dados[count].setTurma(caracteres);
					caracteres = "";
				}

				if(tabela.charAt(i) == '.'){
					caracteres = caracteres.replace(".", "");
					dados[count].setNome(caracteres);
					caracteres = "";
				}

				if(tabela.charAt(i) == ')'){
					caracteres = caracteres.replace(")", "");
					dados[count].setStatus(caracteres);
					caracteres = "";
					count++;
				}
				if(tabela.charAt(i) == '\n'){
					caracteres = "";
				}
			}
			return dados;
		}
		else{
			return null;
		}
	}
}
