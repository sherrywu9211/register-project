package com.demo;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJavafxApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApp.class, args); // 啟動 JavaFX
	}

}
