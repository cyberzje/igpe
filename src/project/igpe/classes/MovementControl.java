package project.igpe.classes;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import project.igpe.GUI.MenuIniziale;
import project.igpe.GUI.SceneHandler;


public class MovementControl implements EventHandler<KeyEvent> {

	private Movement movimento;
	private static Scene sceneGame;
	
	public MovementControl(Movement movimento) {
		this.movimento = movimento;
	}

	@Override
	public void handle(KeyEvent e) {
		switch (e.getCode()) {
		case LEFT:
			movimento.move(Movement.MOVE_LEFT);
			break;
		case RIGHT:
			movimento.move(Movement.MOVE_RIGHT);
			break;
		case UP:
			movimento.move(Movement.MOVE_UP);
			break;
		case DOWN:
			movimento.move(Movement.MOVE_DOWN);
			break;
		case X:
			Hero.setLife(100);			
		case L:
			if (Hero.getLife()>0)
				Hero.setLife(Hero.getLife()-10);			
			break;
			
		case SPACE:
			Hero.shoot();
			break;
			
		case ESCAPE:
			
			//creo animazione cerchio
			
			// Switcho sul menu di PAUSA
			FXMLLoader loader = new FXMLLoader(MenuIniziale.class.getResource("Pausa.fxml"));  
			AnchorPane root = null;
			try { root = (AnchorPane) loader.load(); } catch (IOException e1) {	e1.printStackTrace();}			
			Scene menuPausa = new Scene(root, 1270, 900);	

			try {	SceneHandler.SleepScene(menuPausa);	} catch (Exception e2) {e2.printStackTrace();	}

			//Main.window.setScene(menuPausa);
			
				
			
			
			break;
		case P:
				
			break;
		default:
			break;
		}
	}

	public static Scene getSceneGame() {
		return sceneGame;
	}
	

}
