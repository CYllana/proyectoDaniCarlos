package es.danicarlos.proyecto;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleButton {

    private Sprite skin; 
    //public SimpleButton(Texture texture, float x, float y, float width, float height)
    public SimpleButton(Texture texture, float x, float y) {
      skin = new Sprite(texture); // your image
      skin.setPosition(x, y);
      //skin.setSize(width, height);
    }

    public void update (SpriteBatch batch, float input_x, float input_y) {
        checkIfClicked(input_x, input_y);
        skin.draw(batch); // draw the button
    }

    public boolean checkIfClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                // the button was clicked, perform an action
            	return true;
            }
        }
        return false;
    }

}