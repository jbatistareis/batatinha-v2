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

    // <editor-fold defaultstate="collapsed" desc="hardcoded games list, double click to expand (Netbeans)">
    private static final String[] chip8Games = {
        "15 Puzzle [Roger Ivie] (alt).ch8",
        "15 Puzzle [Roger Ivie].ch8",
        "Addition Problems [Paul C. Moews].ch8",
        "Airplane.ch8",
        "Animal Race [Brian Astle].ch8",
        "Astro Dodge [Revival Studios, 2008].ch8",
        "Biorhythm [Jef Winsor].ch8",
        "Blinky [Hans Christian Egeberg, 1991].ch8",
        "Blinky [Hans Christian Egeberg] (alt).ch8",
        "Blitz [David Winter].ch8",
        "Bowling [Gooitzen van der Wal].ch8",
        "Breakout (Brix hack) [David Winter, 1997].ch8",
        "Breakout [Carmelo Cortez, 1979].ch8",
        "Brick (Brix hack, 1990).ch8",
        "Brix [Andreas Gustafsson, 1990].ch8",
        "Cave.ch8",
        "Coin Flipping [Carmelo Cortez, 1978].ch8",
        "Connect 4 [David Winter].ch8",
        "Craps [Camerlo Cortez, 1978].ch8",
        "Deflection [John Fort].ch8",
        "Figures.ch8",
        "Filter.ch8",
        "Guess [David Winter] (alt).ch8",
        "Guess [David Winter].ch8",
        "Hi-Lo [Jef Winsor, 1978].ch8",
        "Hidden [David Winter, 1996].ch8",
        "Kaleidoscope [Joseph Weisbecker, 1978].ch8",
        "Landing.ch8",
        "Lunar Lander (Udo Pernisz, 1979).ch8",
        "Mastermind FourRow (Robert Lindley, 1978).ch8",
        "Maze (alt) [David Winter, 199x].ch8",
        "Maze [David Winter, 199x].ch8",
        "Merlin [David Winter].ch8",
        "Missile [David Winter].ch8",
        "Most Dangerous Game [Peter Maruhnic].ch8",
        "Nim [Carmelo Cortez, 1978].ch8",
        "Paddles.ch8",
        "Particle Demo [zeroZshadow, 2008].ch8",
        "Pong (1 player).ch8",
        "Pong (alt).ch8",
        "Pong 2 (Pong hack) [David Winter, 1997].ch8",
        "Pong [Paul Vervalin, 1990].ch8",
        "Programmable Spacefighters [Jef Winsor].ch8",
        "Puzzle.ch8",
        "Reversi [Philip Baltzer].ch8",
        "Rocket Launch [Jonas Lindstedt].ch8",
        "Rocket Launcher.ch8",
        "Rocket [Joseph Weisbecker, 1978].ch8",
        "Rush Hour [Hap, 2006] (alt).ch8",
        "Rush Hour [Hap, 2006].ch8",
        "Russian Roulette [Carmelo Cortez, 1978].ch8",
        "Sequence Shoot [Joyce Weisbecker].ch8",
        "Shooting Stars [Philip Baltzer, 1978].ch8",
        "Sirpinski [Sergey Naydenov, 2010].ch8",
        "Slide [Joyce Weisbecker].ch8",
        "Soccer.ch8",
        "Space Flight.ch8",
        "Space Intercept [Joseph Weisbecker, 1978].ch8",
        "Space Invaders [David Winter] (alt).ch8",
        "Space Invaders [David Winter].ch8",
        "Spooky Spot [Joseph Weisbecker, 1978].ch8",
        "Squash [David Winter].ch8",
        "Submarine [Carmelo Cortez, 1978].ch8",
        "Sum Fun [Joyce Weisbecker].ch8",
        "Syzygy [Roy Trevino, 1990].ch8",
        "Tank.ch8",
        "Tapeworm [JDR, 1999].ch8",
        "Tetris [Fran Dachille, 1991].ch8",
        "Tic-Tac-Toe [David Winter].ch8",
        "Timebomb.ch8",
        "Trip8 Demo (2008) [Revival Studios].ch8",
        "Tron.ch8",
        "UFO [Lutz V, 1992].ch8",
        "Vers [JMN, 1991].ch8",
        "Vertical Brix [Paul Robson, 1996].ch8",
        "Wall [David Winter].ch8",
        "Wipe Off [Joseph Weisbecker].ch8",
        "Worm V4 [RB-Revival Studios, 2007].ch8",
        "X-Mirror.ch8",
        "Zero Demo [zeroZshadow, 2007].ch8",
        "ZeroPong [zeroZshadow, 2007].ch8"};

    private static final String[] superChipGames = {
        "Alien [Jonas Lindstedt, 1993].ch8",
        "Ant - In Search of Coke [Erin S. Catto].ch8",
        "Blinky [Hans Christian Egeberg, 1991].ch8",
        "Bounce [Les Harris].ch8",
        "Car Race Demo [Erik Bryntse, 1991].ch8",
        "Car [Klaus von Sengbusch, 1994].ch8",
        "Climax Slideshow - Part 1 [Revival Studios, 2008].ch8",
        "Climax Slideshow - Part 2 [Revival Studios, 2008].ch8",
        "Field! [Al Roland, 1993] (alt).ch8",
        "Field! [Al Roland, 1993].ch8",
        "H. Piper [Paul Raines, 1991].ch8",
        "Joust [Erin S. Catto, 1993].ch8",
        "Laser.ch8",
        "Loopz (with difficulty select) [Hap, 2006].ch8",
        "Loopz [Andreas Daumann].ch8",
        "Magic Square [David Winter, 1997].ch8",
        "Matches.ch8",
        "Mines! - The minehunter [David Winter, 1997].ch8",
        "Robot.ch8",
        "SCSerpinski [Sergey Naydenov, 2010].ch8",
        "SCStars  [Sergey Naydenov, 2010].ch8",
        "Single Dragon (Bomber Section) [David Nurser, 1993].ch8",
        "Single Dragon (Stages 1-2) [David Nurser, 1993].ch8",
        "Sokoban [Hap, 2006] (alt).ch8",
        "Sokoban [Hap, 2006].ch8",
        "Spacefight 2091 [Carsten Soerensen, 1992].ch8",
        "Super Astro Dodge [Revival Studios, 2008].ch8",
        "Super Particle Demo [zeroZshadow, 2008].ch8",
        "SuperMaze [David Winter, 199x].ch8",
        "SuperTrip8 Demo (2008) [Revival Studios].ch8",
        "SuperWorm V3 [RB, 1992].ch8",
        "SuperWorm V4 [RB-Revival Studios, 2007].ch8",
        "U-Boat [Michael Kemper, 1994].ch8",
        "Worms demo.ch8"
    };
    // </editor-fold>

    private static final int padding = 20;

    public Toolbar(Chip8Actor chip8Actor, Stage stage) {
        this.chip8Actor = chip8Actor;
        this.stage = stage;
    }

    public Table getTable() {
        // <editor-fold defaultstate="collapsed" desc="load window, double click to expand (Netbeans)">
        // tabs
        loadTabs.add(getInternalFilesTab("CHIP8", "chip8", chip8Games));
        loadTabs.add(getInternalFilesTab("SuperCHIP", "superchip", superChipGames));

        if (Gdx.files.isExternalStorageAvailable()) {
            loadTabs.add(getExternalFilesTab("SD Card"));
        }

        loadTabs.addListener(new TabbedPaneAdapter() {
            @Override
            public void switchedTab(Tab tab) {
                loadWindowTabContentTable.clearChildren();
                loadWindowTabContentTable.row().expand().fill();
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

    private Tab getInternalFilesTab(String title, String directory, String[] files) {
        return createTab(title, directory, files, true);
    }

    private Tab getExternalFilesTab(String title) {
        Gdx.files.external("chip8").mkdirs();

        final FileHandle[] filesList = Gdx.files.external("chip8").list(".ch8");
        final String[] fileNames = new String[filesList.length];
        for (int i = 0; i < filesList.length; i++) {
            fileNames[i] = filesList[i].name();
        }

        return createTab(title, "chip8", fileNames, false);
    }

    private Tab createTab(String title, String directory, String[] filesList, boolean internal) {
        return new Tab(false, false) {
            @Override
            public String getTabTitle() {
                return title;
            }

            @Override
            public Table getContentTable() {
                final Array<String> files = new Array<String>(filesList);
                final ArrayAdapter<String, VisTable> adapter = new ArrayAdapter<String, VisTable>(files) {
                    @Override
                    protected VisTable createView(String item) {
                        final VisTable table = new VisTable(true);
                        table.row().expandX().fillX();
                        table.add(new VisLabel(item.replace(".ch8", "")));

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

                final ListView<String> listView = new ListView<String>(adapter);
                final VisTextButton load = new VisTextButton("Load");
                final VisTextButton cancel = new VisTextButton("Cancel");

                load.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        try {
                            chip8Actor.startProgram(internal
                                    ? Gdx.files.internal(directory + "/" + adapter.getSelection().get(0)).read()
                                    : Gdx.files.external(directory + "/" + adapter.getSelection().get(0)).read());
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
