package com.demo.util;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ImageUtil {

    public static Image base64ToImage(String base64) {
        if (base64 == null || base64.isEmpty()) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        return new Image(new ByteArrayInputStream(decodedBytes));
    }

}
