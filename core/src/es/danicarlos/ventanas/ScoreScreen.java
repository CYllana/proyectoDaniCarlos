package es.danicarlos.ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.danicarlos.botones.BotonExit;
import es.danicarlos.ventanas.AbstractScreen;

public class ScoreScreen extends AbstractScreen  {
	private SpriteBatch batch;
	private Texture texture;
	private String text;
	private BitmapFont fuente,fuente1,fuente2,fuente3,fuenteTitulo;
	private String[] puntuacion = new String[10]; 
	private String[] nombre = new String[10];
	private float height,width,centroY,centroX,centroYPantalla,centroXPantalla;
	private BotonExit btnAtras;
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
		btnAtras = new BotonExit(width/9,height/20,"INICIO");
		
		fuente = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		fuente1 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		fuente2 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		fuente3 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		fuenteTitulo = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		fuente1.setColor(Color.GREEN);
		fuente2.setColor(Color.CYAN);
		fuente3.setColor(Color.RED);
		fuenteTitulo.setColor(Color.GOLDENROD);
		switch(Gdx.app.getType()) {		
		   case Android:
		       // android specific code
			   fuente.getData().setScale( 2.0f,2.0f);
			   fuente1.getData().setScale( 2.0f,2.0f);
			   fuente2.getData().setScale( 2.0f,2.0f);
			   fuente3.getData().setScale( 2.0f,2.0f);
			   fuenteTitulo.getData().setScale( 2.3f,2.3f);
			   System.out.println("andr");
			   break;
		   case Desktop:
		       // desktop specific code
			   fuente.getData().setScale( 0.7f,0.7f);
			   fuente1.getData().setScale( 0.7f,0.7f);
			   fuente2.getData().setScale( 0.7f,0.7f);
			   fuente3.getData().setScale( 0.7f,0.7f);
			   fuenteTitulo.getData().setScale( 1.0f,1.0f);
			   System.out.println("desk");
			   break;
			default:
				fuente.getData().setScale( 1.0f,1.0f);
				fuente1.getData().setScale( 1.0f,1.0f);
				fuente2.getData().setScale( 1.0f,1.0f);
				fuente3.getData().setScale( 1.0f,1.0f);
				fuenteTitulo.getData().setScale( 1.3f,1.3f);
				break;
		}
	}

	public void render (float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		updatePuntuacion();
		btnAtras.update();
		batch.begin(); 
		batch.draw(texture, 0, 0, width, height);
		btnAtras.draw(batch);
		fuenteTitulo.draw(batch, "Mejores Puntuaciones", centroXPantalla/4, centroYPantalla+(fuente.getLineHeight()*7));
		
		fuente1.draw(batch, nombre[0], centroXPantalla, centroYPantalla+(fuente.getLineHeight()*5));
		fuente2.draw(batch, nombre[1], centroXPantalla, centroYPantalla+(fuente.getLineHeight()*4));
		fuente2.draw(batch, nombre[2], centroXPantalla, centroYPantalla+(fuente.getLineHeight()*3));
		fuente.draw(batch, nombre[3], centroXPantalla, centroYPantalla+(fuente.getLineHeight()*2));
		fuente.draw(batch, nombre[4], centroXPantalla, centroYPantalla+fuente.getLineHeight());
		fuente.draw(batch, nombre[5], centroXPantalla, centroYPantalla);
		fuente.draw(batch, nombre[6], centroXPantalla, centroYPantalla-fuente.getLineHeight());
		fuente.draw(batch, nombre[7], centroXPantalla, centroYPantalla-(fuente.getLineHeight()*2));
		fuente.draw(batch, nombre[8], centroXPantalla, centroYPantalla-(fuente.getLineHeight()*3));
		fuente3.draw(batch, nombre[9], centroXPantalla, centroYPantalla-(fuente.getLineHeight()*4));
		
		fuente1.draw(batch, puntuacion[0], centroXPantalla/2, centroYPantalla+(fuente.getLineHeight()*5));
		fuente2.draw(batch, puntuacion[1], centroXPantalla/2, centroYPantalla+(fuente.getLineHeight()*4));
		fuente2.draw(batch, puntuacion[2], centroXPantalla/2, centroYPantalla+(fuente.getLineHeight()*3));
		fuente.draw(batch, puntuacion[3], centroXPantalla/2, centroYPantalla+(fuente.getLineHeight()*2));
		fuente.draw(batch, puntuacion[4], centroXPantalla/2, centroYPantalla+fuente.getLineHeight());
		fuente.draw(batch, puntuacion[5], centroXPantalla/2, centroYPantalla);
		fuente.draw(batch, puntuacion[6], centroXPantalla/2, centroYPantalla-fuente.getLineHeight());
		fuente.draw(batch, puntuacion[7], centroXPantalla/2, centroYPantalla-(fuente.getLineHeight()*2));
		fuente.draw(batch, puntuacion[8], centroXPantalla/2, centroYPantalla-(fuente.getLineHeight()*3));
		fuente3.draw(batch, puntuacion[9], centroXPantalla/2, centroYPantalla-(fuente.getLineHeight()*4));
		batch.end();
		
	}
	
	private void updatePuntuacion(){
		//consultar bbd
		int i = 0;
		for (i=0; i<10;i++){
			puntuacion[i]=i+"-P";
			nombre[i]="dani"+i;
		}
	}
}