package com.jbatista.batatinha.v2;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisTable;

public class Toolbar {

    private final Chip8Actor chip8Actor;

    private final Table toolbarPadTable = new VisTable(true);

    public Toolbar(Chip8Actor chip8Actor) {
        this.chip8Actor = chip8Actor;
    }

    public Table getTable() {
        return toolbarPadTable;
    }

}
