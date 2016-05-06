package es.danicarlos.proyecto;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Rueda {
	
	private  Sprite img;
	private double radio;
	private float xCentro, yCentro;
	private ArrayList<Vector2> misPuntos;
	private static final int DIVISIONES=12;
	

	
	
	
	public Rueda(Sprite miJuego, double radio, float xCentro, float yCentro) {
		super();
		img= miJuego;
		this.radio=radio;
		this.xCentro=xCentro;
		this.yCentro=yCentro;
		calculadorPosiciones(DIVISIONES,radio,xCentro,yCentro);
		
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

