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
import com.badlogic.gdx.graphics.OrthographicCamera;
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
private OrthographicCamera cam;
private SpriteBatch batch;
private Texture imgTexture, pelota, bIzq,bDer, punto, fondo,bPause, bRStart, bExit, gameOver;
private int  height, width;
private ShapeRenderer figurator;
private BitmapFont font,font2, tiempoRestante,tiempoDurado,estrellasObten,barras,resultado;
private Sprite img,pelotaSprite, point;
private Pelota miPelota;
private Rueda miRueda;
private Punto puntos;
private int estrellas, tiempoIncr, state;

private boolean choqueBola;
private Color[] colores={Color.YELLOW, Color.GREEN, Color.RED, Color.BLACK,Color.BLUE, Color.ORANGE};
private Color[] siguienteColor;
private BotonGirar btIzq, btDer;
private float  totalTime =30;
private BotonPause btPause;
private BotonRestart btRstart, btPlayGO;
private BotonExitJuego btExit,btExitGO ;

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
	cam = new OrthographicCamera(460, 600);
	//Gdx.graphics.setWindowedMode(460,600);
	batch= MainProyecto.getbatch();

	//Inicializamos el tipo de fuente
	font=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	font2=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	tiempoRestante=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	tiempoDurado=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	estrellasObten = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	resultado   = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	barras 	 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	//lblPassword  = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
	//lblPassword2 = new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);

	switch(Gdx.app.getType()) {
	case Android:
		// android specific code
		 cam.zoom +=5;
		font2.getData().setScale( 0.7f,0.7f);
		font.getData().setScale( 1.0f,1.0f);
		tiempoDurado.getData().setScale( 0.7f,0.7f);
		estrellasObten.getData().setScale(  0.7f, 0.7f);
		resultado.getData().setScale(  0.7f, 0.7f);
		tiempoRestante.getData().setScale(  1.2f, 1.2f);

		break;
	case Desktop:
		// desktop specific code
		font2.getData().setScale( 0.4f,0.4f);
		font.getData().setScale( 0.7f, 0.7f);
		tiempoDurado.getData().setScale(  0.5f,0.5f);
		estrellasObten.getData().setScale(  0.5f, 0.5f);
		resultado.getData().setScale(  0.5f, 0.5f);
		tiempoRestante.getData().setScale(  0.7f, 0.7f);

		break;
	default:
		tiempoRestante.getData().setScale(  0.7f, 0.7f);
		font2.getData().setScale( 0.7f,0.7f);
		font.getData().setScale( 1.0f,1.0f);
		tiempoDurado.getData().setScale(   0.5f, 0.5f);
		estrellasObten.getData().setScale(  0.5f, 0.5f);
		resultado.getData().setScale(  0.5f, 0.5f);

		break;
	}

	//Variables;
	height=Gdx.graphics.getHeight();
	width=Gdx.graphics.getWidth();
	//Estrellascogida
	estrellas=0;
	choqueBola=false;

	//Creador de figuras
	figurator = new ShapeRenderer();
	//Inicializamos la imagen de la ruleta
	/*img=new Sprite(imgTexture);
	img.setBounds(0, (height-img.getHeight())/2,width,width);
	img.setCenter(width/2,height/2);
	img.setOriginCenter();
	img.rotate(15);*/
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
	
	//batch.draw(bRStart,-height/10-width/20+width/2 ,(-bRStart.getHeight()/2)+(height)/3,height/10,height/10);
	//float size2=tamanyoString(font2,"Siguiente");
	//font2.draw(batch, "Siguiente", (-size2+width)/2, height/3);

	//batch.draw(bExit,+width/20+width/2,(-bExit.getHeight()/2)+(height)/3,height/10,height/10);

	
	//Objetos
	miRueda=new Rueda (img, width*19/43, width/2,height/2,this);
	//misPuntos=miRueda.calculadorPosiciones(12,204,(width)/2,height/2);
	pelotaSprite=new Sprite(pelota);
	miPelota=new Pelota(pelotaSprite,width/2, height/2,this);
	point=new Sprite(punto);
	puntos=new Punto(point,this);
	//labels


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
		updateGameOver();
		break;
	}    

	//Primero hacer updates y luego dibujar

	//El delta del movimiento pasarlo a traves del tiempo ( tutorial libgdx delta manbow2 )


}


//**********************************************



private void updateGameOver() {

	figurator.begin(ShapeType.Line.Filled);
	figurator.setColor(Color.BLACK); 
	figurator.circle( width/2, height/2, width*19/43 );
	figurator.end();

	batch.begin();
	batch.draw(gameOver,(-gameOver.getWidth()+width)/2 ,height/3+width/3);
	
	//estrellasObten,resultado;
	float sizeEstrellas=tamanyoString(estrellasObten,"tiempoDurado");
	estrellasObten.draw(batch,"\nEstrellas Obtenidas  =  "+estrellas+" x 3",-sizeEstrellas+(width)/2,+width/10+(height)/2);
	tiempoDurado.draw(batch,"Tiempo Total            =  "+mispuntos+" x 1",-sizeEstrellas+(width)/2,+width/10+(height)/2);
	barras.draw(batch, "___________", -sizeEstrellas+(width)/2 ,(-(height)/50)+ (+width/10)+(height)/2);
	resultado.draw(batch, "Resultado                 =      "+ (estrellas*3+mispuntos*1), -sizeEstrellas+(width)/2,(-(4*height)/50)+width/10+ (height)/2);


	batch.draw(bRStart,-height/10-width/20+width/2 ,(-bRStart.getHeight()/2)+(height)/3,height/10,height/10);
	//float size2=tamanyoString(font2,"Siguiente");
	//font2.draw(batch, "Siguiente", (-size2+width)/2, height/3);

	batch.draw(bExit,+width/20+width/2,(-bExit.getHeight()/2)+(height)/3,height/10,height/10);




	batch.end();
	actualizarStado();
	int tiempo=0;
}

//**********************************************



private void updatePaused() {
	miRueda.draw(figurator, width, height);
	figurator.begin(ShapeType.Line.Filled);
	figurator.setColor(Color.BLACK); 
	figurator.circle( width/2, height/2, width*19/43 );
	figurator.end();

	batch.begin();
	batch.draw(bRStart,-width/20-bRStart.getWidth()+width/2 ,(-bRStart.getHeight()+height)/2);
	float size2=tamanyoString(font2,"Siguiente");
	
	batch.draw(bExit,+width/20+width/2,(-bExit.getHeight()+height)/2);
	batch.end();
	actualizarStado();

}

//***********************************************************
private void updateRunning() {
	//Imagen de fondo
	batch.begin();
	batch.draw(fondo, 0, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	batch.end();

	float deltaTime = Gdx.graphics.getDeltaTime(); //You might prefer getRawDeltaTime()
	totalTime -= deltaTime; //if counting down
	int seconds = ((int)totalTime) % 60;
	mispuntos=mispuntos+seconds;
	int tiempo=30+seconds+tiempoIncr;
	tiempoIncr=0;
	if(tiempo<0){
		tiempo=0;
		state=3;
	}
	//Updates
	actualizarTiempo();
	actualizarRueda();
	miPelota.update();
	puntos.update();

	btPause.update();
	actualizarStado();

	
	miRueda.draw(figurator, width, height);
	//------DRAW  Juego-----------------
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
	btPause.draw(batch);
	


	batch.end();



}



//**********************************************
public void actualizarTiempo(){
	if(choqueBola){
		if(miRueda.bordercolor(miPelota.getAngulo()).equals(siguienteColor[1])){
			
			siguienteColor[1]=siguienteColor[0];
			miRueda.setRuedaDentro(siguienteColor[1]);
			font.setColor(siguienteColor[1]);
			siguienteColor[0]=colorAleatorio();

			//font.draw(batch, "Puntooooo", width/2, height-30);
			totalTime=totalTime+5;
		}else{
			totalTime=totalTime-4;
		}

	}
	choqueBola=false;
}

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
		System.out.println("paso");
		if(btPlayGO.sePulsaElBoton()){
			//System.out.println("pasoo");
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
private void rotateLeft() {

	//img.setRotation(img.getRotation() -2);
	miRueda.rotar(-2);
}
private void rotateRight() {

	//img.setRotation(img.getRotation() + 2);
	miRueda.rotar(+2);

}
synchronized public void actualizarRueda(){
	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||btDer.sePulsaElBoton()){
		rotateRight();
		//miRueda.setRotacion(img.getRotation()-15);
		figurator.begin(ShapeType.Line.Filled);
		figurator.setColor(Color.YELLOW); 
		//figurator.circle( width-(height/20-(bIzq.getWidth()/2)+(width/7)), height/20+ width/12, height/20);
		figurator.end();
		//System.out.println(miRueda.getAngulo());


	}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)|| btIzq.sePulsaElBoton()){


		rotateLeft();
		//miRueda.setRotacion(img.getRotation()-15);
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
private float tamanyoString(BitmapFont bmap,String palabra){
	GlyphLayout layout = new GlyphLayout();
	layout.setText(bmap, palabra);
	float width = layout.width;// contains the width of the current set text
	return width;
}
}