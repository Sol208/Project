package com.aroundThirty.View;

import javax.swing.*;
import java.awt.*;

import static com.aroundThirty.Resource.FR.*;

public class AdoptRightTopPanel extends JPanel {
    JPanel switchPanel;
    JPanel switchPanel2;
    JPanel westPanel;
    JPanel groupPanel;

    public AdoptRightTopPanel() {
        switchPanel = new JPanel(cardLayout);
        switchPanel2 = new JPanel(cardLayout);
        groupPanel = new JPanel();
        westPanel = new JPanel();
        westPanel.add(adopt_AddFile);
        adopt_AddFile.setEnabled(false);
        switchPanel.add("수정", adopt_ModifyBtn);
        switchPanel.add("완료", adopt_PostBtn);
        switchPanel2.add("새 글 작성", adopt_WriteBtn);
        switchPanel2.add("완료", adopt_PostBtn2);
        groupPanel.add(switchPanel);
        groupPanel.add(switchPanel2);
        groupPanel.add(adopt_DeleteBtn);

        setBackground(pastelYellow);
        switchPanel.setBackground(pastelYellow);
        switchPanel2.setBackground(pastelYellow);
        westPanel.setBackground(pastelYellow);
        groupPanel.setBackground(pastelYellow);
        setLayout(new BorderLayout());
        add(BorderLayout.EAST, groupPanel);
        add(BorderLayout.WEST, westPanel);
    }
}