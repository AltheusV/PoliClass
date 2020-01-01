package br.com.poli.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tela {
	public void mostrarTela(ActionEvent event, String caminho){
		try {
			Parent root = FXMLLoader.load(getClass().getResource(caminho));	// recebe o caminho e carrega
			Scene scene = new Scene(root);									// passando um root para criar uma nova cena
			Node node = (Node)event.getSource();							// A janela continua a mesma, entretanto a cena será
			Stage stage = (Stage)node.getScene().getWindow();				// alterada (tela)
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void mostrarTela(ActionEvent event, String caminho, String sala, String bloco){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(caminho));	// funciona parecido a anterior,
			Parent root = (Parent) fxmlLoader.load();									// entretanto, ela da um set em duas informacoes 
			AlteracaoController controller = fxmlLoader.<AlteracaoController>getController();	// no proximo controller, que serao utilizadas
			controller.setSala(sala);															// * classe AlteracaoController
			controller.setBloco(bloco);
			Scene scene = new Scene(root);
			Node node = (Node)event.getSource();
			Stage stage = (Stage)node.getScene().getWindow();
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}