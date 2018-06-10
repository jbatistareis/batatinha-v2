package com.jbatista.batatinha.v2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BatatinhaGame extends ApplicationAdapter {

    private final Chip8InputProcessor inputProcessor = new KeyboardProcessor();
    private ScreenViewport viewport;
    private Stage stage;
    private Chip8Actor chip8Actor;

    @Override
    public void create() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        chip8Actor = new Chip8Actor();

        inputProcessor.setChip8Actor(chip8Actor);
        Gdx.input.setInputProcessor((InputProcessor) inputProcessor);
        stage.addActor(chip8Actor);
        stage.setKeyboardFocus(chip8Actor);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
