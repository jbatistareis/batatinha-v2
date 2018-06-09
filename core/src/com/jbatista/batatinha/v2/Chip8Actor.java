package com.jbatista.batatinha.v2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.io.File;

public class Chip8Actor extends Actor {

    private int canvasWidth = 512;
    private int canvasHeight = 256;
    private final Pixmap pixmap = new Pixmap(128, 64, Pixmap.Format.RGB888);
    private Texture texture;

    private File program = new File("D:\\Users\\joao\\Desktop\\CHIP8", "BLINKY");

    private int bufferPosition;
    private int scale;

    @Override
    public void act(float delta) {
        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        texture = new Texture(pixmap);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, canvasWidth, canvasHeight);
    }

    @Override
    public void scaleBy(float scale) {
        this.canvasWidth *= scale;
        this.canvasHeight *= scale;
    }

    @Override
    protected void finalize() throws Throwable {
        pixmap.dispose();
        texture.dispose();
        super.finalize();
    }

    public void loadProgram(File program) {

    }

    public void pressKey() {

    }

    public void releaseKey() {

    }

}
