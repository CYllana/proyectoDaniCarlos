
package es.danicarlos.proyecto;

import com.badlogic.gdx.graphics.Color;
/**
 * Clase que asigna cada segmento de la rueda un color.
 * @author Carlos Yllana y Daniel Ibañez
 *
 */
public class Quesito {
	/**
	 * Ángulo en el que empieza el segmento
	 */
	private float anguloInicio;
	/**
	 * Ángulo en el que acaba el segmento
	 */
	private float angulofinal;
	/**
	 * Color que asignamos a este segmento
	 */
	private Color color;
	/**
	 * Valor de rotación que tiene la rueda, por lo tanto valor que tendremos que asignar a nuestros segmentos.
	 */
	private double rotacion;


	/**
	 * Constructor simple para definir nuestros segmentos
	 * @param anguloInicio
	 * @param angulofinal
	 * @param color
	 */
	public Quesito(float anguloInicio, float angulofinal, Color color) {
		super();
		this.rotacion=0;
		this.anguloInicio = anguloInicio;
		this.angulofinal = angulofinal;
		this.color = color;
	}
	public float getAnguloInicio() {
		return anguloInicio;
	}

	public void setAnguloInicio(float anguloInicio) {
		this.anguloInicio = anguloInicio;
	}
	public float getAngulofinal() {
		return angulofinal;
	}
	public void setAngulofinal(float angulofinal) {
		this.angulofinal = angulofinal;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}



	public double getRotacion() {
		return rotacion;
	}
	public void setRotacion(float rotacion) {
		this.rotacion = rotacion;
	}
	@Override
	public String toString() {
		return "Quesito [anguloInicio=" + anguloInicio + ", angulofinal=" + angulofinal + ", color=" + color + "rotacion="+rotacion+"]";
	}

	/**
	 * actualiza posición viene dada a partir de la rotación que nuestra rueda tenga.
	 * A los dos angulos le tendremos que sumar la rotación.
	 * Si el angulo pasa de 360 tendremos que  divirlo para así tener siempre los angulos en 360 grados.
	 * @param rotacion
	 */
	public synchronized void actualizarPosicion(double rotacion){
		this.anguloInicio=(float) (this.anguloInicio+rotacion);
		this.angulofinal=(float) (this.angulofinal+rotacion);

		if(this.angulofinal>360)this.angulofinal=this.angulofinal%360;
		if(this.anguloInicio>360)this.anguloInicio=this.anguloInicio%360;


	}
	/**
	 * Método que nos dice si dado un angulo, este angulo pertenece al sector dado por el angulo mayor y menor de cada quesito.
	 * Para ello hacemos la primera comprobación ademas, hay que comprobar la situación de si el ángulo inferior
	 * es mayor a 330 ya que en este caso la primera condición no afectaria. 
	 * @param anguloPelota
	 * @return
	 */
	public boolean perteneceAlQuesito(double anguloPelota){

		if(anguloInicio<=anguloPelota &&angulofinal>=anguloPelota){
			return true;
		}else if(anguloInicio>330) {
				if((anguloInicio-360<=anguloPelota && angulofinal>=anguloPelota)||(anguloInicio<=anguloPelota && angulofinal+360>=anguloPelota)){
					return true;
				}
			return false;
		}else return false;
		
	}
	/*
	if(rotacion<60&&rotacion>-120){
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