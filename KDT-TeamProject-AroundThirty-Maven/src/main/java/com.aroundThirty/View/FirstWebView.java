package com.aroundThirty.View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;

import javax.swing.*;
import java.awt.*;

public class FirstWebView extends JFrame {
	private static final long serialVersionUID = 1L;

	public void initAndLoadWebView(JFXPanel pnCenter) {
		Group group = new Group();
		Scene scene = new Scene(group);
		pnCenter.setScene(scene);

		javafx.scene.web.WebView webView = new javafx.scene.web.WebView();

		this.add(BorderLayout.CENTER, pnCenter);

		group.getChildren().add(webView);
		webView.setMinSize(500, 500);
		webView.setMaxSize(690, 500);

		WebEngine webEngine = webView.getEngine();
		webEngine.setJavaScriptEnabled(true);

		webEngine.load("https://colab.research.google.com/drive/1JXcL7Ai4YjcK3x9_P7dhp4VTBlmo16Lp#scrollTo=mK2WzDvOxyFX");
	}

	public FirstWebView() {
		setSize(800, 600);

		JFXPanel pnCenter = new JFXPanel();
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(50);
					initAndLoadWebView(pnCenter);
					FirstWebView.this.setVisible(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}