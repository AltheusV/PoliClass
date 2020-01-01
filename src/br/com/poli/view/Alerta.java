package br.com.poli.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Alerta {
	
	private static String caracteres = "-():|,.";			// define os caracteres invalidos.
															
	public static boolean verificaCaractere(String texto){		//verificacao, caso o texto contiver um dos caracteres, retorna que existe (true) 
		for (int i=0; i<caracteres.length(); i++){
			if(texto.contains(String.valueOf(caracteres.charAt(i)))){	// valueof pois contains precisa receber uma string
				return true;
			}
		}
		return false;
	}
	
	public void setAlerta(String header, String content){		// Recebe como parametro um titulo e um texto
		Alert alert = new Alert(AlertType.WARNING);				// Apenas cria uma nova janela de Alerta.
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		alert.setTitle("PoliClass");
		stage.getIcons().add(new Image("/br/com/poli/resources/PC-LOGO1.png"));
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}