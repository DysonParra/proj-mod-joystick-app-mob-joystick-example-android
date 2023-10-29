/*
 * @fileoverview    {ExampleActivity}
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
package com.project.dev.example.joystick.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.project.dev.joystick.factory.JoystickFactory;
import com.project.dev.joystick.listener.JoystickServerListener;
import com.project.dev.joystick.name.generic.type.GenericJoystickServer;
import com.project.dev.joystick.name.nintendo.NintendoJoystick;
import com.project.dev.joystick.name.poly.PolyJoystick;
import com.project.dev.joystick.setter.GenericJoystickPrintActionSetter;

import com.project.dev.example.joystick.mobile.R;

import com.project.dev.example.joystick.mobile.getter.GraphicGetter;
import com.project.dev.example.joystick.mobile.setter.GenericJoystickComponentActionSetter;
import com.project.dev.example.joystick.mobile.setter.NintendoJoystickComponentActionSetter;
import com.project.dev.example.joystick.mobile.setter.PolyJoystickComponentActionSetter;

/**
 * TODO: Definición de {@code ExampleActivity}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class ExampleActivity extends Activity {

    /*
     * Variables asociadas con elementos la vista.
     */
    private LinearLayout mainLayout;
    private LinearLayout connectionInfoLayout;
    private LinearLayout workSpaceLayout;

    /* Ancho de la ventana. */
    private int width = 0;
    /* Alto de la ventana. */
    private int height = 0;

    /*
     * Variables locales.
     */
    private String joystickType = "";                                   // Indica el tipo de joystick a utilizar.
    private String joystickName = "";                                   // Indica el nombre del joystick a utilizar.
    private GenericJoystickServer server = null;                        // Indica el joystick asociado a la ventana.
    private final ExampleActivity window = this;                        // Referencia a la ventana.
    private ImageView player;                                           // Es el jugador.
    private final int playerMoveQuantity = 6;                           // Cantidad de veces que cabrá el jugador a lo alto de la ventana.
    private Intent mainActivity;                                        // Crea intent para iniciar el activity principal.

    /**
     * Invocado cuando se crea el activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Crea instancia del activity y la asocia con la vista.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Asocia variables locales con elementos de la vista.
        mainLayout = findViewById(R.id.mainLayout);
        connectionInfoLayout = findViewById(R.id.connectionInfoLayout);
        workSpaceLayout = findViewById(R.id.workSpaceLayout);

        // Obtiene los elementos enviados desde el activity anterior y los asigna a variables locales.
        if (getIntent().getExtras() != null) {
            joystickType = (String) getIntent().getExtras().getSerializable("joystickType");
            joystickName = (String) getIntent().getExtras().getSerializable("joystickName");
        }

        // Se indica a StrictMode que en su política deathreads no tenga en cuenta los accesos a la red.
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        // Asigna valores a variables locales.
        mainActivity = new Intent(ExampleActivity.this, MainActivity.class);       // Inicializa el intent para iniciar el activity principal.
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);   // Agrega banderas indicando que se cerrará toda la pila de actividades.

        // Obtiene el alto en píxeles de la ventana.
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        mainLayout.post(new Runnable() {
            public void run() {
                width = mainLayout.getWidth();
                height = mainLayout.getHeight();
                startActivity();
            }
        });
    }

    /**
     * FIXME: Definición de {@code startActivity}. Inicializa el joystick con la información
     * obtenida del activity anterior.
     */
    private void startActivity() {
        try {
            // Obtiene un joystick servidor del nombre indicado en el frame anterior usando la fábrica de joystick.
            server = (GenericJoystickServer) JoystickFactory.makeJoystick(joystickType, joystickName, null, 0);

            // Agrega acciones de impresión en pantalla a cada botón del joystick.
            GenericJoystickPrintActionSetter printActionSetter = new GenericJoystickPrintActionSetter();
            printActionSetter.addButtonPrintActions(server);

            // Asigna textView que dice la dirección ip y puerto del servidor.
            TextView joystickInfo = new TextView(this);
            joystickInfo.setTextSize((float) (width / 100));
            LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(
                    width, (int) (height + (joystickInfo.getTextSize() * 2)));
            joystickInfo.setGravity(Gravity.BOTTOM);
            joystickInfo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            joystickInfo.setLayoutParams(infoParams);
            connectionInfoLayout.addView(joystickInfo);
            String title = this.getString(R.string.txtJoystickInfoServer)
                    + "   IP: " + getIpAddress()
                    + "   "
                    + this.getString(R.string.txtJoystickInfoPort)
                    + ": "
                    + server.getServerPort();
            joystickInfo.setText(title);

            // Agrega mensajes que informan cuando un cliente se conecte y cuando uno se desconecte.
            server.setOnJoystickServerListener(new JoystickServerListener() {
                @Override
                public void onClientConnected() {
                    //Toast.makeText(ExampleActivity.this, ExampleActivity.this.getString(R.string.txtClientConnected), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onClientDisconnected() {
                    //Toast.makeText(ExampleActivity.this, ExampleActivity.this.getString(R.string.txtClientDisconnected), Toast.LENGTH_SHORT).show();
                }
            });

            // Crea un componente que tendrá las imágenes, y lo agrega a la ventana.
            player = new ImageView(this);
            player.setAdjustViewBounds(true);
            Bitmap bit = GraphicGetter.getGraphic("hand_01.png", width, height, playerMoveQuantity);
            player.setImageBitmap(bit);
            if (bit != null) {
                LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(bit.getWidth(), bit.getHeight());
                //viewParams.topMargin  = (height / 2) - (bit.getHeight() / 2);
                //viewParams.leftMargin = (width  / 2) - (bit.getWidth()  / 2);
                player.setLayoutParams(viewParams);
            }
            workSpaceLayout.addView(player);

            // Asigna acciones para controlar el jugador y la ventana dependiendo del nombre del joystick.
            GenericJoystickComponentActionSetter componentActionSetter;

            switch (joystickName) {
                case NintendoJoystick.JOYSTICK_NAME:
                    componentActionSetter = new NintendoJoystickComponentActionSetter(player, window, width, height, playerMoveQuantity);
                    componentActionSetter.setButtonActions(server);
                    break;

                case PolyJoystick.JOYSTICK_NAME:
                    componentActionSetter = new PolyJoystickComponentActionSetter(player, window, width, height, playerMoveQuantity);
                    componentActionSetter.setButtonActions(server);
                    break;
            }
        } catch (Exception ex) {
            startActivity(mainActivity);
        }
    }

    /**
     * FIXME: Definición de {@code onKeyDown}. Comportamiento del botón atrás.Indica que cuando se
     * cierre la ventana se parará el servidor del joystick.
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Si el botón es el de atrás.
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Detiene el servidor del joystick.
            server.stopServer();

            // Inicia el activity principal.
            startActivity(mainActivity);
        }

        return true;
    }

    /**
     * FIXME: Definición de {@code getIpAddress}. Obtiene la dirección ip del dispositivo.
     *
     * @return
     */
    private String getIpAddress() {
        StringBuilder ip = new StringBuilder();

        try {

            int ipQuantity = 0;
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces.nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();

                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ipQuantity++;
                        if (ipQuantity == 1)
                            ip.append(inetAddress.getHostAddress());
                        else
                            ip.append(" (").append(inetAddress.getHostAddress()).append(")");
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace(System.out);
        }
        return ip.toString();
    }
}
