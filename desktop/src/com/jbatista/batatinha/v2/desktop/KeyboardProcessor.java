package com.jbatista.batatinha.v2.desktop;

import com.badlogic.gdx.InputProcessor;
import com.jbatista.batatinha.v2.Chip8Actor;
import com.jbatista.batatinha.v2.Chip8InputProcessor;

public class KeyboardProcessor implements InputProcessor, Chip8InputProcessor {

    private Chip8Actor chip8Actor;

    public void setChip8Actor(Chip8Actor chip8Actor) {
        this.chip8Actor = chip8Actor;
    }

    @Override
    public boolean keyDown(int i) {
        return true;
    }

    @Override
    public boolean keyUp(int i) {
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
