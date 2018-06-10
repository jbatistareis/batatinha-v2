package com.jbatista.batatinha.v2;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jbatista.batatinha.core.Key;

public class KeyboardProcessor implements InputProcessor, Chip8InputProcessor {

    private Chip8Actor chip8Actor;

    public void setChip8Actor(Chip8Actor chip8Actor) {
        this.chip8Actor = chip8Actor;
    }

    @Override
    public boolean keyDown(int i) {
        switch (i) {
            case Input.Keys.NUM_1:
                chip8Actor.pressKey(Key.KEY_1);
                break;
            case Input.Keys.NUM_2:
                chip8Actor.pressKey(Key.KEY_2);
                break;
            case Input.Keys.NUM_3:
                chip8Actor.pressKey(Key.KEY_3);
                break;
            case Input.Keys.NUM_4:
                chip8Actor.pressKey(Key.KEY_C);
                break;
            case Input.Keys.Q:
                chip8Actor.pressKey(Key.KEY_4);
                break;
            case Input.Keys.W:
                chip8Actor.pressKey(Key.KEY_5);
                break;
            case Input.Keys.E:
                chip8Actor.pressKey(Key.KEY_6);
                break;
            case Input.Keys.R:
                chip8Actor.pressKey(Key.KEY_D);
                break;
            case Input.Keys.A:
                chip8Actor.pressKey(Key.KEY_7);
                break;
            case Input.Keys.S:
                chip8Actor.pressKey(Key.KEY_8);
                break;
            case Input.Keys.D:
                chip8Actor.pressKey(Key.KEY_9);
                break;
            case Input.Keys.F:
                chip8Actor.pressKey(Key.KEY_E);
                break;
            case Input.Keys.Z:
                chip8Actor.pressKey(Key.KEY_A);
                break;
            case Input.Keys.X:
                chip8Actor.pressKey(Key.KEY_0);
                break;
            case Input.Keys.C:
                chip8Actor.pressKey(Key.KEY_B);
                break;
            case Input.Keys.V:
                chip8Actor.pressKey(Key.KEY_F);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int i) {
        switch (i) {
            case Input.Keys.NUM_1:
                chip8Actor.releaseKey(Key.KEY_1);
                break;
            case Input.Keys.NUM_2:
                chip8Actor.releaseKey(Key.KEY_2);
                break;
            case Input.Keys.NUM_3:
                chip8Actor.releaseKey(Key.KEY_3);
                break;
            case Input.Keys.NUM_4:
                chip8Actor.releaseKey(Key.KEY_C);
                break;
            case Input.Keys.Q:
                chip8Actor.releaseKey(Key.KEY_4);
                break;
            case Input.Keys.W:
                chip8Actor.releaseKey(Key.KEY_5);
                break;
            case Input.Keys.E:
                chip8Actor.releaseKey(Key.KEY_6);
                break;
            case Input.Keys.R:
                chip8Actor.releaseKey(Key.KEY_D);
                break;
            case Input.Keys.A:
                chip8Actor.releaseKey(Key.KEY_7);
                break;
            case Input.Keys.S:
                chip8Actor.releaseKey(Key.KEY_8);
                break;
            case Input.Keys.D:
                chip8Actor.releaseKey(Key.KEY_9);
                break;
            case Input.Keys.F:
                chip8Actor.releaseKey(Key.KEY_E);
                break;
            case Input.Keys.Z:
                chip8Actor.releaseKey(Key.KEY_A);
                break;
            case Input.Keys.X:
                chip8Actor.releaseKey(Key.KEY_0);
                break;
            case Input.Keys.C:
                chip8Actor.releaseKey(Key.KEY_B);
                break;
            case Input.Keys.V:
                chip8Actor.releaseKey(Key.KEY_F);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return true;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return true;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        return true;
    }

}
