package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Receiver dynamique pour détecter les changements du mode avion.
 * Ce receiver est géré manuellement dans l'activité pour s'adapter au cycle de vie.
 * 
 * Modifié par YOUSRI Ayoub.
 */
public class AirplaneModeReceiver extends BroadcastReceiver {

    /**
     * Méthode appelée à chaque fois que le mode avion est activé ou désactivé.
     * @param context Le contexte d'exécution.
     * @param intent L'intent contenant l'état actuel du mode avion.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // Vérification que l'action reçue est bien le changement de mode avion.
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {

            // Récupère l'état du mode avion via l'extra "state" (true = activé, false = désactivé).
            boolean isAirplaneOn = intent.getBooleanExtra("state", false);

            // Construction du message selon l'état détecté.
            String message = isAirplaneOn
                    ? "Mode Avion ACTIVÉ - Signal capté par YOUSRI Ayoub !"
                    : "Mode Avion DÉSACTIVÉ - Signal capté par YOUSRI Ayoub !";

            // Affichage d'un Toast pour notifier l'utilisateur immédiatement.
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
