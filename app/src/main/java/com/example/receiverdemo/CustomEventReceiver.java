package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Receiver personnalisé utilisé pour recevoir le broadcast custom de l'application.
 * Ce receiver démontre comment une application peut définir ses propres événements.
 * 
 * Modifié par YOUSRI Ayoub.
 */
public class CustomEventReceiver extends BroadcastReceiver {
    // Action unique identifiant ce broadcast personnalisé.
    public static final String ACTION_CUSTOM_EVENT = "com.example.receiverdemo.CUSTOM_EVENT";

    /**
     * Méthode appelée lors de la réception du broadcast.
     * Elle extrait les données supplémentaires et les affiche via un Toast.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_CUSTOM_EVENT.equals(intent.getAction())) {
            // Récupération de la donnée passée sous la clé "message".
            String message = intent.getStringExtra("message");
            // Affichage du message reçu.
            Toast.makeText(context, "Custom reçu : " + message, Toast.LENGTH_LONG).show();
        }
    }
}
