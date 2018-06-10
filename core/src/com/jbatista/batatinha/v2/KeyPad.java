package com.jbatista.batatinha.v2;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jbatista.batatinha.core.Key;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class KeyPad {

    private final Chip8Actor chip8Actor;

    private final Table keyPadTable = new VisTable(true);
    private final VisTextButton button0 = new VisTextButton("0");
    private final VisTextButton button1 = new VisTextButton("1");
    private final VisTextButton button2 = new VisTextButton("2");
    private final VisTextButton button3 = new VisTextButton("3");
    private final VisTextButton button4 = new VisTextButton("4");
    private final VisTextButton button5 = new VisTextButton("5");
    private final VisTextButton button6 = new VisTextButton("6");
    private final VisTextButton button7 = new VisTextButton("7");
    private final VisTextButton button8 = new VisTextButton("8");
    private final VisTextButton button9 = new VisTextButton("9");
    private final VisTextButton buttonA = new VisTextButton("A");
    private final VisTextButton buttonB = new VisTextButton("B");
    private final VisTextButton buttonC = new VisTextButton("C");
    private final VisTextButton buttonD = new VisTextButton("D");
    private final VisTextButton buttonE = new VisTextButton("E");
    private final VisTextButton buttonF = new VisTextButton("F");

    private static final int padding = 20;

    public KeyPad(Chip8Actor chip8Actor) {
        this.chip8Actor = chip8Actor;
    }

    public Table getTable() {
        // <editor-fold defaultstate="collapsed" desc="buttom map, double click to expand (Netbeans)">
        button0.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_0);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_0);
            }
        });

        button1.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_1);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_1);
            }
        });

        button2.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_2);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_2);
            }
        });

        button3.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_3);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_3);
            }
        });

        button4.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_4);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_4);
            }
        });

        button5.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_5);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_5);
            }
        });

        button6.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_6);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_6);
            }
        });

        button7.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_7);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_7);
            }
        });

        button8.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_8);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_8);
            }
        });

        button9.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_9);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_9);
            }
        });

        buttonA.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_A);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_A);
            }
        });

        buttonB.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_B);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_B);
            }
        });

        buttonC.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_C);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_C);
            }
        });

        buttonD.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_D);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_D);
            }
        });

        buttonE.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_E);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_E);
            }
        });

        buttonF.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.pressKey(Key.KEY_F);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                chip8Actor.releaseKey(Key.KEY_F);
            }
        });
        // </editor-fold>

        keyPadTable.add(button1.pad(padding), button2.pad(padding), button3.pad(padding), buttonC.pad(padding));
        keyPadTable.row();
        keyPadTable.add(button4.pad(padding), button5.pad(padding), button6.pad(padding), buttonD.pad(padding));
        keyPadTable.row();
        keyPadTable.add(button7.pad(padding), button8.pad(padding), button9.pad(padding), buttonE.pad(padding));
        keyPadTable.row();
        keyPadTable.add(buttonA.pad(padding), button0.pad(padding), buttonB.pad(padding), buttonF.pad(padding));

        return keyPadTable;
    }

}
