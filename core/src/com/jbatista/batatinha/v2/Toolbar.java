package com.jbatista.batatinha.v2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.jbatista.batatinha.core.Note;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.adapter.AbstractListAdapter.SelectionMode;
import com.kotcrab.vis.ui.util.adapter.ArrayAdapter;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerAdapter;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;
import java.io.IOException;

public class Toolbar {

    private final Chip8Actor chip8Actor;
    private final Stage stage;

    private final Table toolbarPadTable = new VisTable(true);
    private final VisTextButton pause = new VisTextButton("Pause");
    private final VisTextButton load = new VisTextButton("Load");
    private final VisTextButton settings = new VisTextButton("Settings");
    private final VisTextButton reset = new VisTextButton("Reset");

    private final VisWindow loadWindow = new VisWindow("Load program");
    private final Table loadWindowTabContentTable = new VisTable(true);
    private final TabbedPane loadTabs = new TabbedPane();

    private final VisWindow settingsWindow = new VisWindow("Settings");
    private final Table settingsTable = new VisTable(true);
    private final VisLabel cpuSpeedLabel = new VisLabel("CPU speed:");
    private final VisSelectBox<String> cpuSpeedSelect = new VisSelectBox<>();
    private final VisLabel colorsLabel = new VisLabel("Display colors:");
    private final VisTextButton openBackgroundColorPicker = new VisTextButton("Background");
    private final VisTextButton openPixelColorPicker = new VisTextButton("Pixel");
    private final VisTextButton settingsClose = new VisTextButton("Close");
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
        // tabs
        loadTabs.add(getInternalFilesTab("CHIP8", "chip8"));
        loadTabs.add(getInternalFilesTab("SuperCHIP", "superchip"));

        if (Gdx.files.isExternalStorageAvailable()) {
            loadTabs.add(getExternalFilesTab("SD Card"));
        }

        loadTabs.addListener(new TabbedPaneAdapter() {
            @Override
            public void switchedTab(Tab tab) {
                loadWindowTabContentTable.clearChildren();
                loadWindowTabContentTable.row().expandX().fillX();
                loadWindowTabContentTable.add(tab.getContentTable());
            }
        });
        loadTabs.switchTab(0);

        loadWindow.row().expandX().fillX();
        loadWindow.add(loadTabs.getTabsPane());

        loadWindow.row().expand().fill();
        loadWindow.add(loadWindowTabContentTable);

        loadWindow.setModal(true);
        loadWindow.setFillParent(true);
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
        final String[] notes = new String[Note.values().length];
        for (int i = 0; i < Note.values().length; i++) {
            notes[i] = (Note.values()[i].toString());
        }

        // background color
        backgroundColorPicker = new ColorPicker("Background color", new ColorPickerAdapter() {
            @Override
            public void finished(Color newColor) {
                chip8Actor.setBackgroundColor(newColor);
            }
        });
        backgroundColorPicker.setModal(true);
        backgroundColorPicker.setMovable(false);
        backgroundColorPicker.setAllowAlphaEdit(false);

        openBackgroundColorPicker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsTable.getStage().addActor(backgroundColorPicker.fadeIn());
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
        pixelColorPicker.setMovable(false);
        pixelColorPicker.setAllowAlphaEdit(false);

        openPixelColorPicker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsTable.getStage().addActor(pixelColorPicker.fadeIn());
            }
        });

        // close
        settingsClose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsWindow.fadeOut();
                chip8Actor.resume();
            }
        });

        // table
        settingsTable.row().left();
        settingsTable.add(cpuSpeedLabel, cpuSpeedSelect);

        settingsTable.row().left();
        settingsTable.add(colorsLabel, openBackgroundColorPicker, openPixelColorPicker);

        settingsTable.row().colspan(3).center().pad(padding);
        settingsTable.add(settingsClose);

        settingsWindow.sizeBy(170, 20);
        settingsWindow.setModal(true);
        settingsWindow.setMovable(false);
        settingsWindow.setCenterOnAdd(true);
        settingsWindow.add(settingsTable);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="reset window, double click to expand (Netbeans)">        
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

        resetWindow.setModal(true);
        resetWindow.setCenterOnAdd(true);
        resetWindow.setMovable(false);
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
                stage.addActor(loadWindow.fadeIn());
            }
        });

        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chip8Actor.pause();

                switch (chip8Actor.getCpuSpeed()) {
                    case 500:
                        cpuSpeedSelect.setSelectedIndex(0);
                        break;
                    case 1000:
                        cpuSpeedSelect.setSelectedIndex(1);
                        break;
                    case 2000:
                        cpuSpeedSelect.setSelectedIndex(2);
                        break;
                    case 3680:
                        cpuSpeedSelect.setSelectedIndex(3);
                        break;
                    default:
                        break;
                }
                backgroundColorPicker.setColor(chip8Actor.getBackgroundColor());
                pixelColorPicker.setColor(chip8Actor.getPixelColor());

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

    private Tab getInternalFilesTab(String title, String directory) {
        return createTab(title, Gdx.files.internal(directory).list(".ch8"));
    }

    private Tab getExternalFilesTab(String title) {
        Gdx.files.external("chip8").mkdirs();
        return createTab(title, Gdx.files.external("chip8").list());
    }

    private Tab createTab(String title, FileHandle[] filesList) {
        return new Tab(false, false) {
            @Override
            public String getTabTitle() {
                return title;
            }

            @Override
            public Table getContentTable() {
                final Array<FileHandle> files = new Array<FileHandle>(filesList);
                final ArrayAdapter<FileHandle, VisTable> adapter = new ArrayAdapter<FileHandle, VisTable>(files) {
                    @Override
                    protected VisTable createView(FileHandle item) {
                        final VisTable table = new VisTable(true);
                        table.row().expandX().fillX();
                        table.add(new VisLabel(item.name().replace(".ch8", "")));

                        return table;
                    }

                    @Override
                    protected void selectView(VisTable table) {
                        table.setBackground(VisUI.getSkin().getDrawable("list-selection"));
                    }

                    @Override
                    protected void deselectView(VisTable table) {
                        table.setBackground(VisUI.getSkin().getDrawable("window-bg"));
                    }

                };
                adapter.setSelectionMode(SelectionMode.SINGLE);

                final ListView<FileHandle> listView = new ListView<FileHandle>(adapter);
                final VisTextButton load = new VisTextButton("Load");
                final VisTextButton cancel = new VisTextButton("Cancel");

                load.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        try {
                            chip8Actor.startProgram(adapter.getSelection().get(0).read());
                        } catch (IOException ex) {
                            // some sort feedback
                        } finally {
                            loadWindow.fadeOut();
                        }
                    }
                });
                cancel.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        loadWindow.fadeOut();
                        chip8Actor.resume();
                    }
                });

                listView.getMainTable().row().pad(padding);

                final VisTable footerTable = new VisTable(true);
                if (filesList.length > 0) {
                    footerTable.add(load);
                }
                footerTable.add(cancel);
                listView.getMainTable().add(footerTable);

                return listView.getMainTable();
            }
        };
    }

}
