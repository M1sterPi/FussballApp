package com.eahjena.app.wi.fussball;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivityMap extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        // Überprüfen, ob die Standortberechtigung vorhanden ist
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Falls nicht, Berechtigung anfordern
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Falls die Berechtigung vorhanden ist, lade die Karte
            loadMap();
        }


        //Button, um aufs Menü zurückzukommen

        Button btnBackTabelleToMain;


        btnBackTabelleToMain = (Button)findViewById(R.id.MapMenü);
        btnBackTabelleToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActiviyMain();}


        });
    }

    private void loadMap() {
        webView.loadUrl("https://www.openstreetmap.org");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Berechtigung wurde gewährt, lade die Karte
                loadMap();
            } else {
                // Berechtigung wurde nicht gewährt, zeige eine Nachricht an den Benutzer
                Toast.makeText(this, "Standortberechtigung wurde verweigert", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openActiviyMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
}}
