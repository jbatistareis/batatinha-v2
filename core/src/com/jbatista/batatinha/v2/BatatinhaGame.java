package com.jbatista.batatinha.v2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.file.FileChooser;

public class BatatinhaGame extends ApplicationAdapter {

    private final Chip8InputProcessor inputProcessor = new KeyboardProcessor();
    private Camera camera;
    private Stage stage;
    private VisTable rootTable;
    private Chip8Actor chip8Actor;

    private static final int padding = 25;

    @Override
    public void create() {
        VisUI.load();
        FileChooser.setDefaultPrefsName("com.jbatista.batatinha.v2.filechooser");

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new ScreenViewport(camera));

        chip8Actor = new Chip8Actor();
        inputProcessor.setChip8Actor(chip8Actor);

        rootTable = new VisTable(true);
        rootTable.setFillParent(true);

        rootTable.row().center().top().pad(padding);
        rootTable.add(chip8Actor);

        rootTable.row().center().pad(padding);
        rootTable.add(new KeyPad(chip8Actor).getTable());

        rootTable.row().center().bottom().pad(padding);
        rootTable.add(new Toolbar(chip8Actor, stage).getTable());

        stage.addActor(rootTable);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor((InputProcessor) inputProcessor);
        Gdx.input.setInputProcessor(multiplexer);
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
