package es.danicarlos.proyecto;

import com.badlogic.gdx.graphics.Color;

public class Quesito {
	
	private float anguloInicio;
	private float angulofinal;
	private Color color;
	private double rotacion;
	
	
	
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
	
	public void actualizarPosicion(double rotacion){
		if(rotacion<0){
			this.anguloInicio=(float) (this.anguloInicio+rotacion);
			this.angulofinal=(float) (this.angulofinal+rotacion);
			System.out.println(anguloInicio+rotacion);
			if(anguloInicio+rotacion<0){
				System.out.println(anguloInicio+rotacion);
				
				this.anguloInicio=(float) (360+rotacion);
			}if(angulofinal+rotacion<0){
			
				this.angulofinal=(float) (360+rotacion);
			}
		}else{
			this.anguloInicio=(float) (this.anguloInicio+rotacion);
			this.angulofinal=(float) (this.angulofinal+rotacion);
		}
		if(this.angulofinal>360)this.angulofinal=this.angulofinal%360;
		if(this.anguloInicio>360)this.anguloInicio=this.anguloInicio%360;
		
		
	}
	public boolean perteneceAlQuesito(double anguloPelota){
		
		if(anguloInicio<=anguloPelota &&angulofinal>=anguloPelota){
			//System.out.println("ANGULO PELOTA="+anguloPelota+"  anguloInicio="+this.anguloInicio+"anguloFInal="+this.angulofinal+"  AnguloPelota"+anguloPelota+"  ROTACION="+this.rotacion);
			return true;
		}else {
			//System.out.println(anguloPelota);
			return false;
		}
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
