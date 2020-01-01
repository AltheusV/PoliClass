package br.com.poli.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Arquivo {
	public static String Read(String caminho){
		String conteudo = "";
		try{
			FileReader arq = new FileReader(caminho);		 
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			try{
				linha = lerArq.readLine();
				while(linha!=null){
					conteudo += linha +"\n";
					linha = lerArq.readLine();
				}
				arq.close();
			} catch (IOException ex) {
				conteudo = "ERRO31: Não foi possível ler o arquivo.";
			}
		} catch (FileNotFoundException ex){
			conteudo = "ERRO32: arquivo não encontrado.";
		}
		return conteudo;
	}

	public static boolean Write(String caminho, String texto){
		try{
			FileWriter arq = new FileWriter(caminho, true);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println(texto);
			gravarArq.close();
			return true;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static void deleteFile(String caminho){
		File file = new File(caminho);
		if(file.exists()){
			file.delete();
		}
	}
	
	public void salvarAlunos(String informacoes){
		String arq = "Alunos.txt";
		Write(arq, informacoes);
	}
	
	public String lerAlunos(){
		String arq = "Alunos.txt";
		String informacoes = Read(arq);
		return informacoes;
	}
	
	public void deletarAlunos(){
		String arq = "Alunos.txt";
		deleteFile(arq);
	}
	
	public void salvarCadeiras(String informacoes){
		String arq = "Professores.txt";
		Write(arq, informacoes);
	}
	
	public String lerCadeiras(){
		String arq = "Professores.txt";
		String informacoes = Read(arq);
		return informacoes;
	}
	
	public void deletarCadeiras(){
		String arq = "Professores.txt";
		deleteFile(arq);
	}
	
	public void salvarSalas(String informacoes){
		String arq = "Salas.txt";
		Write(arq, informacoes);
	}
	
	public String lerSalas(){
		String arq = "Salas.txt";
		String informacoes = Read(arq);
		return informacoes;
	}
	
	public void deletarSalas(){
		String arq = "Salas.txt";
		deleteFile(arq);
	}
	
	public void salvarTabela(String informacoes){
		String arq = "Tabela.txt";
		Write(arq, informacoes);
	}

	public String lerTabela(){
		String arq = "Tabela.txt";
		String informacoes = Read(arq);
		return informacoes;
	}
	
	public void deletarTabela(){
		String arq = "Tabela.txt";
		deleteFile(arq);
	}
	public void salvarAviso(String informacoes){
		String arq = "Avisos.txt";
		Write(arq, informacoes);
	}

	public String lerAviso(){
		String arq = "Avisos.txt";
		String informacoes = Read(arq);
		return informacoes;
	}
	
	public void deletarAviso(){
		String arq = "Avisos.txt";
		deleteFile(arq);
	}
}
