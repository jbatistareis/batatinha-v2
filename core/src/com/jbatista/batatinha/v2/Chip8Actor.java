package com.jbatista.batatinha.v2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jbatista.batatinha.core.Chip8;
import com.jbatista.batatinha.core.Key;
import java.io.IOException;
import java.io.InputStream;

public class Chip8Actor extends Actor {

    private static final Preferences preferences = Gdx.app.getPreferences("com.jbatista.batatinha.v2");
    private final Sound beep = Gdx.audio.newSound(Gdx.files.internal("beep.wav"));

    private final Chip8 chip8 = new Chip8();
    private final Pixmap pixmap = new Pixmap(128, 64, Pixmap.Format.RGB888);
    private Texture texture;

    private int cpuSpeed = preferences.getInteger("cpu_speed", 500);
    private Color backgroundColor = Color.valueOf(preferences.getString("bg_color", "000000FF"));
    private Color pixelColor = Color.valueOf(preferences.getString("px_color", "FFFFFFFF"));

    private int bufferPosition;
    private int scale = 1;
    private boolean pause = true;

    public Chip8Actor() {
        setSize(128, 64);
    }

    @Override
    public void act(float delta) {
        if (chip8.timerStep()) {
            beep.play();
        }

        if (!pause) {
            for (int i = 0; i < (cpuSpeed * delta); i++) {
                chip8.cpuStep();
            }
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
        batch.setColor(Color.WHITE);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void scaleBy(float scale) {
        setSize(getWidth() * scale, getHeight() * scale);
    }

    @Override
    protected void finalize() throws Throwable {
        texture.dispose();
        beep.dispose();
    }

    public void startProgram(InputStream program) throws IOException {
        chip8.loadProgram(program);
        resetProgram();
    }

    public void resetProgram() {
        pause();
        chip8.reset();
        resume();
    }

    public void togglePause() {
        pause = !pause;
    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }

    public boolean isPaused() {
        return pause;
    }

    public int getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(int cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        preferences.putInteger("cpu_speed", cpuSpeed);
        preferences.flush();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        preferences.putString("bg_color", backgroundColor.toString());
        preferences.flush();
    }

    public Color getPixelColor() {
        return pixelColor;
    }

    public void setPixelColor(Color pixelColor) {
        this.pixelColor = pixelColor;
        preferences.putString("px_color", pixelColor.toString());
        preferences.flush();
    }

    public void pressKey(Key key) {
        chip8.presKey(key);
    }

    public void releaseKey(Key key) {
        chip8.releaseKey(key);
    }

}
