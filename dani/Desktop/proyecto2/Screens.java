package es.danicarlos.proyecto2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;

import es.danicarlos.proyecto.AbstractScreen;
import es.danicarlos.proyecto.Juego;

public class Screens {
	public static MainProyecto juego; // Objeto de la clase principal del juego. Nos sirve para cambiar de una pantalla a otra
	public static Juego GAMESCREEN; // Pantalla de juego
	public static AbstractScreen MAINSCREEN; // Pantalla del menú
	public static AbstractScreen SCORESCREEN; // Pantalla del puntuación
	public static AbstractScreen LOGINSCREEN;  // Pantalla del Login
	public static AbstractScreen NEWSCREEN;  // Pantalla del Creacion Usuario
	public static AbstractScreen RECUPERARSCREEN;
}