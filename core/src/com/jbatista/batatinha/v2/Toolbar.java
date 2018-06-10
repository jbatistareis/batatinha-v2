package com.jbatista.batatinha.v2;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class Toolbar {

    private final Chip8Actor chip8Actor;
    private final Stage stage;

    private final Table toolbarPadTable = new VisTable(true);
    private final VisTextButton pause = new VisTextButton("Pause");
    private final VisTextButton load = new VisTextButton("Load");
    private final VisTextButton settings = new VisTextButton("Settings");
    private final VisTextButton reset = new VisTextButton("Reset");

    private final VisWindow loadWindow = new VisWindow("Load new program");

    private final VisWindow settingsWindow = new VisWindow("Settings");

    private final VisWindow resetWindow = new VisWindow("Reset?");
    private final Table resetTable = new VisTable(true);
    private final VisTextButton resetYes = new VisTextButton("Yes");
    private final VisTextButton resetNo = new VisTextButton("No");

    private static final int padding = 20;

    public Toolbar(Chip8Actor chip8Actor, Stage stage) {
        this.chip8Actor = chip8Actor;
        this.stage = stage;
    }

    public Table getTable() {
        // <editor-fold defaultstate="collapsed" desc="load window, double click to expand (Netbeans)">
        loadWindow.setModal(true);
        loadWindow.setCenterOnAdd(true);
        loadWindow.setMovable(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="settings window, double click to expand (Netbeans)">
        settingsWindow.setModal(true);
        settingsWindow.setCenterOnAdd(true);
        settingsWindow.setMovable(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="reset window, double click to expand (Netbeans)">
        resetWindow.setModal(true);
        resetWindow.setCenterOnAdd(true);
        resetWindow.setMovable(false);
        resetYes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetWindow.fadeOut(0);
                chip8Actor.resetProgram();
                chip8Actor.togglePause();
            }
        });
        resetNo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetWindow.fadeOut(0);
                chip8Actor.togglePause();
            }
        });
        resetTable.add(resetYes.pad(padding), resetNo.pad(padding));
        resetWindow.add(resetTable);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="buttons, double click to expand (Netbeans)">
        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chip8Actor.togglePause();
            }
        });

        load.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chip8Actor.togglePause();
                stage.addActor(loadWindow.fadeIn(0));
            }
        });

        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chip8Actor.togglePause();
                stage.addActor(settingsWindow.fadeIn(0));
            }
        });

        reset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chip8Actor.togglePause();
                stage.addActor(resetWindow.fadeIn(0));
            }
        });
        // </editor-fold>

        toolbarPadTable.add(pause.pad(padding), load.pad(padding), settings.pad(padding), reset.pad(padding));

        return toolbarPadTable;
    }

}
