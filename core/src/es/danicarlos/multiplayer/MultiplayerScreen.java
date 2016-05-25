package es.danicarlos.multiplayer;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import es.danicarlos.botones.BotonExit;
import es.danicarlos.botones.BotonValidar;
import es.danicarlos.proyecto.BotonExitJuego;
import es.danicarlos.proyecto.BotonGirar;
import es.danicarlos.proyecto.BotonPause;
import es.danicarlos.proyecto.BotonRestart;
import es.danicarlos.proyecto.Pelota;
import es.danicarlos.proyecto.Punto;
import es.danicarlos.proyecto.Rueda;
import es.danicarlos.ventanas.AbstractScreen;
import es.danicarlos.ventanas.MainProyecto;
import es.danicarlos.ventanas.Screens;

public class MultiplayerScreen extends AbstractScreen implements WarpListener {
	private final static int WINDOW_WIDTH = 500;
	private final static int WINDOW_HEIGHT	 = 500;
	private SpriteBatch batch2;
	private Texture imgTexture, pelota, bIzq,bDer, punto, fondo,bPause, bRStart, bExit;
	private int  height, width, xCirc, yCirc, xPelota, yPelota;
	private ShapeRenderer figurator;
	private ArrayList<Vector2> misPuntos;
	private BitmapFont font,font2, puntuacion;
	private Sprite img,pelotaSprite, point, flechaIz,flechaDer;
	private Pelota miPelota;
	private Rueda miRueda;
	private Punto puntos;
	private int estrellas, tiempoIncr, state;
	private float scale;
	private boolean choqueBola;
	private Color[] colores={Color.YELLOW, Color.GREEN, Color.RED, Color.BLACK,Color.BLUE, Color.ORANGE};
	private Color[] siguienteColor;
	private MainProyecto miMP;
	private boolean paso;
	//private SimpleButton botonIzq, botonDer;
	private BotonGirar btIzq, btDer;
	private float  totalTime =30;
	private BotonPause btPause;
	private BotonRestart btRstart;
	private BotonExitJuego btExit;

	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_OVER = 3;
	private int mispuntos;
	
	private final String[] tryingToConnect = {"Connecting","to AppWarp"};
	private final String[] waitForOtherUser = {"Waiting for","other user"};
	private final String[] errorInConnection = {"Error in","Connection", "Go Back"};
	
	private final String[] game_win = {"Congrats You Win!", "Enemy Defeated"};
	private final String[] game_loose = {"Oops You Loose!","Target Achieved","By Enemy"};
	private final String[] enemy_left = {"Congrats You Win!", "Enemy Left the Game"};
	
	public MultiplayerScreen(MainProyecto main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	public void show () {
		Gdx.graphics.setWindowedMode(460,600);
		batch2= MainProyecto.getbatch();
		font=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		font2=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		puntuacion=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);

		
		
		
		switch(Gdx.app.getType()) {
		case Android:
			// android specific code
			font2.getData().setScale( 0.7f,0.7f);
			font.getData().setScale( 1.0f,1.0f);

			break;
		case Desktop:
			// desktop specific code
			font2.getData().setScale( 0.4f,0.4f);
			font.getData().setScale( 1.0f,1.0f);
			System.out.println("desk");
			break;
		default:
			font2.getData().setScale( 0.7f,0.7f);
			font.getData().setScale( 1.0f,1.0f);

			break;
		}



		estrellas=0;
		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();

		choqueBola=false;
		scale=height/width;
		imgTexture = new Texture("miJuegoMenu.png");
		fondo= new Texture("fondo.jpg");
		//img=new Sprite(imgTexture);
		//textRegion= new TextureRegion(img);
		pelota= new Texture("esfera.png");
		//Definimos valores medios
		xPelota=pelota.getWidth()+(width/2);
		yPelota=pelota.getHeight()+(height/2);	
		xCirc=(-imgTexture.getWidth()+width)/2;
		yCirc=(-imgTexture.getHeight()+height)/2;
		//Creador de figuras
		figurator = new ShapeRenderer();
		//Inicializamos la imagen de la ruleta
		img=new Sprite(imgTexture);
		img.setBounds(0, (height-img.getHeight())/2,width,width);
		img.setCenter(width/2,height/2);
		img.setOriginCenter();
		img.rotate(15);
		//Inicializamos imagenes de los botones
		bIzq=new Texture("flachaIzq.png");
		bDer=new Texture("flachaDer.png");
		bPause=new Texture("pause.png");
		bRStart=new Texture("playJuego.png");
		punto =new Texture("punto.png");
		bExit=new Texture("salirColor.png");
		//Botones

		btPause=new BotonPause(30, height-70);
		btIzq=new BotonGirar(bIzq, (-width/16)+width/8  ,width/12); 
		btDer=new BotonGirar(bDer,-width/8+width-width/16, width/12);
		btRstart=new BotonRestart(-width/20-bRStart.getWidth()+width/2 ,(-bRStart.getHeight()+height)/2);
		btExit=new BotonExitJuego(+width/20+width/2,(-bExit.getHeight()+height)/2);
		//btIzq=new BotonGirar(bIzq, -(bIzq.getWidth()/2)+(width/7),width/12); 
		//btDer=new BotonGirar(bDer,width-((bIzq.getWidth()/2)+(width/7)), width/12);
		//miRueda=new Rueda (img, width*19/43, width/2,height/2,this);

		//miPelota=new Pelota(pelotaSprite,width/2, height/2,this);

		//puntos=new Punto(point,this);

		//Tiempo
		totalTime=0;
		state=1;
		tiempoIncr=0;

	}
	//**********************************************


	@Override
	public void render (float delta){	



		switch (state) {
		case GAME_RUNNING:
			updateRunning();
			break;
		case GAME_PAUSED:
			updatePaused();

			break;
		case GAME_OVER:
			//updateGameOver();
			break;
		}    

		//Primero hacer updates y luego dibujar

		//El delta del movimiento pasarlo a traves del tiempo ( tutorial libgdx delta manbow2 )


	}

	//**********************************************



	private void updatePaused() {

	}

	//***********************************************************
	private void updateRunning() {
		// TODO Auto-generated method stub
		
		

	}



	//**********************************************


	public void actualizarStado(){

		/*	
		if(btPause.sePulsaElBoton()){

			state=2;
			//Screens.juego.setScreen(Screens.LOGINSCREEN);
			System.out.println("pause");



		}else if(btRstart.sePulsaElBoton()){
			state=1;
		}else if(btExit.sePulsaElBoton()){
			Screens.juego.setScreen(Screens.MAINSCREEN);
		}*/

	}




	public void dispose(){
		batch2.dispose();
		font.dispose();
		puntuacion.dispose();

	}
	
	public SpriteBatch getBatch() {
		return batch2;
	}
	public void setBatch(SpriteBatch batch) {
		this.batch2 = batch;
	}

	private float tamañoString(BitmapFont bmap,String palabra){
		GlyphLayout layout = new GlyphLayout();
		layout.setText(bmap, palabra);
		float width = layout.width;// contains the width of the current set text
		return width;
	}
	@Override
	public void onWaitingStarted(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onError(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGameStarted(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGameFinished(int code, boolean isRemote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGameUpdateReceived(String message) {
		// TODO Auto-generated method stub
		
	}
}