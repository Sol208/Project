package com.aroundThirty.View;

import javax.swing.*;
import java.awt.*;

import static com.aroundThirty.Resource.FR.*;

public class MovedBoarderPage extends JFrame implements Runnable {
    JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();

    public MovedBoarderPage() {
        setUndecorated(true);
        try {
//            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(295, 230));
            setTitle(title);

            headerLabel.setFont(fontNanum);
//            headerLabel.setText("Moving");
            contentPane.add(headerLabel, BorderLayout.NORTH);

            imageLabel.setIcon(roadingImg);
            contentPane.add(imageLabel, BorderLayout.CENTER);
            setLocationRelativeTo(null);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        try {
            Thread.sleep(2500);
            movedBoarderPage.setVisible(false);
            movedBoarderPage.dispose();
            JOptionPane.showMessageDialog(null, "완료되었습니다.", title, JOptionPane.PLAIN_MESSAGE);
            dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
