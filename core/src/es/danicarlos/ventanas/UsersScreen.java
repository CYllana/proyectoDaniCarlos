package es.danicarlos.ventanas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import es.danicarlos.botones.BotonNew;
import es.danicarlos.botones.BotonValidar;
import es.danicarlos.proyecto.Juego;

public class UsersScreen extends AbstractScreen  {
	private SpriteBatch batch;
	private Stage stage;
	private TextButton btnLogin;
	private TextField txtNombre,txtPassword;
	private float centroX,centroY;
	private Texture texture;
	private ShapeRenderer figurator;
	private BitmapFont lblUsuario,lblPassword,lblError;
	private String error;
	private BotonValidar btnLog;
	private BotonNew btnCre,btnRec ;
	private float width, height;
	public UsersScreen(MainProyecto juego) {
		super(juego);
		// TODO Auto-generated constructor stub
		
	}
	public void show(){
		stage=new Stage();
		Gdx.input.setInputProcessor(stage);
		batch= MainProyecto.getbatch();
		texture = new Texture("fondo7.png");

		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();
		centroY = height / 2 ; // Centro en el eje x de la pantalla centrando el botón
		centroX = width / 2 ; // Centro en el eje y de la pantalla centrando el botón
		creacionBtns();
		
		btnLog = new BotonValidar(centroX,centroY-Gdx.graphics.getHeight() / 7,"VALIDAR");
		btnCre = new BotonNew(centroX,centroY-Gdx.graphics.getHeight() / 5,"NEW");
		btnCre.getBordes().setWidth(btnLog.getBordes().getWidth()/2);
		btnCre.getBordes().setHeight(btnLog.getBordes().getHeight()/2);
		btnRec = new BotonNew(btnLog.getX()+btnLog.getBordes().getWidth(),centroY-Gdx.graphics.getHeight() / 5,"RECUPERAR");
		btnRec.getBordes().setWidth(btnLog.getBordes().getWidth()/2);
		btnRec.getBordes().setHeight(btnLog.getBordes().getHeight()/2);
		lblUsuario = new BitmapFont();
		lblPassword = new BitmapFont();
		lblError = new BitmapFont();
		error="";
			
	}

	public void render (float delta){
		Gdx.gl.glClearColor(0, 0, 0, (float) 0.5);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		btnLog.update();
		btnCre.update();
		btnRec.update();
		
		
		batch.begin();		
		batch.draw(texture, 0, 0, width, height);

		btnLog.draw(batch);
		btnRec.draw(batch);
		btnCre.draw(batch);
		lblUsuario.draw(batch, "Usuario", width / 3,height / 2+height / 7+txtNombre.getHeight());
		lblPassword.draw(batch, "Contrase�a", width / 3,height / 2+txtNombre.getHeight() );		
		lblError.setColor(Color.RED);
		lblError.draw(batch, error, width / 3,height / 7 );
		batch.end();
		

		stage.act(delta);
		stage.draw();
		

	}
	public void setError(String error){
		this.error = error;
	}
	
	synchronized public void verificacionUsuario(){
		System.out.println(txtNombre.getText());
		if( txtNombre.getText().equals("carlos")){
			setError("carlos ");
		}else{
			setError(" ");
		}
		Screens.juego.setScreen(Screens.MAINSCREEN);
	}
	
	public void creacionBtns(){

		/*game.manager.load("uiskin.json", Skin.class);
		skin = game.manager.get("uiskin.json", Skin.class);*/

		/*TextureAtlas miTexture= new TextureAtlas("uiskin.json");
		Skin skin = new Skin(miTexture);*/

		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

		btnLogin = new TextButton("Validar", skin);
		
		float anchuraBtn = width/3;
		float alturaBtn = height/12;
		
		btnLogin.setSize(anchuraBtn, alturaBtn);
		
		txtNombre = new TextField("", skin);
		txtNombre.setSize(anchuraBtn, alturaBtn);
		
		txtPassword = new TextField("", skin);
		txtPassword.setSize(anchuraBtn, alturaBtn);
		
		float centroY = Gdx.graphics.getHeight() / 2 - btnLogin.getHeight() / 2; // Centro en el eje y de la pantalla centrando el botón
		float centroX = Gdx.graphics.getWidth() / 2 - btnLogin.getWidth()/ 2; // Centro en el eje x de la pantalla centrando el botón		
		btnLogin.setPosition(centroX,centroY-height / 7);
		txtNombre.setPosition(centroX,centroY+height / 7);
		txtPassword.setPosition(centroX,centroY);	  
		char psw = '*';
		txtPassword.setPasswordCharacter(psw);
		txtPassword.setPasswordMode(true);
		
		
		stage.addActor(txtPassword);
		
		stage.addActor(txtNombre);
		//stage.addActor(btnLogin);
		
	}
	
	public void dispose(){
//		stage.dispose();
//		lblError.dispose();
//		lblPassword.dispose();
//		lblUsuario.dispose();
	}
	
}