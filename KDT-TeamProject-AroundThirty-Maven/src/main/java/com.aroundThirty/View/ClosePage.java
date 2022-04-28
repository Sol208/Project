package com.aroundThirty.View;

import javax.swing.*;

import static com.aroundThirty.Resource.FR.title;

public class ClosePage implements Runnable {

    public ClosePage() {
        String gifPath = "src/main/java/com.aroundThirty/imgFiles/DogSimpsons.gif";
        Icon icon = new ImageIcon(gifPath);
        JLabel label = new JLabel(icon);

        JFrame f = new JFrame(title);
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
            Thread.sleep(1250);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
