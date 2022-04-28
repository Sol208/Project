package com.aroundThirty;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class Fusioncharts_javafx extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        WebView myWebView = new WebView();
        final WebEngine engine = myWebView.getEngine();



        Button btn1 = new Button("Render Chart from file");
        btn1.setOnAction(new EventHandler < ActionEvent > () {

            @Override
            public void handle(ActionEvent event) {

                File currDir = new File(".");
                String path = currDir.getAbsolutePath();
                path = path.substring(0, path.length() - 1);
                path = path.replaceAll("\\\\", "/");
                // System.out.println(path);


                File file = new File(path + "/src/main/java/org/comstudy21/mygraph/index.html");
                URL url;
                try {
                    url = file.toURI().toURL();
                    engine.load(url.toString());
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Fusioncharts_javafx.class.getName()).log(Level.SEVERE, null, ex);
                }

                // file:/C:/test/a.html

            }
        });

        Button btn2 = new Button("Render Chart from static string");
        btn2.setOnAction(new EventHandler < ActionEvent > () {

            @Override
            public void handle(ActionEvent event) {
                engine.loadContent("\n" +
                    "\n" +
                    "My first chart using FusionCharts Suite XT\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "FusionCharts.ready(function () {\n" +
                    "    var revenueChart = new FusionCharts({\n" +
                    "        type: 'doughnut2d',\n" +
                    "        renderAt: 'chart-container',\n" +
                    "        width: '450',\n" +
                    "        height: '450',\n" +
                    "        dataFormat: 'json',\n" +
                    "        dataSource: {\n" +
                    "            \"chart\": {\n" +
                    "                \"caption\": \"Split of Revenue by Product Categories\",\n" +
                    "                \"subCaption\": \"Last year\",\n" +
                    "                \"numberPrefix\": \"$\",\n" +
                    "                \"paletteColors\": \"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000\",\n" +
                    "                \"bgColor\": \"#ffffff\",\n" +
                    "                \"showBorder\": \"0\",\n" +
                    "                \"use3DLighting\": \"0\",\n" +
                    "                \"showShadow\": \"0\",\n" +
                    "                \"enableSmartLabels\": \"0\",\n" +
                    "                \"startingAngle\": \"310\",\n" +
                    "                \"showLabels\": \"0\",\n" +
                    "                \"showPercentValues\": \"1\",\n" +
                    "                \"showLegend\": \"1\",\n" +
                    "                \"legendShadow\": \"0\",\n" +
                    "                \"legendBorderAlpha\": \"0\",\n" +
                    "                \"defaultCenterLabel\": \"Total revenue: $64.08K\",\n" +
                    "                \"centerLabel\": \"Revenue from $label: $value\",\n" +
                    "                \"centerLabelBold\": \"1\",\n" +
                    "                \"showTooltip\": \"0\",\n" +
                    "                \"decimals\": \"0\",\n" +
                    "                \"captionFontSize\": \"14\",\n" +
                    "                \"subcaptionFontSize\": \"14\",\n" +
                    "                \"subcaptionFontBold\": \"0\"\n" +
                    "            },\n" +
                    "            \"data\": [\n" +
                    "                {\n" +
                    "                    \"label\": \"Food\",\n" +
                    "                    \"value\": \"28504\"\n" +
                    "                }, \n" +
                    "                {\n" +
                    "                    \"label\": \"Apparels\",\n" +
                    "                    \"value\": \"14633\"\n" +
                    "                }, \n" +
                    "                {\n" +
                    "                    \"label\": \"Electronics\",\n" +
                    "                    \"value\": \"10507\"\n" +
                    "                }, \n" +
                    "                {\n" +
                    "                    \"label\": \"Household\",\n" +
                    "                    \"value\": \"4910\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    }).render();\n" +
                    "});\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "  FusionCharts XT will load here!\n" +
                    " Loaded from a string \n" +
                    "\n" +
                    "");
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(myWebView, btn1, btn2);

        Scene scene = new Scene(root, 700, 510);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}