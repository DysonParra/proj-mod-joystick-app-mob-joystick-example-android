/*
 * @fileoverview {ButtonAction} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {ButtonAction} fue realizada el 31/07/2022.
 * @Dev - La primera version de {ButtonAction} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.example.joystick.mobile.buttonaction;

import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.project.dev.joystick.GenericComponent.BUTTON_TYPED;
import static com.project.dev.joystick.GenericComponent.BUTTON_PRESSED;
import static com.project.dev.joystick.GenericComponent.BUTTON_RELEASED;
import static com.project.dev.joystick.GenericComponent.BUTTON_UNPRESSED;

import com.project.dev.example.joystick.mobile.activity.ExampleActivity;
import com.project.dev.example.joystick.mobile.getter.GraphicGetter;
import com.project.dev.example.joystick.mobile.setter.GenericJoystickComponentActionSetter;

/**
 * TODO: Definición de {@code ButtonAction}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class ButtonAction {

    private static int buttonLeftImageNumber = 0;
    private static int buttonRightImageNumber = -1;
    private static int buttonUpImageNumber = -1;
    private static int buttonDownImageNumber = -1;

    /**
     * FIXME: Definición de {@code runButtonStartAction}. Ejecuta la acción para el botón start.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonStartAction(GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonSelectAction}. Ejecuta la acción para el botón select.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonSelectAction(GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonMoveLeftAction}. Ejecuta la acción para el botón flecha
     * izquierda.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveLeftAction(GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                final LinearLayout.LayoutParams viewParams = (LinearLayout.LayoutParams) actionSetter.getComponent().getLayoutParams();
                final ImageView component = actionSetter.getComponent();
                if (viewParams.leftMargin >= component.getWidth() / 10) {
                    actionSetter.getWindow().runOnUiThread(new Runnable() {
                        public void run() {
                            viewParams.leftMargin -= component.getWidth() / 10;
                            component.setLayoutParams(viewParams);
                        }
                    });
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonMoveRightAction}. Ejecuta la acción para el botón flecha
     * derecha.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveRightAction(GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                final LinearLayout.LayoutParams viewParams = (LinearLayout.LayoutParams) actionSetter.getComponent().getLayoutParams();
                final ImageView component = actionSetter.getComponent();
                if (viewParams.leftMargin + component.getWidth() <= actionSetter.getWindowWidth()) {
                    actionSetter.getWindow().runOnUiThread(new Runnable() {
                        public void run() {
                            viewParams.leftMargin += component.getWidth() / 10;
                            component.requestLayout();
                        }
                    });
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonMoveUpAction}. Ejecuta la acción para el botón flecha
     * arriba.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveUpAction(GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                final LinearLayout.LayoutParams viewParams = (LinearLayout.LayoutParams) actionSetter.getComponent().getLayoutParams();
                final ImageView component = actionSetter.getComponent();
                if (viewParams.topMargin >= component.getHeight() / 10) {
                    viewParams.topMargin -= component.getHeight() / 10;
                    actionSetter.getWindow().runOnUiThread(new Runnable() {
                        public void run() {
                            component.invalidate();
                            component.requestLayout();
                        }
                    });
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonMoveDownAction}. Ejecuta la acción para el botón flecha
     * abajo.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveDownAction(GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                final LinearLayout.LayoutParams viewParams = (LinearLayout.LayoutParams) actionSetter.getComponent().getLayoutParams();
                final ImageView component = actionSetter.getComponent();
                if (viewParams.topMargin + component.getHeight() + (component.getHeight() / 10) <= actionSetter.getWindowHeight()) {
                    viewParams.topMargin += component.getHeight() / 10;
                    actionSetter.getWindow().runOnUiThread(new Runnable() {
                        public void run() {
                            component.invalidate();
                            component.requestLayout();
                        }
                    });
                }
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonMoveMediumAction}. Ejecuta la acción para el botón
     * flecha intermedia.
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonMoveMediumAction(GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonLeftAction}. Ejecuta la acción para el botón de arriba
     * del lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonLeftAction(final GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                buttonLeftImageNumber++;
                actionSetter.getWindow().runOnUiThread(new Runnable() {
                    public void run() {
                        actionSetter.getComponent().setImageBitmap(GraphicGetter.getGraphic("hand_0" + ((buttonLeftImageNumber % 6) + 1) + ".png", actionSetter.getWindowWidth(), actionSetter.getWindowHeight(), actionSetter.getWindowDivision()));
                    }
                });
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonRightAction}. Ejecuta la acción para el botón de arriba
     * del lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonRightAction(final GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                buttonRightImageNumber++;
                actionSetter.getWindow().runOnUiThread(new Runnable() {
                    public void run() {
                        actionSetter.getComponent().setImageBitmap(GraphicGetter.getGraphic("punch_0" + ((buttonRightImageNumber % 6) + 1) + ".png", actionSetter.getWindowWidth(), actionSetter.getWindowHeight(), actionSetter.getWindowDivision()));
                    }
                });
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonUpAction}. Ejecuta la acción para el botón de arriba del
     * lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonUpAction(final GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                buttonUpImageNumber++;
                actionSetter.getWindow().runOnUiThread(new Runnable() {
                    public void run() {
                        actionSetter.getComponent().setImageBitmap(GraphicGetter.getGraphic("good_0" + ((buttonUpImageNumber % 6) + 1) + ".png", actionSetter.getWindowWidth(), actionSetter.getWindowHeight(), actionSetter.getWindowDivision()));
                    }
                });
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }

    /**
     * FIXME: Definición de {@code runButtonDownAction}. Ejecuta la acción para el botón de arriba
     * del lado contrario a los direccionales (X,Y,O,A, etc...).
     *
     * @param actionSetter es el asignador de acciones para un joystick.
     * @param buttonState  es el estado del botón.
     */
    public static void runButtonDownAction(final GenericJoystickComponentActionSetter<ExampleActivity, ImageView> actionSetter, byte buttonState) {
        switch (buttonState) {
            case BUTTON_TYPED:
                buttonDownImageNumber++;
                actionSetter.getWindow().runOnUiThread(new Runnable() {
                    public void run() {
                        actionSetter.getComponent().setImageBitmap(GraphicGetter.getGraphic("tick_0" + ((buttonDownImageNumber % 6) + 1) + ".png", actionSetter.getWindowWidth(), actionSetter.getWindowHeight(), actionSetter.getWindowDivision()));
                    }
                });
                break;

            case BUTTON_PRESSED:
                break;

            case BUTTON_RELEASED:
                break;

            case BUTTON_UNPRESSED:
                break;
        }
    }
}
