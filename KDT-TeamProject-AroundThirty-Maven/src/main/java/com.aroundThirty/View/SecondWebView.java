package com.aroundThirty.View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;

import javax.swing.*;
import java.awt.*;

public class SecondWebView extends JFrame {
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

		webEngine.load("https://colab.research.google.com/drive/1JwFjhDmUkMnwDhcbM50D8MmSgTGpU9Dg#scrollTo=Xl2tSb_EDoYK");
	}

	public SecondWebView() {
		setSize(800, 600);

		JFXPanel pnCenter = new JFXPanel();
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(50);
					initAndLoadWebView(pnCenter);
					SecondWebView.this.setVisible(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}