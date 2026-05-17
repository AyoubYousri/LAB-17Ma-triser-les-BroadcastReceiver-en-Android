package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Receiver statique déclaré dans le Manifest. 
 * Il est automatiquement appelé par le système Android après le démarrage complet de l'appareil.
 * 
 * Note : Nécessite la permission RECEIVE_BOOT_COMPLETED.
 * 
 * Modifié par YOUSRI Ayoub.
 */
public class BootReceiver extends BroadcastReceiver {
    
    /**
     * Cette méthode est déclenchée lors de la réception de l'événement système.
     * @param context Le contexte de l'application.
     * @param intent L'intent contenant l'action BOOT_COMPLETED.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // Vérification de l'action pour s'assurer qu'il s'agit bien du démarrage.
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Affichage d'un message informatif à l'utilisateur.
            Toast.makeText(context, "Téléphone démarré - BootReceiver activé par YOUSRI Ayoub !", Toast.LENGTH_LONG).show();
        }
    }
}
