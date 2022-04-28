package com.aroundThirty.View;

import java.awt.*;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

import static com.aroundThirty.Resource.BR.*;

public class CircleGraphView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public void initAndLoadWebView(JFXPanel pnCenter) {
		Group group = new Group();
		Scene scene = new Scene(group);
		pnCenter.setScene(scene);

		this.add(BorderLayout.CENTER, pnCenter);
		
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("발견했어요", reportListAll.size()),
                new PieChart.Data("잃어버렸어요", missingListAll.size()),
                new PieChart.Data("보호중이에요", temporaryListAll.size()),
                new PieChart.Data("새가족을 찾아요", adoptListAll.size()),
                new PieChart.Data("기타", xmlDtoListAll.size()));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("게시물 정보");
		chart.setLegendSide(Side.LEFT);
		((Group) scene.getRoot()).getChildren().add(chart);

//		final Label caption = new Label("");
//		caption.setFont(fontNanumBold);
//		for (final PieChart.Data data : chart.getData()){
//			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent e){
//					caption.setText(String.valueOf(data.getPieValue()) + "%");
//				}
//			});
//		}
	}
	
	public CircleGraphView() {
		setSize(700, 500);

		JFXPanel pnCenter = new JFXPanel();
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(50);
					initAndLoadWebView(pnCenter);
					CircleGraphView.this.setVisible(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}