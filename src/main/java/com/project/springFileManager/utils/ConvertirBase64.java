package com.project.springFileManager.utils;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;

@Service
public class ConvertirBase64 {

    public File retornarImagen(String datosImagen) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(datosImagen);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            BufferedImage imagen = ImageIO.read(bis);
            bis.close();
            File archivo = new File("image.png");
            ImageIO.write(imagen, "png", archivo);
            return archivo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
