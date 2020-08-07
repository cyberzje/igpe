package project.igpe.classes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Hero {
	private static String name;
	private static Boolean sex; //0 maschio | 1 femmina
	private static int life=100;
	private static int speed=10;
	private static int size=60;
	private Image img;
	private static int x;
	private static int y;
	private static List<Bullet> contenitoreBullets;

	public Hero() {
		name = "nome";
		sex = false;
		ResetPosition();
		contenitoreBullets=new ArrayList<Bullet>();
	}
	
	public static void resetHero () {
		life = 100;
		x=Settings.x/2;
		y=Settings.y/2;
	}
	
	public static void ResetPosition () {
		x=Settings.x/2;
		y=Settings.y/2;
	}
	
	public static void shoot() {
		Bullet bullet =  new Bullet(getX(), getY(), Movement.getDir(), 10);
		contenitoreBullets.add(bullet);
	}


	public static List<Bullet> getContenitoreBullets() {
		return contenitoreBullets;
	}

	public static void setContenitoreBullets(List<Bullet> bullets) {
		Hero.contenitoreBullets = bullets;
	}

	
	
	public static String getName() {
		return name;
	}

	public void setName(String nome) {
		Hero.name = nome;
	}

	public static Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sesso) {
		Hero.sex = sesso;
	}

	public static int getLife() {
		return life;
	}

	public static void setLife(int lifex) {
		life = lifex;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speedx) {
		speed = speedx;
	}

	public static int getSize() {
		return size;
	}

	public static void setSize(int sizex) {
		size = sizex;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public static int getX() {
		return x;
	}

	public static void setX(int xx) {
		x = xx;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int yy) {
		y = yy;
	}

	
}
