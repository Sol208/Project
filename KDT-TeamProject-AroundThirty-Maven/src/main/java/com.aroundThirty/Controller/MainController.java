package com.aroundThirty.Controller;


import com.aroundThirty.View.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.aroundThirty.Resource.FR.*;

public class MainController {

    public MainController() {
    }

    public void Play(String fileName) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.stop();
            clip.open(ais);
            clip.start();
        } catch (Exception ex) {
        }
    }


    public static void main(String[] args) {
        openPage = new OpenPage();
        Thread thread = new Thread(openPage);
        thread.start();
        new MainView().setVisible(true);
    }
}
