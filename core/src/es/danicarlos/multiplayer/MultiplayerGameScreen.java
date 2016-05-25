/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package es.danicarlos.multiplayer;

import java.util.ArrayList;

import org.json.JSONObject;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import es.danicarlos.proyecto.BotonExitJuego;
import es.danicarlos.proyecto.BotonGirar;
import es.danicarlos.proyecto.BotonPause;
import es.danicarlos.proyecto.BotonRestart;
import es.danicarlos.proyecto.Pelota;
import es.danicarlos.proyecto.Punto;
import es.danicarlos.proyecto.Rueda;
import es.danicarlos.ventanas.AbstractScreen;
import es.danicarlos.ventanas.MainProyecto;

public class MultiplayerGameScreen extends AbstractScreen implements  WarpListener {
	public MultiplayerGameScreen(MainProyecto main2) {
		super(main2);
		// TODO Auto-generated constructor stub
	}

	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	private final static int WINDOW_WIDTH = 500;
	private final static int WINDOW_HEIGHT	 = 500;
	private SpriteBatch batch2;
	private Texture imgTexture, pelota, bIzq,bDer, punto, fondo,bPause, bRStart, bExit;
	private int  height, width, xCirc, yCirc, xPelota, yPelota;
	private ShapeRenderer figurator;
	private ArrayList<Vector2> misPuntos;
	private BitmapFont font,font2, puntuacion;
	private Sprite img,pelotaSprite, point, flechaIz,flechaDer;
	private Pelota miPelota;
	private Rueda miRueda;
	private Punto puntos;
	private int estrellas, tiempoIncr;
	private float scale;
	private boolean choqueBola;
	private Color[] colores={Color.YELLOW, Color.GREEN, Color.RED, Color.BLACK,Color.BLUE, Color.ORANGE};
	private Color[] siguienteColor;
	private MainProyecto miMP;
	private boolean paso;
	//private SimpleButton botonIzq, botonDer;
	private BotonGirar btIzq, btDer;
	private float  totalTime =30;
	private BotonPause btPause;
	private BotonRestart btRstart;
	private BotonExitJuego btExit;
	Game game;

	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	SpriteBatch batcher;
	World world;
	//WorldListener worldListener;
	//WorldRenderer renderer;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	int lastScore;
	String scoreString;
	private StartMultiplayerScreen prevScreen;

	public void  show(){
		this.game = game;
		this.prevScreen = prevScreen;
		state = GAME_RUNNING;
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		Gdx.graphics.setWindowedMode(460,600);
		batch2= MainProyecto.getbatch();
		font=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		font2=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);
		puntuacion=new BitmapFont(Gdx.files.internal("comic.fnt"),Gdx.files.internal("comic.png"),false);

		
		
		
		switch(Gdx.app.getType()) {
		case Android:
			// android specific code
			font2.getData().setScale( 0.7f,0.7f);
			font.getData().setScale( 1.0f,1.0f);

			break;
		case Desktop:
			// desktop specific code
			font2.getData().setScale( 0.4f,0.4f);
			font.getData().setScale( 1.0f,1.0f);
			System.out.println("desk");
			break;
		default:
			font2.getData().setScale( 0.7f,0.7f);
			font.getData().setScale( 1.0f,1.0f);

			break;
		}



		estrellas=0;
		height=Gdx.graphics.getHeight();
		width=Gdx.graphics.getWidth();

		choqueBola=false;
		scale=height/width;
		imgTexture = new Texture("miJuegoMenu.png");
		fondo= new Texture("fondo.jpg");
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
		//Inicializamos imagenes de los botones
		bIzq=new Texture("flachaIzq.png");
		bDer=new Texture("flachaDer.png");
		bPause=new Texture("pause.png");
		bRStart=new Texture("playJuego.png");
		punto =new Texture("punto.png");
		bExit=new Texture("salirColor.png");
		//Botones

		btPause=new BotonPause(30, height-70);
		btIzq=new BotonGirar(bIzq, (-width/16)+width/8  ,width/12); 
		btDer=new BotonGirar(bDer,-width/8+width-width/16, width/12);
		btRstart=new BotonRestart(-width/20-bRStart.getWidth()+width/2 ,(-bRStart.getHeight()+height)/2);
		btExit=new BotonExitJuego(+width/20+width/2,(-bExit.getHeight()+height)/2);
		//btIzq=new BotonGirar(bIzq, -(bIzq.getWidth()/2)+(width/7),width/12); 
		//btDer=new BotonGirar(bDer,width-((bIzq.getWidth()/2)+(width/7)), width/12);
		//miRueda=new Rueda (img, width*19/43, width/2,height/2,this);

		//miPelota=new Pelota(pelotaSprite,width/2, height/2,this);

		//puntos=new Punto(point,this);

		//Tiempo
		totalTime=0;
		state=1;
		tiempoIncr=0;
		/*worldListener = new WorldListener() {
			@Override
			public void jump () {
				Assets.playSound(Assets.jumpSound);
			}

			@Override
			public void highJump () {
				Assets.playSound(Assets.highJumpSound);
			}

			@Override
			public void hit () {
				Assets.playSound(Assets.hitSound);
			}

			@Override
			public void coin () {
				Assets.playSound(Assets.coinSound);
			}
		};
		
		world = new World(worldListener);
		renderer = new WorldRenderer(batcher, world);
		*/
		pauseBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
		resumeBounds = new Rectangle(160 - 96, 240, 192, 36);
		quitBounds = new Rectangle(160 - 96, 240 - 36, 192, 36);
		lastScore = 0;
		scoreString = "SCORE: 0";
		WarpController.getInstance().setListener(this);
	}

	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void updateReady () {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}
	}

	private void updateRunning (float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			/*if (pauseBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
//				state = GAME_PAUSED;
				game.setScreen(new MainMenuScreen(game));
				handleLeaveGame();
				
				return;
				
			}*/
		}
		
		ApplicationType appType = Gdx.app.getType();
		
		// should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
		if (appType == ApplicationType.Android || appType == ApplicationType.iOS) {
			//world.update(deltaTime, Gdx.input.getAccelerometerX());
		} else {
			float accel = 0;
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) accel = 5f;
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) accel = -5f;
			//world.update(deltaTime, accel);
		}
		/*if (world.score != lastScore) {
			lastScore = world.score;
			scoreString = "SCORE: " + lastScore;
		}
		if (world.state == World.WORLD_STATE_NEXT_LEVEL) {
			state = GAME_LEVEL_END;
		}
		if (world.state == World.WORLD_STATE_GAME_OVER) {
			state = GAME_OVER;
			if (lastScore >= Settings.highscores[4])
				scoreString = "NEW HIGHSCORE: " + lastScore;
			else
				scoreString = "SCORE: " + lastScore;
			Settings.addScore(lastScore);
			Settings.save();
		}
		*/
	}

	private void updatePaused () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (resumeBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				state = GAME_RUNNING;
				return;
			}

			if (quitBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				//game.setScreen(new MainMenu(game));
				return;
			}
		}
	}



	private void updateGameOver () {
		if (Gdx.input.justTouched()) {
			WarpController.getInstance().handleLeave();
			//game.setScreen(new MainMenuScreen(game));
		}
	}

	public void draw () {
		//Pintamos el fondo
				Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

				//Draws
				batch2.begin();
				//Imagen de fondo
				batch2.draw(fondo, 0, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				img.draw(batch2);
				//Puntuación
				batch2.end();
				figurator.begin(ShapeType.Line.Filled);
				figurator.setColor(Color.BLACK); 
				figurator.circle( width/2, height/2, width*19/43 );
				figurator.end();
	}

	private void presentReady () {
		//batcher.draw(Assets.ready, 160 - 192 / 2, 240 - 32 / 2, 192, 32);
	}

	private void presentRunning () {
		/*batcher.draw(Assets.arrow, 320 - 64, 480 - 64, 64, 64);
		Assets.font.draw(batcher, scoreString, 16, 480 - 20);*/
	}

	private void presentPaused () {
		//batcher.draw(Assets.pauseMenu, 160 - 192 / 2, 240 - 96 / 2, 192, 96);
		//Assets.font.draw(batcher, scoreString, 16, 480 - 20);
	}



	private void presentGameOver () {
		//batcher.draw(Assets.gameOver, 160 - 160 / 2, 240 - 96 / 2, 160, 96);
		//float scoreWidth = Assets.font.getBounds(scoreString).width;
		//Assets.font.draw(batcher, scoreString, 160 - scoreWidth / 2, 480 - 20);
	}

	@Override
	public void render (float delta) {
		update(delta);
		draw();
	}

	@Override
	public void resize (int width, int height) {
	}


	@Override
	public void hide () {
	}

	@Override
	public void pause () {
//		if (state == GAME_RUNNING) state = GAME_PAUSED;
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
		
	}
	private void handleLeaveGame(){
		WarpController.getInstance().handleLeave();
	}

	@Override
	public void onWaitingStarted (String message) {
		
	}

	@Override
	public void onError (String message) {
		
	}

	@Override
	public void onGameStarted (String message) {
		
	}

	@Override
	public void onGameFinished (int code, boolean isRemote) {
		if(isRemote){
			prevScreen.onGameFinished(code, true);
		}else{
			if(code==WarpController.GAME_WIN){
				//world.state = World.WORLD_STATE_NEXT_LEVEL;
			}else if(code==WarpController.GAME_LOOSE){
				//world.state = World.WORLD_STATE_GAME_OVER;
			}
		}
		WarpController.getInstance().handleLeave();
	}

	@Override
	public void onGameUpdateReceived (String message) {
		try {
			JSONObject data = new JSONObject(message);
			float x = (float)data.getDouble("x");
			float y = (float)data.getDouble("y");
			float width = (float)data.getDouble("width");
			float height = (float)data.getDouble("height");
			//renderer.updateEnemyLocation(x, y, width, height);
		} catch (Exception e) {
			// exception in onMoveNotificationReceived
		}
	}
}
