package com.comze_instancelabs.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class YoutubePlayerTest extends Application {
	public static void main(final String[] args) {
		Application.launch(args);
	}

	public void start(){
		try {
			start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		WebView webview = new WebView();
		webview.getEngine().load("http://www.youtube.com/embed/UXt0AEkQmWU?autoplay=1");
		webview.setPrefSize(640, 390);

		stage.setScene(new Scene(webview));
		stage.show();
	}
}