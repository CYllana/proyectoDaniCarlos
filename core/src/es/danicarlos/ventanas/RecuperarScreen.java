package es.danicarlos.ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import es.danicarlos.botones.BotonExit;
import es.danicarlos.botones.BotonValidar;

public class RecuperarScreen extends AbstractScreen  {
	private SpriteBatch batch;
	private Stage stage;
	private TextField txtID,txtCorreo;
	private float centroX,centroY;
	private Texture texture;
	private BitmapFont lblID,lblError,lblCorreo;
	private String error;
	private BotonValidar btnEnviar;
	private float width, height;
	private BotonExit btnAtras;
	public RecuperarScreen(MainProyecto main) {
		super(main);
		// TODO Auto-generated constructor stub
		
	}
	public void show(){
		stage=new Stage();
		Gdx.input.setInputProcessor(stage);
		batch= MainProyecto.getbatch();
		texture = new Texture("fondo7.png");
		
		height = Gdx.graphics.getHeight();
		width  = Gdx.graphics.getWidth();
		
		centroY = height / 2 ; // Centro en el eje x de la pantalla centrando el botón
		centroX = width / 2 ; // Centro en el eje y de la pantalla centrando el botón
		creacionBtns();
		
		btnEnviar = new BotonValidar(centroX,(float) (centroY-height /2.5),"ENVIAR");
		btnAtras = new BotonExit(width/9,height/20,"LOGIN");
		
		lblID 		 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		lblCorreo 	 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		lblError 	 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		error=" ";

		switch(Gdx.app.getType()) {
			case Android:
				// android specific code
				lblID.getData().setScale( 1.0f,1.0f);
				lblCorreo.getData().setScale( 1.0f,1.0f);
				lblError.getData().setScale( 1.0f,1.0f);
				System.out.println("andr");
				break;
			case Desktop:
				// desktop specific code
				lblID.getData().setScale( 0.7f,0.7f);
				lblCorreo.getData().setScale( 0.7f,0.7f);
				lblError.getData().setScale( 0.7f,0.7f);
				System.out.println("desk");
				break;
			default:
				lblID.getData().setScale( 1.0f,1.0f);
				lblCorreo.getData().setScale( 1.0f,1.0f);
				lblError.getData().setScale( 1.0f,1.0f);
				break;
		}
		
	}

	public void render (float delta){
		Gdx.gl.glClearColor(0, 0, 0, (float) 0.5);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		btnEnviar.update();
		btnAtras.update();
		
		batch.begin();		
		batch.draw(texture, 0, 0, width, height);
		btnEnviar.draw(batch);
		btnAtras.draw(batch);
		lblID.draw(batch, "ID", width/ 4,height / 2+(height / 7) +txtID.getHeight());
		lblCorreo.draw(batch, "Correo", width / 4,height / 2+txtID.getHeight() );	
		
		lblError.setColor(Color.RED);
		lblError.draw(batch, error, width / 4,height / 6 );
		batch.end();


		stage.act(delta);
		stage.draw();
		

	}
	public void setError(String error){
		this.error = error;
	}
	
	
	public void creacionBtns(){
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		float anchuraBtn = width/2;
		float alturaBtn = height/12;
		
		txtID = new TextField("", skin);
		txtID.setSize(anchuraBtn, alturaBtn);
		
		txtCorreo = new TextField("", skin);
		txtCorreo.setSize(anchuraBtn, alturaBtn);
		
		float centroY = height / 2 - txtID.getHeight() / 2; // Centro en el eje y de la pantalla centrando el botón
		float centroX = width / 2 - txtID.getWidth()/ 2; // Centro en el eje x de la pantalla centrando el botón		
		
		txtID.setPosition(centroX,centroY+(height / 7));
		txtCorreo.setPosition(centroX,centroY);
		
		stage.addActor(txtCorreo);
		stage.addActor(txtID);
		
	}
	
	public void dispose(){
		stage.dispose();
		lblError.dispose();
		lblID.dispose();
		lblCorreo.dispose();
		batch.dispose();
	}
}