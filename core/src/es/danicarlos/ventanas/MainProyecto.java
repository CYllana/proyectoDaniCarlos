package es.danicarlos.ventanas;


import java.awt.FlowLayout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.danicarlos.multiplayer.MultiplayerGameScreen;
import es.danicarlos.multiplayer.MultiplayerScreen;
import es.danicarlos.multiplayer.StartMultiplayerScreen;
import es.danicarlos.proyecto.Juego;


public class MainProyecto extends Game {
	private static SpriteBatch batch;
	
	@Override
	public void create () {
//		Gdx.input.setCatchBackKey(true); // Bloquea el boton "Back" de android para que se tenga que salir del juego usando el boton "Exit"
		Screens.juego = this;
		Gdx.graphics.setWindowedMode(460, 600);//Tamaño de pantalla
		batch = new SpriteBatch();
		Screens.GAMESCREEN      = new Juego(this); // Se inicializan todas las pantallas
		Screens.MAINSCREEN      = new MainScreen(this);
		Screens.SCORESCREEN     = new ScoreScreen(this);
		Screens.MULTIPLAYERSCREEN  = new MultiplayerGameScreen(this);
		Screens.NEWSCREEN       = new NewUserScreen(this);
		Screens.RECUPERARSCREEN = new RecuperarScreen(this);
		Screens.LOGINSCREEN     = new UsersScreen(this);
		//setScreen(Screens.GAMESCREEN);
		setScreen(Screens.LOGINSCREEN);
		
	}
	
	

	public static  SpriteBatch getbatch(){
		return batch;
	}

}
