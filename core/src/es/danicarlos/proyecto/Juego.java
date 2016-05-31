package es.danicarlos.proyecto;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import es.danicarlos.botones.BotonExitJuego;
import es.danicarlos.botones.BotonGirar;
import es.danicarlos.botones.BotonPause;
import es.danicarlos.botones.BotonRestart;
import es.danicarlos.ventanas.AbstractScreen;
import es.danicarlos.ventanas.MainProyecto;
import es.danicarlos.ventanas.Screens;


/**
 * Clase juego
 * Es la clase que controla todo el apartado del juego.
 * 
 * @author Carlos Yllana y Daniel Ibañez
 *
 */
public class Juego  extends AbstractScreen implements HttpResponseListener {
	/**
	 * SpriteBatch es la clase que utilizamos en libgdx para dibujar en pantalla
	 */
	private SpriteBatch batch;
	/**
	 * La clase texture es la forma en la que cargamos las imagenes
	 */
	private Texture imgTexture, pelota, bIzq,bDer, punto, fondo,bPause, bRStart, bExit, gameOver;
	/**
	 * Valores de hancho y largo de pantalla, variables que utilizaremos para tener controlado el cambio a pantalla de movil. 
	 */
	private int  height, width;
	/**
	 * ShapeRender, es una clase que nos ofrece libgdx para hacer figuras. La utilizaremos básicamente para hacer la redonda de juego.
	 */
	private ShapeRenderer figurator;
	/**
	 * BitmapFont, lo utilizamos para mostrar textos.
	 */
	private BitmapFont font,font2, tiempoRestante,tiempoDurado,estrellasObten,barras,resultado, tick;
	/**
	 * El sprite es otra forma de mostrar imagenes. La utilizamos para objetos animados.
	 */
	private Sprite img,pelotaSprite, point;
	/**
	 * Variable de nuestra pelota.
	 */
	private Pelota miPelota;
	/**
	 * Variable de nuestra Rueda.
	 */
	private Rueda miRueda;
	/**
	 * Punto son las estrellas que salen dentro de nuestro juego las cuales nos aportan tiempo extra.
	 */
	private Punto puntos;
	/**ContadoreS:
	 * estrellas= Estrellas obtenidas.
	 * puntosTotale= contador de tiempo.
	 * tiempoIncr=tiempo que obtenmos cada vez que damos con un color correcto.
	 * state= estado de nuestro juego.
	 */
	private int estrellas,puntosTotales, tiempoIncr, state;
	/**
	 * TiempoActual es la variable que nos cuenta los segundos de juego que llevamos.
	 */
	private float tiempoActual;
	/**
	 * La booleana choqueBola es para saber si nuestra pelota ha chocado con un borde.
	 * la variable gameO es para saber si el juego ha terminado.
	 * 
	 */
	private boolean choqueBola, gameO ;
	/**
	 * Vector con los colores de nuestra rueda.
	 */
	private Color[] colores={Color.YELLOW, Color.GREEN, Color.RED, Color.BLACK,Color.BLUE, Color.ORANGE};
	/**
	 * Vector de colores, nos da información del color que toca y del siguiente.
	 */
	private Color[] siguienteColor;
	/**
	 * Botones que nos giraran a la izquiera o a la derecha.
	 */
	private BotonGirar btIzq, btDer;
	/**
	 * Variable que utilizaremos para calcular los segundos del juego
	 */
	private float  totalTime;
	/**
	 * Boton que nos activa el modo pausa.
	 */
	private BotonPause btPause;
	/**
	 * BotonRestart tiene dos funciones, si esta en pause, reanudar el juego. 
	 * Si esta en la pantalla de gameOver, nos empieza un juego nuevo.
	 * 
	 */
	private BotonRestart btRstart, btPlayGO;
	/**
	 * BotonExitJuego tiene la función de volvernos al menu principal del juego.
	 * 
	 */
	private BotonExitJuego btExit,btExitGO ;
	/**
	 * 
	 */
	private boolean getFin;
	private String resultadoWeb;
	private String nomb;
	private String url;
	private String httpMethod = Net.HttpMethods.GET;
	private String solicitud_variables = null;	
	private HttpRequest httpsolicitud;


	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_OVER = 3;
	private int mispuntos, tiempoLimite=5;
	public Juego(MainProyecto main) {
		super(main);


	}
	/**
	 * Se inicializan todas las variables
	 */
	public void show () {
		Gdx.graphics.setWindowedMode(460,600);
		batch= MainProyecto.getbatch();

		//Inicializamos el tipo de fuente
		font=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		font2=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		tiempoRestante=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		tiempoDurado=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		estrellasObten = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		resultado   = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		barras 	 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		tick = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);


		//Hacemos un control del tipo de fuente, segun sea para android o desktop ya que para android necesitaremos una fuente de mayor tamaño. 
		switch(Gdx.app.getType()) {
		case Android:
			// android specific code
			font2.getData().setScale( 0.7f,0.7f);
			font.getData().setScale( 1.0f,1.0f);
			tiempoDurado.getData().setScale( 0.7f,0.7f);
			estrellasObten.getData().setScale(  0.7f, 0.7f);
			resultado.getData().setScale(  0.7f, 0.7f);
			tiempoRestante.getData().setScale(  1.2f, 1.2f);
			tick.getData().setScale(  1.2f, 1.2f);
			break;
		case Desktop:
			// desktop specific code
			font2.getData().setScale( 0.4f,0.4f);
			font.getData().setScale( 0.7f, 0.7f);
			tiempoDurado.getData().setScale(  0.5f,0.5f);
			estrellasObten.getData().setScale(  0.5f, 0.5f);
			resultado.getData().setScale(  0.5f, 0.5f);
			tiempoRestante.getData().setScale(  0.7f, 0.7f);
			tick.getData().setScale(  0.7f, 0.7f);
			break;
		default:
			tiempoRestante.getData().setScale(  0.7f, 0.7f);
			font2.getData().setScale( 0.7f,0.7f);
			font.getData().setScale( 1.0f,1.0f);
			tiempoDurado.getData().setScale(   0.5f, 0.5f);
			estrellasObten.getData().setScale(  0.5f, 0.5f);
			resultado.getData().setScale(  0.7f, 0.7f);
			tick.getData().setScale(  0.7f, 0.7f);
			break;
		}

		//Variables;
		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();
		//Estrellascogida
		estrellas=0;
		choqueBola=false;
		gameO=true;
		//Creador de figuras
		figurator = new ShapeRenderer();
		//Inicializamos todas las imagenes
		imgTexture = new Texture("miJuegoMenu.png");
		fondo= new Texture("fondo.jpg");
		pelota= new Texture("esfera.png");
		bIzq=new Texture("flachaIzq.png");
		bDer=new Texture("flachaDer.png");
		bPause=new Texture("pause.png");
		bRStart=new Texture("playJuego.png");
		punto =new Texture("punto.png");
		bExit=new Texture("salirColor.png");
		gameOver=new Texture("gameover.png");
		
		//Botones
		btPause=new BotonPause(width/20, height-height/7);
		btIzq=new BotonGirar(bIzq,-width/5 -height/8+width/2 ,width/30); 
		btDer=new BotonGirar(bDer,+width/5+width/2, width/30);
		btRstart=new BotonRestart(-width/20-bRStart.getWidth()+width/2 ,(-bRStart.getHeight()+height)/2);
		btExit=new BotonExitJuego(+width/20+width/2,(-bExit.getHeight()+height)/2);
		btPlayGO=new BotonRestart(-height/10-width/20+width/2 ,(-bRStart.getHeight()/2)+(height)/3);
		btExitGO=new BotonExitJuego(+width/20+width/2,(-bExit.getHeight()/2)+(height)/3);

		//Objetos
		miRueda=new Rueda (img, width*19/43, width/2,height/2,this);
		pelotaSprite=new Sprite(pelota);
		miPelota=new Pelota(pelotaSprite,width/2, height/2,this);
		point=new Sprite(punto);
		puntos=new Punto(point,this);

		//Tiempo
		totalTime=0;
		state=1;
		tiempoIncr=0;
		tiempoActual=0;
		//tiempoLimite=10;
		//vectorColores
		siguienteColor=new Color[2];
		siguienteColor[1]=colorAleatorio();
		siguienteColor[0]=colorAleatorio();
		font.setColor(siguienteColor[1]);
		miRueda.setRuedaDentro(siguienteColor[1]);
		//puntos
		mispuntos=0;
		puntosTotales=0;
		

	}
	//**********************************************

	/**
	 * El método render se ejecutará uans 60 veces por segundo dependiendo de la carga del algoritmo.
	 * Las dos primeras lsentencias son para limpiar el buffer y pintar la pantalla.
	 * Éste método nos generará todo el movimiento y acciones de nuestro juego.
	 * Contiene un switch para manejarse en cada  estado del juego.
	 * 
	 */
	@Override
	public void render (float delta){	
		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(fondo, 0, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		switch (state) {
		case GAME_RUNNING:
			updateRunning(delta);
			break;
		case GAME_PAUSED:
			updatePaused();

			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}    



	}


	//**********************************************

	/**
	 * updateGameOver, es el método que se ejecuta cuando el tiempo es 0 o menor que 0. Esta situación indica que el juego ha terminado y hemos perdido.
	 * Cuando ejecutamos este método, paramos todas las acciones, y dibujamos un fondo negro.
	 * En este fondo encontramos el calculo de nuestra puntuación y dos botones.
	 * Estos botones nos dan la opción de nueva partida, o volver al menu.
	 * 
	 * 
	 */
	private void updateGameOver() {
		miRueda.draw(figurator, width, height);
		//Circulo Negro
		figurator.begin(ShapeType.Line.Filled);
		figurator.setColor(Color.BLACK); 
		figurator.circle( width/2, height/2, width*19/43,1000 );
		figurator.end();
		//Imagen GameOver
		batch.begin();
		
		batch.draw(gameOver,(-gameOver.getWidth()+width)/2 ,height/3+width/3);
		//calculo del string
		float sizeEstrellas=tamanyoString(estrellasObten,"tiempoDurado");
		//Texto de las puntuaciones
		estrellasObten.draw(batch,"\nEstrellas Obtenidas  =  "+estrellas+" x 3",-sizeEstrellas+(width)/2,+width/10+(height)/2);
		tiempoDurado.draw(batch,"Tiempo Total            =  "+(int)-tiempoActual+" x 1",-sizeEstrellas+(width)/2,+width/10+(height)/2);
		barras.draw(batch, "___________", -sizeEstrellas+(width)/2 ,(-(height)/50)+ (+width/10)+(height)/2);
		puntosTotales=(int) (estrellas*3+tiempoActual*(-1));
		resultado.draw(batch, "Resultado                 =      "+ (puntosTotales), -sizeEstrellas+(width)/2,(-(4*height)/50)+width/10+ (height)/2);
		//Botones de Nuevo juego y Salir
		batch.draw(bRStart,-height/10-width/20+width/2 ,(-bRStart.getHeight()/2)+(height)/3,height/10,height/10);
		batch.draw(bExit,+width/20+width/2,(-bExit.getHeight()/2)+(height)/3,height/10,height/10);

		batch.end();
		//Actualizamos el estado segun el boton clicado.
		actualizarStado();

	}
	//**********************************************
	/**
	 * updatePaused() se ejecuta cuando hemos clicado el boton de pausa.
	 * Como en gameOver, dibujamos el fondo negro de la pelota y aparecen dos botones.
	 * Estos botones nos dan la opción de volver a jugar o salir.
	 * 
	 */

	private void updatePaused() {
		miRueda.draw(figurator, width, height);
		//Dibujamos la rueda y su fondo negro
		miRueda.draw(figurator, width, height);
		figurator.begin(ShapeType.Line.Filled);
		figurator.setColor(Color.BLACK); 
		figurator.circle( width/2, height/2, width*19/43 ,1000);
		figurator.end();
		//Dibujamos los botones
		batch.begin();
		batch.draw(bRStart,-width/20-bRStart.getWidth()+width/2 ,(-bRStart.getHeight()+height)/2);
		batch.draw(bExit,+width/20+width/2,(-bExit.getHeight()+height)/2);
		batch.end();
		//Actualizamos estado segun los botones.
		actualizarStado();
	}

	//***********************************************************
	/**
	 * updateRunning es el metodo que ejecuta toda las acciones del juego.
	 *Por este motivo le pasamos el valor deltaTime, que utilizaremos para regular el movimiento de nuestro juego.
	 */
	private void updateRunning(float deltaTime) {
		//Imagen de fondo
		
		//Calcula el tiempo que llevamos jugando, y el tiempo que nos queda en el juego.
		tiempoActual-=deltaTime;
		totalTime -= deltaTime; //if counting down
		//System.out.println(tiempoActual);
		int secondsPortados=((int)tiempoActual) % 60;
		int seconds = ((int)totalTime) % 60;
		//System.out.println(secondsPortados);
		mispuntos=mispuntos+seconds;
		int tiempo=30+seconds+tiempoIncr;
		tiempoIncr=0;
		//System.out.println(-secondsPortados);
		if((-1*tiempoActual)>tiempoLimite){
			System.out.println("--------------------------------------------");
			System.out.println("2  "+tiempoLimite+"  Seco"+secondsPortados);
			tiempoLimite=tiempoLimite+5;
			//System.out.println(tiempoLimite);
			miPelota.setSpeed((float)0.25);
		}
		//Cambia a estado GameOver si el tiempo es menor a 0
		if(tiempo<=0){
			tiempo=0;
			state=3;
		}

		//Updates
		actualizarTiempo(seconds);
		actualizarRueda();
		miPelota.update(deltaTime);
		puntos.update(tiempoActual);
		btPause.update();
		actualizarStado();





		//------DRAW  Juego-----------------
		miRueda.draw(figurator, width, height);

		batch.begin();
		//tiempo
		tiempoRestante.draw(batch, ""+tiempo, width-width/10, height-width/15);

		//Pelota
		miPelota.draw(batch);
		//Puntos
		puntos.draw(batch);
		//Botones
		btDer.draw(batch);
		btIzq.draw(batch);
		batch.draw(bPause,width/20, height-height/8);
		batch.end();

	}

	//**********************************************
	/**
	 * El método actualizarTiempo nos actualiza el tiempo que nos queda de juego según si hemos dado 
	 * con un borde del color que toca, o con un borde del color que no tocaba.
	 * Si el borde es correcto, nos sumara 5 segundos, sino, nos restará.
	 * Tambien actualiza siguiente color. 
	 * 
	 * @param seconds es el tiempo que tenemos actualmente.
	 */
	public void actualizarTiempo(float seconds){
		if(choqueBola){
			if(miRueda.bordercolor(miPelota.getAngulo()).equals(siguienteColor[1])){
				
				siguienteColor[1]=siguienteColor[0];
				miRueda.setRuedaDentro(siguienteColor[1]);
				font.setColor(siguienteColor[1]);
				siguienteColor[0]=colorAleatorio();
				//tiempoActual=seconds;
				totalTime=totalTime+5;
			}else{
				totalTime=totalTime-4;
			}

		}
		choqueBola=false;
	}

	/**
	 * actualizarStado, nos actualizar el estado de juego dependiendo del bóton que pulsemos
	 * 
	 */
	public void actualizarStado(){
		if(state!=3){
			if(btPause.sePulsaElBoton()){
				state=2;
			}else if(btRstart.sePulsaElBoton()){
				state=1;
			}else if(btExit.sePulsaElBoton()){
				Screens.juego.setScreen(Screens.MAINSCREEN);
			}
		}else{
			if(btPlayGO.sePulsaElBoton()){
				Screens.juego.setScreen(Screens.GAMESCREEN);
			}else if(btExitGO.sePulsaElBoton()){
				Screens.juego.setScreen(Screens.MAINSCREEN);
			}
		}

	}


	public void dispose(){
		batch.dispose();
		font.dispose();
		tiempoRestante.dispose();

	}

	/**
	 * actualizaRueda, nos girá nuestra rueda de juego a la izquierda o a la derecha dependiendo de la tecla o bóton que apretemos.
	 */
	 public void actualizarRueda(){
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||btDer.sePulsaElBoton()){
			miRueda.rotar(+2);
		}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)|| btIzq.sePulsaElBoton()){
			miRueda.rotar(358);
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

	public boolean isGameO() {
		return gameO;
	}
	public void setGameO(boolean gameO) {
		this.gameO = gameO;
	}
	/**
	 * Este método nos escoge un color de nuestro vector de colores.
	 * @return Retorna un Color aleatorio.
	 */
	private Color colorAleatorio(){
		Random rnd=new Random();
		int valor=rnd.nextInt(6);
		return colores[valor];

	}
	/**
	 * tamayoString es el método que utilizamos para saber el ancho de nuestra cadena de carácteres.
	 * 
	 * @param bmap tipo de fuente.
	 * @param palabra String del que queremos saber el tamaño
	 * @return retorna cuantos pixels tiene de ancho nuestro palabra.
	 */
	private float tamanyoString(BitmapFont bmap,String palabra){
		GlyphLayout layout = new GlyphLayout();
		layout.setText(bmap, palabra);
		float width = layout.width;// contains the width of the current set text
		return width;
	}
	/**
	 * Método que nos inserta los puntos realizados en nuestra sesión de juego,dentro de la base de datos
	 */
	public void insertarPuntos(){
	/*	url = "http://"+MainProyecto.ip_web+"/putPuntacion.php?";
		solicitud_variables = "&ID="+MainProyecto.getId()+"&PUNTOS="+puntosTotales;			
		httpsolicitud = new HttpRequest(httpMethod);
		httpsolicitud.setUrl(url);
		httpsolicitud.setContent(solicitud_variables);
		Gdx.net.sendHttpRequest(httpsolicitud, Juego.this);
		*/
	}
	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		Gdx.app.log("Status code ", "" + httpResponse.getStatus().getStatusCode());
		resultadoWeb=httpResponse.getResultAsString();	
		//System.out.println(resultado);
		getFin=true;
	}
	@Override
	public void failed(Throwable t) {
		// TODO Auto-generated method stub

	}
	@Override
	public void cancelled() {
		// TODO Auto-generated method stub

	}
}