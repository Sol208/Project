package com.aroundThirty.View;

import javax.swing.*;

import java.util.Random;

import static com.aroundThirty.Resource.FR.title;

public class OpenPage implements Runnable {
    JFrame f;

    public OpenPage() {
        String gifPath = "src/main/java/com.aroundThirty/imgFiles/DogLisa_Loading.gif";
        Icon icon = new ImageIcon(gifPath);
        JLabel label = new JLabel(icon);

        f = new JFrame(title);
        f.setUndecorated(true);
        f.getContentPane().add(label);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            f.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
