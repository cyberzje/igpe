package project.igpe.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GraphicsGame extends StackPane{
	
	private static Canvas canvas;
	private StackPane root;
	public static boolean firstRoom = true;
	private static EventHandler<KeyEvent> keyHandler;
	private static Movement movimento;
	
	
	public GraphicsGame(Movement movimentox) {
		root = this;
		movimento = movimentox;
		canvas = new Canvas();
		canvas.setFocusTraversable(true);
		keyHandler=new MovementControl(movimento);
		canvas.setOnKeyPressed(keyHandler);
		canvas.setOnKeyReleased(keyHandler);
		
		getChildren().add(canvas);			
		movimento.setGraphicGame(this);
	
		if(!firstRoom) {
			try {
				setBg(Movement.getnRand());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				spawnBg();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
		canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
	}
	
	
	public void spawnBg() throws Exception {
		Image caricaSfondo = null;
		try {
			
			caricaSfondo = new Image (new FileInputStream(Maps.getFirstRoomImg()));
			BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT); 
			Background sfondo = new Background(backgroundimage);
			
			this.setBackground(sfondo);
			
		} catch (FileNotFoundException e) {
			System.out.println("JPG - Sfondo non trovato");
			e.printStackTrace();
		}

	}
	
	public void setBg(int index) throws Exception {
		
		if (Movement.isDoorDown()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getUpImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorDx()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getLeftImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorLx()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getRightImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}
		
		if (Movement.isDoorUp()) {
			Maps.setIndiceMappe(index);
			Maps.loadMap(index);
			Image caricaSfondo = null;
			try {

				caricaSfondo = new Image(new FileInputStream(Maps.getDownImg().get(index)));
				BackgroundImage backgroundimage = new BackgroundImage(caricaSfondo, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				Background sfondo = new Background(backgroundimage);

				this.setBackground(sfondo);

			} catch (FileNotFoundException e) {
				System.out.println("JPG - Sfondo non trovato");
				e.printStackTrace();
			} 
		}

	}
	

	
	public void draw() {
		
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());		
		canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), Hero.getX(), Hero.getY(), Hero.getSize(), Hero.getSize());
		
		// Vita in alto a SX
		canvas.getGraphicsContext2D().setFill(Color.RED);
		Font font = new Font("Verdana", 20);
		canvas.getGraphicsContext2D().setFont(font);
		canvas.getGraphicsContext2D().fillText("Vita: " + Hero.getLife() + "%", 50, 50);
		
		//bullets
		for(Bullet b:Hero.getContenitoreBullets()) {
			canvas.getGraphicsContext2D().drawImage(b.getImgBulletDX(), b.getPosX(), b.getPosY(), Settings.block/2,Settings.block/2);
		}
		
		
		for (int i = 0; i < Settings.xMatrix; i++) {
			for (int j = 0; j < Settings.yMatrix; j++) {				
				switch (movimento.getRoom().getCella()[i][j].getType()) {						
				case Cell.OBSTACLE:
					canvas.getGraphicsContext2D().setFill(Color.BLUE);
					canvas.getGraphicsContext2D().fillRect(Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Settings.block*1, Settings.block*1);						
					break;		
				case Cell.DOOR:
					canvas.getGraphicsContext2D().setFill(Color.YELLOW);
					canvas.getGraphicsContext2D().fillRect(Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Settings.block*1, Settings.block*1);						
					break;	
				case Cell.OBSTACLEDAMAGE:
					canvas.getGraphicsContext2D().setFill(Color.RED);
					canvas.getGraphicsContext2D().fillRect(Movement.matrixInPixelX(i), Movement.matrixInPixelY(j), Settings.block*1, Settings.block*1);						
					break;
				default:
					break;					
				}
			}
		}
		
	}
		/*
		 // DISEGNARE SU MAPPA MATRICE
			for (int i = 0; i < movimento.getRoom().getCella().length; i++) {
				int x = i * Settings.block;
				for (int j = 0; j < movimento.getRoom().getCella()[i].length; j++) {
					int y = j * Settings.block;
					
					
					switch (movimento.getRoom().getCella()[i][j].getType()) {						
					case Cell.OBSTACLE:
						canvas.getGraphicsContext2D().setFill(Color.BLUE);
						canvas.getGraphicsContext2D().fillRect(x+Settings.block/15, y, Settings.block*1, Settings.block*1);						
						break;										
					default:
						break;
					}
			// DISEGNARE PG BLOCCHI MATRICE		
			//		if (movimento.getPg().getX() == i && movimento.getPg().getY() == j)
			//			canvas.getGraphicsContext2D().drawImage(GraphicHero.getImg(), x, y, Hero.getSize(), Hero.getSize());
				}
			}
			*/	
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvasx) {
		canvas = canvasx;
	}


	public static boolean getFirstRoom() {
		return firstRoom;
	}


	public static void setFirstRoom(boolean firstRoom) {
		GraphicsGame.firstRoom = firstRoom;
	}

	
	
}
