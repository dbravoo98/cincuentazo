package com.example.cincuentazo.view.Alert;

public interface AlertBoxInterface {
    /**
     * Displays an information alert with the specified title, header, and message.
     * @param title   The title of the alert dialog, typically displayed at the top.
     * @param header  The header text of the alert dialog, providing context for the alert.
     * @param message The content message of the alert dialog, conveying the main information to the user.
     */
    void InformationAlert(String title, String header, String message);
}