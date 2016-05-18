package es.danicarlos.proyecto2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.danicarlos.botones.BotonesCuentas;
import es.danicarlos.proyecto.AbstractScreen;

public class ScoreScreen extends AbstractScreen  {
	private SpriteBatch batch;
	private BotonesCuentas usuario,contrase�a;
	private Texture texture;
	private String text;
	private BitmapFont fuente;
	private int puntuacion;
	private String nombre;
	private float height,width,centroY,centroX,centroYPantalla,centroXPantalla;
	public ScoreScreen(MainProyecto main) {
		super(main);
		// TODO Auto-generated constructor stub
		
		
	}
	public void show(){
		batch= MainProyecto.getbatch();
		texture = new Texture("fondo7.png");		
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		centroY = height / 2 - texture.getHeight() / 2; // Centro en el eje x de la pantalla centrando el botón
		centroX = Gdx.graphics.getWidth() / 2 - texture.getWidth()/ 2; // Centro en el eje y de la pantalla centrando el botón
		centroYPantalla = Gdx.graphics.getHeight() / 2;
		centroXPantalla = Gdx.graphics.getWidth() / 2;
		fuente = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		puntuacion=0;
		nombre="dani";
	}

	public void render (float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		updatePuntuacion();
		batch.begin();
		batch.draw(texture, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		fuente.draw(batch, nombre, centroXPantalla, centroYPantalla);
		batch.end();
	}
	
	private void updatePuntuacion(){
		
	}
}