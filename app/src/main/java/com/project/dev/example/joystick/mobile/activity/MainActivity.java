/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.example.joystick.mobile.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.project.dev.joystick.name.nintendo.NintendoJoystick;
import com.project.dev.joystick.name.poly.PolyJoystick;
import static com.project.dev.joystick.name.generic.GenericJoystick.JOYSTICK_TYPE_SERVER;

import com.project.dev.example.joystick.mobile.R;

/**
 * TODO: Definición de {@code MainActivity}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class MainActivity extends AppCompatActivity {

    /*
     * Variables asociadas con elementos la vista.
     */
    private Spinner spnJoystickName;
    private Button btnCreate;

    /*
     * Variables locales.
     */
    private static final String[] JOYSTICK_NAMES = {
        PolyJoystick.JOYSTICK_NAME,
        NintendoJoystick.JOYSTICK_NAME
    };

    /**
     * Invocado cuando se crea el activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Crea instancia del activity y la asocia con la vista.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Asocia variables locales con elementos de la vista.
        spnJoystickName = findViewById(R.id.spnJoystickName);
        btnCreate = findViewById(R.id.btnCreateConnect);

        // Asigna valores al spinner con el nombre del joystick.
        ArrayAdapter<String> spnJoystickNameAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, JOYSTICK_NAMES);
        spnJoystickName.setAdapter(spnJoystickNameAdapter);

        // Comportamiento del botón crear joysticks.
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un intent para iniciar el activity con el joystick.
                Intent intent = new Intent(MainActivity.this, ExampleActivity.class);

                // Manda información al activity con el joystick.
                intent.putExtra("joystickType", JOYSTICK_TYPE_SERVER);
                intent.putExtra("joystickName", JOYSTICK_NAMES[(int) spnJoystickName.getSelectedItemId()]);

                // Inicia el activity.
                startActivity(intent);
            }
        });
    }
}
