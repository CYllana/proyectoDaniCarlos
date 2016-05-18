package es.danicarlos.proyecto;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import es.danicarlos.ventanas.MainProyecto;
import es.danicarlos.ventanas.MainScreen;
import es.danicarlos.ventanas.NewUserScreen;
import es.danicarlos.ventanas.RecuperarScreen;
import es.danicarlos.ventanas.ScoreScreen;
import es.danicarlos.ventanas.Screens;
import es.danicarlos.ventanas.UsersScreen;

public class Juego  extends Game {

	private final static int WINDOW_WIDTH = 500;
	private final static int WINDOW_HEIGHT	 = 500;
	private SpriteBatch batch;
	private Texture imgTexture, pelota, bIzq,bDer, punto, fondo;
	private int  height, width, xCirc, yCirc, xPelota, yPelota;
	private ShapeRenderer figurator;
	private ArrayList<Vector2> misPuntos;
	private BitmapFont font, puntuacion;
	private Sprite img,pelotaSprite, point, flechaIz,flechaDer;
	private Pelota miPelota;
	private Rueda miRueda;
	private Punto puntos;
	private int estrellas;
	private int tiempo;
	private boolean choqueBola;
	private Color[] colores={Color.YELLOW, Color.GREEN, Color.RED, Color.BLACK,Color.BLUE, Color.ORANGE};
	private MainProyecto miMP;
	//private SimpleButton botonIzq, botonDer;
	private BotonGirar btIzq, btDer;
	@Override
	public void create () {
		miMP=new MainProyecto();
		Gdx.graphics.setWindowedMode(460,600);
		font=new BitmapFont();
		puntuacion=new BitmapFont();
		estrellas=0;
		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		choqueBola=false;
		
	
		imgTexture = new Texture("miJuegof.png");
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
		//Inicializamos los botones
		bIzq=new Texture("flachaIzq.png");
		bDer=new Texture("flachaDer.png");

		//
		punto =new Texture("punto.png");
		btIzq=new BotonGirar(bIzq, (-width/16)+width/8  ,width/12); 
		btDer=new BotonGirar(bDer,-width/8+width-width/16, width/12);
		//btIzq=new BotonGirar(bIzq, -(bIzq.getWidth()/2)+(width/7),width/12); 
		//btDer=new BotonGirar(bDer,width-((bIzq.getWidth()/2)+(width/7)), width/12);
		System.out.println(width*19/43);
		miRueda=new Rueda (img, width*19/43, width/2,height/2,this);

		misPuntos=miRueda.calculadorPosiciones(12,204,(width)/2,height/2);
		pelotaSprite=new Sprite(pelota);
		miPelota=new Pelota(pelotaSprite,width/2, height/2,this);

		point=new Sprite(punto);
		puntos=new Punto(point,this);

		//Tiempo
		tiempo=30;

		// Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		//miPelota=new Pelota((width)/2, height/2,miRueda);

		/*Label nameLabel = new Label("Name:", skin);
	    TextField nameText = new TextField("aaa",skin);
	    Label addressLabel = new Label("Address:", skin);
	    TextField addressText = new TextField("bbb",skin);

	    table = new Table();
	    table.add(nameLabel).expandX(); // Column 0 receives all extra horizontal space.
	    table.add(nameText).width(100);
	    table.row();
	    table.add(addressLabel);
	    table.add(addressText).width(100);*/
		

		//Screens.GAMESCREEN      = new Juego(this); // Se inicializan todas las pantallas
		
				Screens.MAINSCREEN      = new MainScreen(miMP);
				Screens.SCORESCREEN     = new ScoreScreen(miMP);
				
				Screens.NEWSCREEN       = new NewUserScreen(miMP);
				Screens.RECUPERARSCREEN = new RecuperarScreen(miMP);
				Screens.LOGINSCREEN     = new UsersScreen(miMP);
				//setScreen(Screens.GAMESCREEN);
				setScreen(Screens.LOGINSCREEN);
			}


				
	@Override
	public void render () {
		
		//Primero hacer updates y luego dibujar

		//El delta del movimiento pasarlo a traves del tiempo ( tutorial libgdx delta manbow2 )

		//Pintamos el fondo
		Gdx.gl.glLineWidth(32);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Updates
		actualizarRueda();
		miPelota.update();
		puntos.update();

		//Draws
		batch.begin();
		//Imagen de fondo
		batch.draw(fondo, 0, 0,  width, height);
		//Puntuación
		puntuacion.draw(batch, ""+estrellas, width-20, height-20);
		//Nombre del color
		if(choqueBola){
			font.setColor(colores[miRueda.bordercolor(miPelota.getAngulo()).ordinal()]);
		

		}
		choqueBola=false;
		font.draw(batch, "Color", -"Color".length()+width/2, height-10);
		
		//------Juego-----------------
		//Circulo de juego
		img.draw(batch);
		//Pelota
		miPelota.draw(batch);
		//Puntos
		puntos.draw(batch);
		//Botones
		btDer.draw(batch);
		btIzq.draw(batch);
		
		batch.end();



		

	}


	private void rotateLeft() {

		img.setRotation(img.getRotation() -1);

	}
	private void rotateRight() {

		img.setRotation(img.getRotation() + 1);

	}
	synchronized public void actualizarRueda(){
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||btDer.sePulsaElBoton()){
			miMP.show();
			rotateRight();
			miRueda.setRotacion(img.getRotation()-15);
			figurator.begin(ShapeType.Line.Filled);
			figurator.setColor(Color.YELLOW); 
			figurator.circle( width-(height/20-(bIzq.getWidth()/2)+(width/7)), height/20+ width/12, height/20);
			figurator.end();
			//System.out.println(miRueda.getAngulo());


		}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)|| btIzq.sePulsaElBoton()){
			
			
			rotateLeft();
			miRueda.setRotacion(img.getRotation()-15);
			figurator.begin(ShapeType.Line.Filled);
			figurator.setColor(Color.YELLOW);
			figurator.circle(height/20-(bIzq.getWidth()/2)+(width/7), height/20+ width/12, height/20);
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








}
