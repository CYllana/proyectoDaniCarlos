package es.danicarlos.proyecto;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Esta clase 
 * @author Carlos Yllana y Daniel Ibañez
 *
 */	
public class Rueda {

	private  Sprite img;
	private double radio;
	private float xCentro, yCentro;
	private ArrayList<Vector2> misPuntos;
	private static final int DIVISIONES=12;
	private double rotacion;
	private HashMap<Integer, Color> map;
	private Juego miJuego;
	private float transportX, transportY;
	private Color colorChoque;
	private Quesito[] misQuesos;
	private Color ruedaDentro;
	

	private ArrayList<Color> colores;
	
	public Rueda(Sprite miJuego, double radio, float xCentro, float yCentro) {
		super();
		img= miJuego;
		this.radio=radio;
		this.xCentro=xCentro;
		this.yCentro=yCentro;
		calculadorPosiciones(DIVISIONES,radio,xCentro,yCentro);
		colores= new ArrayList<Color>();
		colores();
		misQuesos=new Quesito[12];
		rueda();
		
		
		 
	}


	public Rueda(Sprite spriteRueda, double radio, float xCentro, float yCentro, Juego miJuego) {
		super();
		
		img= spriteRueda;
		this.radio=radio;
		this.xCentro=xCentro;
		this.yCentro=yCentro;
		calculadorPosiciones(DIVISIONES,radio,xCentro,yCentro);
		rotacion=0;
		this.miJuego=miJuego;
		map = new HashMap<Integer, Color>();
		colores= new ArrayList<Color>();
		colores();
		misQuesos=new Quesito[12];
		rueda();
	}
	public void draw(ShapeRenderer figurator, float width, float height){
		//System.out.println(rotacion);
		figurator.begin(ShapeType.Filled);
		figurator.setColor(ruedaDentro); 
		figurator.circle( width/2, height/2, width/2,210);
		figurator.setColor(Color.YELLOW); 
		
		for(int i=0; i<misQuesos.length; i++){
			figurator.setColor(misQuesos[i].getColor());
			figurator.arc(width/2, height/2, width*11/23, misQuesos[i].getAnguloInicio(),30,1000);
			
		}
		figurator.setColor(Color.WHITE); 
		figurator.circle( width/2, height/2, width*19/43,1000);
		figurator.end();
		figurator.begin(ShapeType.Line);
		figurator.setColor(Color.BLACK); 
		figurator.circle( width/2, height/2, width*19/43,1000);
		figurator.end();

	}


	public Sprite getImg() {
		return img;
	}



	public void setImg(Sprite img) {
		this.img = img;
	}




	public ArrayList<Vector2> getMisPuntos(float centrox, float centroy) {
		return calculadorPosiciones(12, DIVISIONES, centrox, centroy);
	}

	public void setMisPuntos(ArrayList<Vector2> misPuntos) {
		this.misPuntos = misPuntos;
	}
	public Color getRuedaDentro() {
		return ruedaDentro;
	}
	public void setRuedaDentro(Color ruedaDentro) {
		this.ruedaDentro = ruedaDentro;
	}
	public float getTransportX() {
		return transportX;
	}
	public void setTransportX(float transportX) {
		this.transportX = (float) (transportX+this.rotacion);
	}
	public float getTransportY() {
		return transportY;
	}
	public void setTransportY(float transportY) {
		this.transportY = transportY;
	}
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	public float getxCentro() {
		return xCentro;
	}
	public void setxCentro(float xCentro) {
		this.xCentro = xCentro;
	}
	public float getyCentro() {
		return yCentro;
	}
	public void setyCentro(float yCentro) {
		this.yCentro = yCentro;
	}

	public ArrayList<Vector2> getMisPuntos() {
		return misPuntos;
	}




	public double getRotacion() {
		return rotacion;
	}



	public void setRotacion(double d) {

		this.rotacion=d%360;

	}


	public ArrayList<Vector2> calculadorPosiciones(int numeroDiv, double radio, float centroX, float centroY){
		int angulo=360/numeroDiv;
		float x;
		float y;
		int j=0;
		MathUtils matUtils=new MathUtils();

		ArrayList<Vector2> misPuntos= new ArrayList<Vector2>();

		for(int i=0; i<361; i=i+angulo){

			x=(centroX+(matUtils.cosDeg(i)*(float)radio));
			y=(centroY+(matUtils.sinDeg(i)*(float)radio));
			Vector2 miVector=new Vector2(x,y);
			misPuntos.add(miVector);
			//vertices[j]=x;
			//vertices[j+1]=y;
			j=j+2;
		}

		return misPuntos;

	}
	public void colores(){
		 Color[] miscolores={Color.YELLOW, Color.GREEN, Color.RED, Color.BLACK,Color.BLUE, Color.ORANGE,Color.YELLOW, Color.GREEN, Color.RED, Color.BLACK,Color.BLUE, Color.ORANGE};
		 for (int i=0; i<miscolores.length; i++){
			 colores.add(miscolores[i]);
		 }
	
	}
	public void rueda(){
		for(int i=0; i<12; i++){
			Random rnd=new Random();
			int num=rnd.nextInt(colores.size());
			misQuesos[i]=new Quesito(i*30,i*30+30, colores.get(num));
			colores.remove(num);
		}
	}

	
	public void rotar(float rotacion){
		
		for(int i=0; i<12; i++){
			
			misQuesos[i].actualizarPosicion(rotacion);
			
			
			
		}
	}
	public Color bordercolor(double anguloPelota){
		int i=0;
		
			while(!misQuesos[i].perteneceAlQuesito(anguloPelota)){
			
			i++;
			
			if(i>11){
				System.out.println(misQuesos[1].getRotacion());
				for(int j=0; j<12; j++){
					
					//System.out.println(j+misQuesos[j].toString()+"  angulo pelota="+anguloPelota);
					
					
					
				}
				return Color.BLACK;
			}
			
		}
		//System.out.println(anguloPelota);
		return misQuesos[i].getColor();
		
		/*if(rotacion<60&&rotacion>-120){
			if(anguloPelota>=0 +rotacion&& anguloPelota<30+rotacion){
				return miColor.YELLOW;
			}else 	if(anguloPelota>=30+rotacion && anguloPelota<60+rotacion){
				//negro
				return miColor.BLACK;
			}else 	if(anguloPelota>=60+rotacion && anguloPelota<90+rotacion){
				//setTransportX(135);
				return miColor.GREEN;
			}
			else if(anguloPelota>=90+rotacion && anguloPelota<120+rotacion){
				return miColor.YELLOW;
			}else if(anguloPelota>=120+rotacion && anguloPelota<150+rotacion){
				//setTransportX(75);
				return miColor.GREEN;
			}else if(anguloPelota>=150+rotacion && anguloPelota<180+rotacion){
				return miColor.ORANGE;		
			}else if(anguloPelota>=180+rotacion && anguloPelota<210+rotacion){
				return miColor.BLUE;
			}else if(anguloPelota>=210+rotacion && anguloPelota<240+rotacion){
				return miColor.RED;
			}else if(anguloPelota>=240+rotacion && anguloPelota<270+rotacion){
				return miColor.ORANGE;
			}else if(anguloPelota>=270 +rotacion&& anguloPelota<300+rotacion){
				return miColor.BLACK;
			}else if(anguloPelota>=300+rotacion && anguloPelota<330+rotacion){
				return miColor.BLUE;
			}else if(anguloPelota>=330+rotacion && anguloPelota<360+rotacion){
				return miColor.RED;
			}else  {
				System.out.println("rotacion="+rotacion+" angulo que Pelota="+anguloPelota+ "  color=negro");
				System.out.println("FALLO");
				//negro	
				return miColor.GREEN;
			}
		}else if(rotacion>=240 ){
			if(anguloPelota>=-360+rotacion&& anguloPelota<-330+rotacion){
				return miColor.YELLOW;
			}else 	if(anguloPelota>=-330+rotacion && anguloPelota<-300+rotacion){
				return miColor.BLACK;
			}else 	if(anguloPelota>=-300+rotacion && anguloPelota<-270+rotacion){
				//setTransportX(360-225);
				return miColor.GREEN;
			}else if(anguloPelota>=-270+rotacion && anguloPelota<-240+rotacion){
				return miColor.YELLOW;
			}else if(anguloPelota>=-240+rotacion && anguloPelota<-210+rotacion){
				//setTransportX(360-285);
				return miColor.GREEN;
			}else if(anguloPelota>=-210+rotacion && anguloPelota<-180+rotacion){
				return miColor.ORANGE;
			}else if(anguloPelota>=-180+rotacion && anguloPelota<-150+rotacion){
				return miColor.BLUE;
			}else if(anguloPelota>=-150+rotacion && anguloPelota<-120+rotacion){
				return miColor.RED;
			}else if(anguloPelota>=-120+rotacion && anguloPelota<-90+rotacion){
				return miColor.ORANGE;
			}else if(anguloPelota>=-90 +rotacion&& anguloPelota<-60+rotacion){
				return miColor.BLACK;
			}else if(anguloPelota>=-60+rotacion && anguloPelota<-30+rotacion){
				return miColor.BLUE;
			}else if(anguloPelota>=-30+rotacion && anguloPelota<0+rotacion){
				return miColor.RED;
			}else  {
				System.out.println("rotacion="+rotacion+" angulo que Pelota="+anguloPelota+ "  color=verde");
				System.out.println("FALLO");
				//negro	
				return miColor.BLACK;
			}


		}else if((rotacion>60 && rotacion<240)|(rotacion<-120)){
			if(rotacion<-120){
				rotacion=360-rotacion*-1;
			}

			if(anguloPelota>=0 +rotacion&& anguloPelota<30+rotacion){
				return miColor.YELLOW;
			}else 	if(anguloPelota>=30+rotacion && anguloPelota<60+rotacion){
				//negro
				return miColor.BLACK;
			}else 	if(anguloPelota>=60+rotacion && anguloPelota<90+rotacion){
				//setTransportX(135);
				return miColor.GREEN;
			}
			else if(anguloPelota>=90+rotacion && anguloPelota<120+rotacion){
				return miColor.YELLOW;
			}else if(anguloPelota>=120+rotacion && anguloPelota<150+rotacion){
				//setTransportX(75);
				return miColor.GREEN;
			}else if(anguloPelota>=150+rotacion && anguloPelota<180+rotacion){
				return miColor.ORANGE;		
			}else if(anguloPelota>=180+rotacion && anguloPelota<210+rotacion){
				return miColor.BLUE;
			}else if(anguloPelota>=-180+rotacion && anguloPelota<-150+rotacion){
				return miColor.BLUE;
			}else if(anguloPelota>=-150+rotacion && anguloPelota<-120+rotacion){
				return miColor.RED;
			}else if(anguloPelota>=-120+rotacion && anguloPelota<-90+rotacion){
				return miColor.ORANGE;
			}else if(anguloPelota>=-90 +rotacion&& anguloPelota<-60+rotacion){
				return miColor.BLACK;
			}else if(anguloPelota>=-60+rotacion && anguloPelota<-30+rotacion){
				return miColor.BLUE;
			}else if(anguloPelota>=-30+rotacion && anguloPelota<0+rotacion){
				return miColor.RED;
			}else  {
				//System.out.println("rotacion="+rotacion+" angulo que Pelota="+anguloPelota+ "  color=verde");
				System.out.println("FALLO");
				//negro	
				return miColor.BLACK;
			}
		}else{
			System.out.println("rotacion="+rotacion+" angulo que Pelota="+anguloPelota+ "  color=amarillo");


			System.out.println("FALLO");

			//negro	
			return miColor.BLACK;

		}
		*/






	}


}


/*map = new HashMap<Integer, Color>();
map.put(1, Color.RED);		map.put(7, Color.GREEN);
map.put(2, Color.BLUE);		map.put(8, Color.GRAY);
map.put(3, Color.BLACK);	
map.put(4, Color.ORANGE);	
map.put(5, Color.YELLOW);		
map.put(6, Color.PURPLE);
vertices=new float[27];
graficos=new Graph()*/

/*//polygon vertices
/*figurator.begin(ShapeType.Line.Filled);
figurator.polygon(vertices);
figurator.end();


figurator.begin(ShapeType.Line.Filled);
figurator.setColor(Color.WHITE);
figurator.circle(xCirc, height/2, 200);
figurator.end();

Gdx.gl.glLineWidth(8);
figurator.begin(ShapeType.Line);


for(int i=0; i<(misPuntos.size());i++){
	Random rnd= new Random();

	figurator.setColor(map.get(rnd.nextInt(8)+1));


	if(i!=misPuntos.size()-1){

		figurator.line(misPuntos.get(i),  misPuntos.get(i+1));
	}else{
		figurator.line(misPuntos.get(i), misPuntos.get(0));

	}
}
figurator.end();





figurator.begin(ShapeType.Line);
figurator.arc(0, 0, 30, 0, 270);
Gdx.gl.glLineWidth(19);
//30º

/*figurator.setColor(Color.RED);
figurator.line(xCirc+200, yCirc ,xCirc+173, yCirc+100);

//60
figurator.setColor(Color.GREEN);
figurator.line(xCirc+173, yCirc+100 , xCirc+100 ,yCirc+173 );
//90
figurator.setColor(Color.PURPLE);
figurator.line(xCirc+100, yCirc+173 , xCirc ,yCirc+200 );
//120
figurator.setColor(Color.RED);
figurator.line(xCirc-100, yCirc+173 , xCirc ,yCirc+200 );
//150
figurator.setColor(Color.GRAY);
figurator.line(xCirc-100, yCirc+173 , xCirc-173, yCirc+	100 ); 
//180
figurator.setColor(Color.BLUE);
figurator.line(xCirc-173, yCirc+100,xCirc-200, yCirc );
//210
figurator.setColor(Color.GREEN);
figurator.line(xCirc-173, yCirc-100,xCirc-200, yCirc );
//240
figurator.setColor(Color.YELLOW);
figurator.line(xCirc-100, yCirc-173 , xCirc-173, yCirc-100 );

//270
figurator.setColor(Color.BROWN);
figurator.line(xCirc-100, yCirc-173 , xCirc ,yCirc-200 );
//300
figurator.setColor(Color.PURPLE);
figurator.line(xCirc+100, yCirc-173 , xCirc ,yCirc-200 );

//330
figurator.setColor(Color.BLACK);
figurator.line(xCirc+173, yCirc-100 ,  xCirc+100 ,yCirc-173 );
//360
figurator.setColor(Color.ORANGE);s
figurator.line(xCirc+173, yCirc-100,xCirc+200, yCirc );

//figurator.arc(xCirc, yCirc, 200, 0, 30);
figurator.setColor(Color.ORANGE);
//figurator.curve(0, 0, 0, 0, 0, 0, 100, 100,1000);
//figurator.polyline(vertices);




//figurator.line(xCirc+100, yCirc-173 , xCirc ,yCirc-200 );   
//figurator.setColor(0.0f, 0.0f, 0.0f, 1);
//figurator.circle(width/2, height/2, 10);
//System.out.println(matrix);

figurator.end();
/*figurator.begin(ShapeType.Line);
figurator.setColor(Color.BLACK);
//figurator.triangle(xCirc+173, yCirc+100, xCirc+173+5, yCirc+100+5, xCirc+173+5, yCirc-5);
Gdx.gl.glLineWidth(18);
//figurator.circle(xCirc, yCirc, 201);
	figurator.end();*/

/*
 * Color miVariable=new Color();
			if(anguloPelota>=rotacion && anguloPelota<rotacion+30){
				//amarillo
				miVariable= map.get(5);
			}else 	if(anguloPelota>=rotacion+30 && anguloPelota<rotacion+60){
				//negro
				miVariable=map.get(3);
			}else 	if(anguloPelota>=rotacion+60 && anguloPelota<rotacion+90){
				//verde
				miVariable= map.get(6);
			}
			else if(anguloPelota>=rotacion+90 && anguloPelota<rotacion+120){
				//amarillo
				miVariable= map.get(5);
			}else if(anguloPelota>=rotacion+120 && anguloPelota<rotacion+150){
				//verde
				miVariable= map.get(6);
			}else if(anguloPelota>=rotacion+150 && anguloPelota<rotacion+180){
				//naranja
				miVariable= map.get(4);
			}else if(anguloPelota>=rotacion+180 && anguloPelota<rotacion+210){
				//azul
				miVariable= map.get(2);
			}else if(anguloPelota>=rotacion+210 && anguloPelota<rotacion+240){
				//rojo
				miVariable= map.get(1);
			}else if(anguloPelota>=rotacion+240 && anguloPelota<rotacion+270){
				//naranja
				miVariable=map.get(4);
			}else if(anguloPelota>=rotacion+270 && anguloPelota<rotacion+300){
				//negro
				miVariable= map.get(3);
			}else if(anguloPelota>=rotacion+300 && anguloPelota<rotacion+330){
				//azul
				miVariable=map.get(2);
			}else {
				//rojo	
				miVariable= map.get(1);
			}
			System.out.println("rotacion="+rotacion+" angulo que Pelota="+anguloPelota+"  color=  "+miVariable.toString());
			return miVariable;
 */