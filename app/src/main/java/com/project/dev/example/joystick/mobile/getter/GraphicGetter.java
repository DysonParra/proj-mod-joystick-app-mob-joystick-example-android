/*
 * @fileoverview {GraphicGetter} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {GraphicGetter} fue realizada el 31/07/2022.
 * @Dev - La primera version de {GraphicGetter} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.example.joystick.mobile.getter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * TODO: Definición de {@code GraphicGetter}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class GraphicGetter {

    private static final String RES_ROOT_PATH = "/assets/com/project/dev/example/joystick/mobile/";

    /**
     * FIXME: Definición de {@code getGraphic}. Obtiene un Bitmap desde los recursos de la app.
     *
     * @param fileName es el nombre de la imagen.
     * @return un Bitmap obtenido desde el archivo indicado.
     */
    public static Bitmap getGraphic(String fileName) {
        try {
            InputStream assetInStream = (GraphicGetter.class.getResource(RES_ROOT_PATH + fileName)).openStream();
            return BitmapFactory.decodeStream(assetInStream);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * FIXME: Definición de {@code getGraphic}. Obtiene un Bitmap desde los recursos de la app.
     *
     * @param fileName       es el nombre de la imagen.
     * @param windowWidth    es el ancho máximo de la ventana donde se moverá el componente gráfico.
     * @param windowHeight   Es el alto máximo de la ventana donde se moverá el componente gráfico.
     * @param windowDivision Indica en cuanto se dividirá la ventana (cuantas imagenes cabrán a lo
     *                       alto).
     * @return un Bitmap obtenido desde el archivo indicado.
     */
    public static Bitmap getGraphic(String fileName, int windowWidth, int windowHeight, int windowDivision) {
        try {
            InputStream assetInStream = (GraphicGetter.class.getResource(RES_ROOT_PATH + fileName)).openStream();
            Bitmap bit = BitmapFactory.decodeStream(assetInStream);

            int width = bit.getWidth();
            int height = bit.getHeight();
            double percent = (windowHeight / (double) windowDivision) / height;

            if (windowHeight == 0)
                return bit;
            else
                return Bitmap.createScaledBitmap(bit, (int) (width * percent), (int) (height * percent), false);
        } catch (Exception e) {
            return null;
        }
    }
}
