package com.aroundThirty.View;

import javax.swing.*;

import java.awt.*;

import static com.aroundThirty.Resource.FR.*;

public class LeftPanel extends JPanel {
    JPanel centerPan;

    public LeftPanel() {

        Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
        insets.bottom = -1;
        insets.left = -1;
        insets.right = -1;
        UIManager.put("TabbedPane.contentBorderInsets", insets);

        tabbed_Pane = new JTabbedPane();
        center_Panel = new CenterPanel();
        main_Right_Panel = new MainRightPanel();
//        switchPan = main_Right_Panel;

        tabbed_Pane.addTab("메인메뉴", center_Panel);
        tabbed_Pane.addTab("발견했어요", report_Page);
        tabbed_Pane.addTab("잃어버렸어요", missing_Page);
        tabbed_Pane.addTab("보호중이에요", temporary_Page);
        tabbed_Pane.addTab("새 가족을 찾아요", adopt_Page);
        tabbed_Pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        tabbed_Pane.setOpaque(true);
        tabbed_Pane.setBackground(pastelYellow);
        tabbed_Pane.setPreferredSize(new Dimension(840, 780));
        tabbed_Pane.setBorder(null);

        centerPan = new JPanel();
        centerPan.add(tabbed_Pane);
        centerPan.setBackground(pastelYellow);
        add(centerPan, BorderLayout.CENTER);
        setBackground(pastelYellow);
    }
}
