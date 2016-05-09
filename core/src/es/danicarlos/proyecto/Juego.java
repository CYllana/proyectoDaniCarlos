package es.danicarlos.proyecto;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class Juego extends ApplicationAdapter {
	
	private final static int WINDOW_WIDTH = 500;
	private final static int WINDOW_HEIGHT	 = 500;
	private SpriteBatch batch;
	private Texture imgTexture, pelota, bIzq,bDer, punto;
	private BitmapFont font;
	private int  height, width, xCirc, yCirc, xPelota, yPelota;
	private ShapeRenderer figurator;
	private MathUtils matUtils;
	private ArrayList<Vector2> misPuntos;
	private Map<Integer, Color> map;
	private int xa =3;
	private int ya = 3;
	private Sprite img,pelotaSprite, point;
	private Animation anim;
	private Pelota miPelota;
	private Rueda miRueda;
	private Punto puntos;
	//private SimpleButton botonIzq, botonDer;
	private BotonGirar btIzq, btDer;

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(460,600);

		
		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		font= new BitmapFont();
		imgTexture = new Texture("miJuegof.png");
	
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
	    System.out.println((bIzq.getWidth()/2)+(width/7));
	    btIzq=new BotonGirar(bIzq, -(bIzq.getWidth()/2)+(width/7),width/12); 
	    btDer=new BotonGirar(bDer,width-((bIzq.getWidth()/2)+(width/7)), width/12);
	    System.out.println(width*19/43);
	    miRueda=new Rueda (img, width*19/43, width/2,height/2);

	    misPuntos=miRueda.calculadorPosiciones(12,204,(width)/2,height/2);
	    pelotaSprite=new Sprite(pelota);
	    miPelota=new Pelota(pelotaSprite,width/2, height/2,miRueda);
	    point=new Sprite(punto);
	    puntos=new Punto(point,this,width/20);

	}
	@Override
	public void render () {
		
		//Primero hacer updates y luego dibujar
		
		//El delta del movimiento pasarlo a traves del tiempo ( tutorial libgdx delta manbow2 )
		
		//Pintamos el fondo
		Gdx.gl.glLineWidth(32);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Dibujamos el circulo blanco de fondo
	    figurator.begin(ShapeType.Line.Filled);

	    figurator.setColor(Color.WHITE);
	    //circulo botones
	    //izq
	    figurator.circle(height/20-(bIzq.getWidth()/2)+(width/7), height/20+ width/12, height/20);
	   	//der
	    figurator.circle( width-(height/20-(bIzq.getWidth()/2)+(width/7)), height/20+ width/12, height/20);
	    figurator.end();
	    
	    //Dibujamos nuestro circulo de juego
	    batch.begin();
	    img.draw(batch);
	    batch.end();
	    
	    //Metodo que nos 
	    actualizarRueda();
	    batch.begin();
	    miPelota.draw(batch);
	    puntos.draw(batch);
	    batch.end();

	    
	
	    
	    
	    
	    
	    
	    
	   

			batch.begin();
			//batch.draw(punto, xCirc,yCirc);
			btDer.draw(batch);
			btIzq.draw(batch);
			batch.end();
		
		
		
			
			//batch.dra
			//batch.draw(img,xCirc,yCirc );
			//batch.draw(bIzq, 45, 50);
			//batch.draw(bDer, width-90, 50);
			//batch.end();

		
		
	}

	
private void rotateLeft() {
    img.setRotation(img.getRotation() -2);
}
private void rotateRight() {
    img.setRotation(img.getRotation() + 2);
}
public void actualizarRueda(){
	 if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||btDer.sePulsaElBoton()){
    	 rotateRight();
    	figurator.begin(ShapeType.Line.Filled);
    	figurator.setColor(Color.YELLOW); 
    	figurator.circle( width-(height/20-(bIzq.getWidth()/2)+(width/7)), height/20+ width/12, height/20);
 	    figurator.end();
    	

    }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)|| btIzq.sePulsaElBoton()){
    	figurator.begin(ShapeType.Line.Filled);
    	figurator.setColor(Color.YELLOW);
    	figurator.circle(height/20-(bIzq.getWidth()/2)+(width/7), height/20+ width/12, height/20);
 	    figurator.end();
    	rotateLeft();
    	 
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





}
