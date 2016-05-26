package es.danicarlos.proyecto;

import java.util.ArrayList;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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

import es.danicarlos.ventanas.AbstractScreen;
import es.danicarlos.ventanas.MainProyecto;
import es.danicarlos.ventanas.MainScreen;
import es.danicarlos.ventanas.NewUserScreen;
import es.danicarlos.ventanas.RecuperarScreen;
import es.danicarlos.ventanas.ScoreScreen;
import es.danicarlos.ventanas.Screens;
import es.danicarlos.ventanas.UsersScreen;

public class Juego  extends AbstractScreen  {
	private SpriteBatch batch;
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
	public Juego(MainProyecto main) {
		super(main);
		// TODO Auto-generated constructor stub
	}
	public void show () {
		Gdx.graphics.setWindowedMode(460,600);
		batch= MainProyecto.getbatch();
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
		miRueda=new Rueda (img, width*19/43, width/2,height/2,this);





		misPuntos=miRueda.calculadorPosiciones(12,204,(width)/2,height/2);
		pelotaSprite=new Sprite(pelota);
		miPelota=new Pelota(pelotaSprite,width/2, height/2,this);

		point=new Sprite(punto);
		puntos=new Punto(point,this);

		//Tiempo
		totalTime=0;
		state=1;
		tiempoIncr=0;

		//vectorColores
		siguienteColor=new Color[2];
		siguienteColor[1]=colorAleatorio();
		siguienteColor[0]=colorAleatorio();
		font.setColor(siguienteColor[1]);
		miRueda.setRuedaDentro(siguienteColor[1]);

		//puntos
		mispuntos=0;
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
		figurator.begin(ShapeType.Line.Filled);
		figurator.setColor(Color.BLACK); 
		figurator.circle( width/2, height/2, width*19/43,1000 );
		figurator.end();

		batch.begin();
		batch.draw(bRStart,-width/20-bRStart.getWidth()+width/2 ,(-bRStart.getHeight()+height)/2);
		batch.draw(bExit,+width/20+width/2,(-bExit.getHeight()+height)/2);
		batch.end();
		actualizarStado();

	}

	//***********************************************************
	private void updateRunning() {
		// TODO Auto-generated method stub
		//Pintamos el fondo
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Draws
		batch.begin();
		//Imagen de fondo
		batch.draw(fondo, 0, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();

		float deltaTime = Gdx.graphics.getDeltaTime(); //You might prefer getRawDeltaTime()

		totalTime -= deltaTime; //if counting down

		int seconds = ((int)totalTime) % 60;
		mispuntos=mispuntos+seconds;
		int tiempo=30+seconds+tiempoIncr;
		tiempoIncr=0;
		if(tiempo<0){
			state=3;
		}



		//Updates
		actualizarRueda();
		miPelota.update();
		puntos.update();

		btPause.update();
		actualizarStado();



		figurator.begin(ShapeType.Line.Filled);
		figurator.setColor(Color.WHITE); 
		figurator.circle( width/2, height-width/10, width/7);
		figurator.end();
		
		batch.begin();
		puntuacion.draw(batch, ""+tiempo, width-width/10, height-width/15);
		//Nombre del color
		font2.setColor(siguienteColor[0]);
		float size2=tamañoString(font2,"Siguiente");
		font2.draw(batch, "Siguiente", (-size2+width)/2, height-width/40);
		if(choqueBola){
			if(miRueda.bordercolor(miPelota.getAngulo()).equals(siguienteColor[1])){
				miRueda.setRuedaDentro(siguienteColor[1]);
				siguienteColor[1]=siguienteColor[0];
				font.setColor(siguienteColor[1]);
				siguienteColor[0]=colorAleatorio();
				
				//font.draw(batch, "Puntooooo", width/2, height-30);
				totalTime=totalTime+5;
			}else{
				totalTime=totalTime-4;
			}

		}
		choqueBola=false;
		float size=tamañoString(font,"Color");
		font.draw(batch, "Color", (-size+width)/2, height-width/16);
		batch.end();
		miRueda.draw(figurator, width, height);
		batch.begin();
		//------Juego-----------------
		//Circulo de juego
		//img.draw(batch);
		//Pelota
		
		miPelota.draw(batch);
		//Puntos
		puntos.draw(batch);
		//Botones
		btDer.draw(batch);
		btIzq.draw(batch);
		batch.draw(bPause, 30,height-70);


		batch.end();
	


	}



	//**********************************************


	public void actualizarStado(){


		if(btPause.sePulsaElBoton()){

			state=2;
			//Screens.juego.setScreen(Screens.LOGINSCREEN);
			System.out.println("pause");



		}else if(btRstart.sePulsaElBoton()){
			state=1;
		}else if(btExit.sePulsaElBoton()){
			Screens.juego.setScreen(Screens.MAINSCREEN);
		}

	}




	public void dispose(){
		batch.dispose();
		font.dispose();
		puntuacion.dispose();

	}
	private void rotateLeft() {

		img.setRotation(img.getRotation() -2);
		miRueda.rotar(-2);
	}
	private void rotateRight() {

		img.setRotation(img.getRotation() + 2);
		miRueda.rotar(+2);

	}
	synchronized public void actualizarRueda(){
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||btDer.sePulsaElBoton()){
			rotateRight();
			miRueda.setRotacion(img.getRotation()-15);
			figurator.begin(ShapeType.Line.Filled);
			figurator.setColor(Color.YELLOW); 
			//figurator.circle( width-(height/20-(bIzq.getWidth()/2)+(width/7)), height/20+ width/12, height/20);
			figurator.end();
			//System.out.println(miRueda.getAngulo());


		}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)|| btIzq.sePulsaElBoton()){


			rotateLeft();
			miRueda.setRotacion(img.getRotation()-15);
			figurator.begin(ShapeType.Line.Filled);
			figurator.setColor(Color.YELLOW);
			//figurator.circle(height/20-(bIzq.getWidth()/2)+(width/7), height/20+ width/12, height/20);
			figurator.end();

			//System.out.println(miRueda.getAngulo());

		}

	}
	public SpriteBatch getBatch() {
		return batch;
	}
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}


	public int getState() {
		return state;
	}




	public void setState(int state) {
		this.state = state;
	}







	public float getTotalTime() {
		return totalTime;
	}




	public void setTotalTime(float totalTime) {
		this.totalTime =this.totalTime+ totalTime;
	}




	public int getTiempoIncr() {
		return tiempoIncr;
	}




	public void setTiempoIncr(int tiempoIncr) {
		this.tiempoIncr += tiempoIncr;
	}




	public boolean isChoqueBola() {
		return choqueBola;
	}
	public void setChoqueBola(boolean choqueBola) {
		this.choqueBola = choqueBola;
	}
	public Pelota getMiPelota() {
		return miPelota;
	}
	public void setMiPelota(Pelota miPelota) {
		this.miPelota = miPelota;
	}
	public Rueda getMiRueda() {
		return miRueda;
	}
	public void setMiRueda(Rueda miRueda) {
		this.miRueda = miRueda;
	}
	public Punto getPuntos() {
		return puntos;
	}
	public void setPuntos(Punto puntos) {
		this.puntos = puntos;
	}
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(int estrellas) {
		this.estrellas = this.estrellas+estrellas;
	}



	private Color colorAleatorio(){
		Random rnd=new Random();
		int valor=rnd.nextInt(6);
		return colores[valor];

	}
	private float tamañoString(BitmapFont bmap,String palabra){
		GlyphLayout layout = new GlyphLayout();
		layout.setText(bmap, palabra);
		float width = layout.width;// contains the width of the current set text
		return width;
	}
}