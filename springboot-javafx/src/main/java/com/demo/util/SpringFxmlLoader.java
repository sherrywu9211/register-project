package com.demo.util;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringFxmlLoader {

    // 使用 JavaFX 時，FXMLLoader 預設不支援 Spring 的依賴注入。需要用 Spring 來管理 FXMLLoader。
    private final ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context) {
        this.context = context;
    }

    public FXMLLoader load(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(context::getBean); // 使用 Spring 來管理控制器
        return loader;
    }
}
