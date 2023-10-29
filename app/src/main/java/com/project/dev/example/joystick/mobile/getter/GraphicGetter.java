/*
 * @fileoverview    {GraphicGetter}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.example.joystick.mobile.getter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * TODO: Description of {@code GraphicGetter}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class GraphicGetter {

    private static final String RES_ROOT_PATH = "/assets/com/project/dev/example/joystick/mobile/";

    /**
     * FIXME: Description of {@code getGraphic}. Obtiene un Bitmap desde los recursos de la app.
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
     * FIXME: Description of {@code getGraphic}. Obtiene un Bitmap desde los recursos de la app.
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
