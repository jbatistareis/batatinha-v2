package com.jbatista.batatinha.v2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jbatista.batatinha.v2.BatatinhaGame;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Batatinha";
        config.vSyncEnabled = true;
        config.resizable = false;
        config.width = 450;
        config.height = 800;

        new LwjglApplication(new BatatinhaGame(), config);
    }
}
