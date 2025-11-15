package com.example.cincuentazo.view.Alert;

import javafx.scene.control.Alert;

/**
 * AlertBox utility class.
 */
public class AlertBox implements AlertBoxInterface {

    @Override
    public void InformationAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Método estático compatible con GameController.
     * Usa un header vacío automáticamente.
     */
    public static void show(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
