package com.jbatista.batatinha.v2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jbatista.batatinha.core.Chip8;
import com.jbatista.batatinha.core.Key;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chip8Actor extends Actor {

    private final Chip8 chip8 = new Chip8();
    private final Pixmap pixmap = new Pixmap(128, 64, Pixmap.Format.RGB888);
    private int canvasWidth = 128;
    private int canvasHeight = 64;
    private float posX = 0;
    private float posY = 0;
    private Texture texture;

    private int cpuSpeed = 500;
    private Color backgroundColor = Color.BLACK;
    private Color pixelColor = Color.WHITE;

    private int bufferPosition;
    private int scale;

    public Chip8Actor() {
        try {
            startProgram("");
        } catch (IOException ex) {
            Logger.getLogger(Chip8Actor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void act(float delta) {
        if (chip8.timerTick()) {
            // beep!
            // libgdx doesnt play nice with sine waves... T_T
        }

        for (int i = 0; i < (cpuSpeed * delta); i++) {
            chip8.cpuTick();
        }

        // find out ratio of pixels
        scale = (chip8.getDisplayBuffer().length == 2048) ? 2 : 1;

        // fill background
        pixmap.setColor(backgroundColor);
        pixmap.fill();

        // draw pixels
        bufferPosition = 0;
        pixmap.setColor(pixelColor);
        for (int y = 0; y < 64; y += scale) {
            for (int x = 0; x < 128; x += scale) {
                if (chip8.getDisplayBuffer()[bufferPosition++] == 1) {
                    pixmap.fillRectangle(x, y, scale, scale);
                }
            }
        }

        texture = new Texture(pixmap);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, posX, posY, canvasWidth, canvasHeight);
    }

    @Override
    public void scaleBy(float scale) {
        this.canvasWidth *= scale;
        this.canvasHeight *= scale;
    }

    @Override
    public void setPosition(float x, float y) {
        posX = x;
        posY = y;
    }

    @Override
    protected void finalize() throws Throwable {
        pixmap.dispose();
        texture.dispose();
    }

    public void startProgram(String program) throws IOException {
        chip8.loadProgram(new File("D:\\Users\\joao\\Desktop\\CHIP8", "BRIX"));
        resetProgram();
    }

    public void resetProgram() {
        chip8.reset();
    }

    public void changeCpuSpeed(int newSpeed) {
        this.cpuSpeed = newSpeed;
    }

    public void pressKey(Key key) {
        chip8.presKey(key);
    }

    public void releaseKey(Key key) {
        chip8.releaseKey(key);
    }

}
