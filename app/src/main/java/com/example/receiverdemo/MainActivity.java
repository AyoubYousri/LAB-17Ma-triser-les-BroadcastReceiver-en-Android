package com.example.receiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Activité principale de l'application de démonstration des Broadcast Receivers.
 * Cette classe gère l'interface utilisateur, l'enregistrement d'un receiver dynamique 
 * et l'envoi d'un broadcast personnalisé.
 * 
 * Modifié et commenté en détail par YOUSRI Ayoub.
 */
public class MainActivity extends AppCompatActivity {

    // Déclaration du receiver dynamique pour le mode avion. 
    // Il doit être enregistré programmatiquement.
    private AirplaneModeReceiver airplaneReceiver;
    
    // Flag pour suivre l'état de l'enregistrement du receiver afin d'éviter les erreurs.
    private boolean isReceiverRegistered = false;

    // Références aux éléments graphiques définis dans activity_main.xml.
    private Button btnToggleAirplane;
    private Button btnSendCustom;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Liaison de l'activité avec son layout XML.
        setContentView(R.layout.activity_main);

        // Initialisation des objets.
        airplaneReceiver = new AirplaneModeReceiver();
        tvStatus = findViewById(R.id.tvStatus);
        btnToggleAirplane = findViewById(R.id.btnToggleAirplane);
        btnSendCustom = findViewById(R.id.btnSendCustom);

        // État initial de l'interface (receiver non enregistré au départ).
        updateReceiverUi(false);

        // Configuration des écouteurs de clics pour les boutons.
        btnToggleAirplane.setOnClickListener(v -> toggleAirplaneReceiver());
        btnSendCustom.setOnClickListener(v -> sendCustomBroadcast());
    }

    /**
     * Alterne entre l'enregistrement et le désenregistrement du receiver de mode avion.
     * C'est un exemple de gestion dynamique des Broadcast Receivers.
     */
    private void toggleAirplaneReceiver() {
        if (!isReceiverRegistered) {
            // Un IntentFilter définit quels types d'intents le receiver doit écouter.
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

            // Enregistrement du receiver avec les paramètres de sécurité Android récents.
            // RECEIVER_NOT_EXPORTED signifie que seules les applications internes ou le système peuvent envoyer des signaux.
            ContextCompat.registerReceiver(
                    this,
                    airplaneReceiver,
                    filter,
                    ContextCompat.RECEIVER_NOT_EXPORTED
            );

            isReceiverRegistered = true;
            updateReceiverUi(true);
        } else {
            // Désenregistrement pour libérer les ressources.
            unregisterReceiver(airplaneReceiver);
            isReceiverRegistered = false;
            updateReceiverUi(false);
        }
    }

    /**
     * Met à jour l'apparence visuelle de l'activité selon que le receiver est actif ou non.
     * @param isActive État actuel de l'écouteur.
     */
    private void updateReceiverUi(boolean isActive) {
        if (isActive) {
            tvStatus.setText(R.string.status_airplane_on);
            tvStatus.setTextColor(ContextCompat.getColor(this, R.color.status_on_text));
            tvStatus.setBackgroundResource(R.drawable.status_active_background);
            btnToggleAirplane.setText(R.string.action_disable_airplane);
            btnToggleAirplane.setBackgroundResource(R.drawable.button_stop_background);
        } else {
            tvStatus.setText(R.string.status_airplane_off);
            tvStatus.setTextColor(ContextCompat.getColor(this, R.color.status_off_text));
            tvStatus.setBackgroundResource(R.drawable.status_inactive_background);
            btnToggleAirplane.setText(R.string.action_enable_airplane);
            btnToggleAirplane.setBackgroundResource(R.drawable.button_primary_background);
        }
    }

    /**
     * Crée et envoie un broadcast personnalisé (Custom Intent).
     * Ce broadcast sera intercepté par CustomEventReceiver s'il est déclaré.
     */
    private void sendCustomBroadcast() {
        // Création de l'intent avec une action personnalisée.
        Intent intent = new Intent(CustomEventReceiver.ACTION_CUSTOM_EVENT);
        // Ajout de données supplémentaires (extras) à l'intent.
        intent.putExtra("message", "Bonjour depuis le custom broadcast envoyé par YOUSRI Ayoub!");
        // Définition explicite de la classe cible pour des raisons de sécurité (Explicit Intent).
        intent.setClass(this, CustomEventReceiver.class);
        
        // Envoi du signal à travers le système.
        sendBroadcast(intent);

        Toast.makeText(this, "Custom Broadcast envoyé par YOUSRI Ayoub!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Méthode de cycle de vie appelée avant la destruction de l'activité.
     * Il est crucial de désenregistrer les receivers dynamiques ici pour éviter les fuites mémoire.
     */
    @Override
    protected void onDestroy() {
        if (isReceiverRegistered) {
            unregisterReceiver(airplaneReceiver);
        }
        super.onDestroy();
    }
}
