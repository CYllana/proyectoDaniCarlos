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

public class MainProyecto extends ApplicationAdapter {
	
	private final static int WINDOW_WIDTH = 500;
	private final static int WINDOW_HEIGHT	 = 500;
	private SpriteBatch batch;
	private Texture imgTexture, pelota, bIzq,bDer;
	private BitmapFont font;
	private int  height, width, xCirc, yCirc, xPelota, yPelota;
	private ShapeRenderer figurator;
	private MathUtils matUtils;
	private ArrayList<Vector2> misPuntos;
	private Map<Integer, Color> map;
	private int xa =3;
	private int ya = 3;
	private Sprite img;
	private Animation anim;
	//private SimpleButton botonIzq, botonDer;
	private BotonGirar btIzq, btDer;

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(500,600);

		
		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		font= new BitmapFont();
		imgTexture = new Texture("juego1.png");
		//img=new Sprite(imgTexture);
		//textRegion= new TextureRegion(img);
		pelota= new Texture("esfera.png");
		
		xPelota=(-pelota.getWidth()+width)/2;
		yPelota=(-pelota.getHeight()+height)/2;	
		xCirc=(-imgTexture.getWidth()+width)/2;
		yCirc=(-imgTexture.getHeight()+height)/2;
		figurator = new ShapeRenderer();
		System.out.println(height);
		img=new Sprite(imgTexture);
		img.setBounds(0, width-img.getWidth(),width,width);
	    bIzq=new Texture("flachaIzq.png");
	    bDer=new Texture("flachaDer.png");
	    btIzq=new BotonGirar(bIzq, height, height/12); 
	    btDer=new BotonGirar(bDer, width-90, height/12);



	}
	@Override
	public void render () {
		Gdx.gl.glLineWidth(32);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		figurator.begin(ShapeType.Line.Filled);
	    figurator.setColor(Color.WHITE);
	    figurator.circle(+3+width/2, -11+height/2, -5+img.getHeight()/2);
	   // figurator.circle(70, 70, height/2);
	   //figurator.circle(width-70, 70, height/20);
	    figurator.end();

	    batch.begin();
	    img.draw(batch);
	    batch.end();
	    
	    

	    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||btDer.sePulsaElBoton()){
	    	 rotateRight();
	    	figurator.begin(ShapeType.Line.Filled);
	    	figurator.setColor(Color.YELLOW);
	 	    figurator.circle(width-70, 70, height/20);

	 	    figurator.end();
	    	

	    }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)|| btIzq.sePulsaElBoton()){
	    	figurator.begin(ShapeType.Line.Filled);
	    	figurator.setColor(Color.YELLOW);
	 	    figurator.circle(70, 70, height/20);
	 	    figurator.end();
	    	rotateLeft();
	    	 
	    }
	    	
	    
	    
	    
	    
	    
	    
	    
	   
	    double distancia=distanciaPuntos(xPelota,yPelota,(-pelota.getWidth()+width)/2, (-pelota.getHeight()+height)/2);
		int retval = Double.compare(distancia, 184);		
		if(retval>0){
			
			
				
				ya=-1+ya*-1;
				xa=-1+xa*-1;
			
		}
			xPelota=xPelota+xa;
			yPelota=yPelota+ya;

			batch.begin();
			btDer.draw(batch);
			btIzq.draw(batch);
			batch.end();
		
		
		
			
			//batch.dra
			//batch.draw(img,xCirc,yCirc );
			//batch.draw(bIzq, 45, 50);
			//batch.draw(bDer, width-90, 50);
			//batch.end();
		
		
		
		
	}
public static double distanciaPuntos(float x, float y, float centerX, float centerY){
		
		double respuest;
		double res1,res2;
		res1=centerX-x;  
		res2=centerY-y;
	    res1=Math.pow(res1, 2)+Math.pow(res2, 2);
	    respuest=Math.sqrt(res1);
	    return respuest;
	}
	
private void rotateLeft() {
    img.setRotation(img.getRotation() - 1);
}
private void rotateRight() {
    img.setRotation(img.getRotation() + 1);
}



}
