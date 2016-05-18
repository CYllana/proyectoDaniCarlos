package es.danicarlos.ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import es.danicarlos.botones.BotonExit;
import es.danicarlos.botones.BotonPlay;
import es.danicarlos.botones.BotonPuntuaciones;
import es.danicarlos.proyecto.Juego;
import es.danicarlos.proyecto.Pelota;
import es.danicarlos.proyecto.Rueda;

public class MainScreen extends AbstractScreen {
	private SpriteBatch batch;
	private BotonExit exit;
	private BotonPlay play;
	private BotonPuntuaciones puntuaciones;
	private Texture texture,pelota;
	private Sprite img;
	private int centroY ;
	private int centroX;
	private float centroYPantalla ;
	private float centroXPantalla ;
	private Pelota miPelota;
	private Sprite pelotaSprite;
	private Rueda miRueda;
	private ShapeRenderer figurator;
	public MainScreen(MainProyecto juego) {
		super(juego);
		// TODO Auto-generated constructor stub
		
		
	}
	public void show(){
		batch= MainProyecto.getbatch();
		texture = new Texture("miJuegof.png");
		
		centroY = Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2; // Centro en el eje y de la pantalla centrando el botón
		centroX = Gdx.graphics.getWidth() / 2 - texture.getWidth()/ 2; // Centro en el eje x de la pantalla centrando el botón
		centroYPantalla = Gdx.graphics.getHeight() / 2;
		centroXPantalla = Gdx.graphics.getWidth() / 2;
		
		play = new BotonPlay(centroXPantalla,centroYPantalla+50);
		exit = new BotonExit(centroXPantalla-50,centroYPantalla-25);
		puntuaciones = new BotonPuntuaciones(centroXPantalla+50,centroYPantalla-25);
		System.out.println("entra");
		
		figurator = new ShapeRenderer();
		img=new Sprite(texture);
		img.setBounds(0, (Gdx.graphics.getHeight()-img.getHeight())/2,Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
		img.setCenter(centroXPantalla,centroYPantalla);
		img.setOriginCenter();
		img.rotate(15);
		miRueda=new Rueda (img, Gdx.graphics.getWidth()*19/43, centroXPantalla,centroYPantalla);
		pelota= new Texture("esfera.png");
		pelotaSprite=new Sprite(pelota);
		miPelota=new Pelota(pelotaSprite,centroXPantalla, centroYPantalla,miRueda);
	}

	public void render (float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		exit.update(); // Comprobamos que se pulsan los botones
		play.update();
		puntuaciones.update();
		//System.out.println("x"+miPelota.getPosicionX());
		batch.begin();
		miPelota.draw(batch);
		play.setX(miPelota.getPosicionX());
		play.setY(miPelota.getPosicionY());
		//miPelota.g
		batch.draw(texture, centroX, centroY, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		exit.draw(batch); // Dibujamos el botón exit
		play.draw(batch); // Dibujamos el botón play
		puntuaciones.draw(batch); // Dibujamos el botón puntuaciones
		batch.end();
		
		//Comprobacion posiciones
	    figurator.begin(ShapeType.Line.Filled);
	    figurator.setColor(Color.RED);
	    //figurator.circle(miPelota.getBordes().x,miPelota.getBordes().y,miPelota.getBordes().radius);
	    //figurator.circle(puntos.getBordes().x,puntos.getBordes().y,puntos.getBordes().radius);
	    //figurator.circle(puntos.getX(),puntos.getY(), 3);
	    figurator.circle(play.getX(),play.getY(), 3);
	    figurator.end();
	}
}
