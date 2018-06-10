package com.jbatista.batatinha.v2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BatatinhaGame extends ApplicationAdapter {

    private final Chip8InputProcessor inputProcessor = new KeyboardProcessor();
    private Camera camera;
    private Stage stage;
    private Table rootTable;
    private Chip8Actor chip8Actor;

    @Override
    public void create() {
        VisUI.load();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new ScreenViewport(camera));

        chip8Actor = new Chip8Actor();
        inputProcessor.setChip8Actor(chip8Actor);

        rootTable = new VisTable(true);
        rootTable.setFillParent(true);

        rootTable.add(chip8Actor).align(Align.top);
        rootTable.row().fillY();
        rootTable.add(new KeyPad(chip8Actor).getTable()).align(Align.center);
        rootTable.row().fillY();
        rootTable.add(new Toolbar(chip8Actor).getTable()).align(Align.bottom);

        stage.addActor(rootTable);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor((InputProcessor) inputProcessor);
        Gdx.input.setInputProcessor(multiplexer);

        try {
            chip8Actor.startProgram("D:\\Users\\joao\\Desktop\\CHIP8\\BRIX");
        } catch (IOException ex) {
            Logger.getLogger(BatatinhaGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        chip8Actor.scaleBy(Gdx.graphics.getWidth() / chip8Actor.getWidth());
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width / 2, height / 2, 0);
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        VisUI.dispose();
        stage.dispose();
    }
}
