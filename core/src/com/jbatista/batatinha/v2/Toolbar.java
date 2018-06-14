package com.jbatista.batatinha.v2;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerAdapter;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooser.Mode;
import com.kotcrab.vis.ui.widget.file.SingleFileChooserListener;
import java.io.IOException;

public class Toolbar {

    private final Chip8Actor chip8Actor;
    private final Stage stage;

    private final Table toolbarPadTable = new VisTable(true);
    private final VisTextButton pause = new VisTextButton("Pause");
    private final VisTextButton load = new VisTextButton("Load");
    private final VisTextButton settings = new VisTextButton("Settings");
    private final VisTextButton reset = new VisTextButton("Reset");

    private FileChooser fileChooser = new FileChooser(Mode.OPEN);

    private final VisWindow settingsWindow = new VisWindow("Settings");
    private final Table settingsTable = new VisTable(true);
    private final VisLabel cpuSpeedLabel = new VisLabel("Emulated CPU speed");
    private final VisSelectBox<String> cpuSpeedSelect = new VisSelectBox();
    private final VisLabel beepNoteLabel = new VisLabel("Speaker note");
    private final VisSelectBox<String> beepNoteSelect = new VisSelectBox();
    private final VisLabel colorsLabel = new VisLabel("Display colors");
    private final VisTextButton openBackgroundColorPicker = new VisTextButton("Background");
    private final VisTextButton openPixelColorPicker = new VisTextButton("Pixel");
    private ColorPicker backgroundColorPicker;
    private ColorPicker pixelColorPicker;

    private final VisWindow resetWindow = new VisWindow("Reset?");
    private final VisTable resetTable = new VisTable(true);
    private final VisTextButton resetYes = new VisTextButton("Yes");
    private final VisTextButton resetNo = new VisTextButton("No");

    private static final int padding = 20;

    public Toolbar(Chip8Actor chip8Actor, Stage stage) {
        this.chip8Actor = chip8Actor;
        this.stage = stage;
    }

    public Table getTable() {
        // <editor-fold defaultstate="collapsed" desc="load window, double click to expand (Netbeans)">
        fileChooser.setModal(true);
        fileChooser.setFillParent(true);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.FILES);
        fileChooser.setListener(new SingleFileChooserListener() {
            @Override
            protected void selected(FileHandle fh) {
                try {
                    chip8Actor.startProgram(fh.read());
                } catch (IOException ex) {
                    // some sort feedback
                }
            }

            @Override
            public void canceled() {
                chip8Actor.resume();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="settings window, double click to expand (Netbeans)">
        // cpu
        cpuSpeedSelect.setItems("500 Hz", "1 MHz", "2 MHz", "3.68 MHz");
        cpuSpeedSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                switch (cpuSpeedSelect.getSelectedIndex()) {
                    case 0:
                        chip8Actor.setCpuSpeed(500);
                        break;
                    case 1:
                        chip8Actor.setCpuSpeed(1000);
                        break;
                    case 2:
                        chip8Actor.setCpuSpeed(2000);
                        break;
                    case 3:
                        chip8Actor.setCpuSpeed(3680);
                        break;
                    default:
                        break;
                }
            }
        });

        // speaker
        beepNoteSelect.setItems("B", "A", "C", "D", "E", "F", "G");
        beepNoteSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                switch (beepNoteSelect.getSelectedIndex()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        break;
                }
            }
        });

        // background color
        backgroundColorPicker = new ColorPicker("Background color", new ColorPickerAdapter() {
            @Override
            public void finished(Color newColor) {
                chip8Actor.setBackgroundColor(newColor);
            }
        });
        backgroundColorPicker.setModal(true);
        backgroundColorPicker.setAllowAlphaEdit(false);

        openBackgroundColorPicker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backgroundColorPicker.fadeIn();
            }
        });

        // pixel color
        pixelColorPicker = new ColorPicker("Pixel color", new ColorPickerAdapter() {
            @Override
            public void finished(Color newColor) {
                chip8Actor.setPixelColor(newColor);
            }
        });
        pixelColorPicker.setModal(true);
        pixelColorPicker.setAllowAlphaEdit(false);

        openPixelColorPicker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pixelColorPicker.fadeIn();
            }
        });

        settingsWindow.setModal(true);
        settingsWindow.setFillParent(true);
        settingsWindow.add(settingsTable);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="reset window, double click to expand (Netbeans)">
        resetWindow.setModal(true);
        resetWindow.setCenterOnAdd(true);
        resetWindow.setMovable(false);
        resetYes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetWindow.fadeOut();
                chip8Actor.resetProgram();
            }
        });
        resetNo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetWindow.fadeOut();
                chip8Actor.resume();
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
                chip8Actor.pause();
                stage.addActor(fileChooser.fadeIn());
            }
        });

        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chip8Actor.pause();
                stage.addActor(settingsWindow.fadeIn());
            }
        });

        reset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chip8Actor.pause();
                stage.addActor(resetWindow.fadeIn());
            }
        });
        // </editor-fold>

        toolbarPadTable.add(pause.pad(padding), load.pad(padding), settings.pad(padding), reset.pad(padding));

        return toolbarPadTable;
    }

}
