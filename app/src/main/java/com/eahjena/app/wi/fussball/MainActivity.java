package com.eahjena.app.wi.fussball;


import static com.eahjena.app.wi.fussball.MainApplication.goalList;
import static com.eahjena.app.wi.fussball.MainApplication.matchList;
import static com.eahjena.app.wi.fussball.MainApplication.tableTeamList;

import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



/* AppCompatActivity" ist eine von Android bereitgestellte Klasse, die auch altere Funkionalitäten unterstützt */

public class MainActivity extends AppCompatActivity {


    /* Button deklarieren */

    private Button btnTable;
    private Button btnErgebnisse;
    private Button btnMap;



    /* Kommunizieren aus dem Hintergrundprozess */

    private final Handler mainHandler = new Handler();

    private ProgressDialog progressDialog; // Daten werden geladen Bildschirm


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hintergrundthread fetchData, der Daten von einer API abruft und in die App lädt.*/

        if(!MainApplication.isLoaded) {
            new fetchData().start();

            // MainApplication.setData(this); - für Erzeugung der Testdaten
            MainApplication.isLoaded = true;
        }



        /* Button Initialisieren - zum öffnen der Ergebnisse */


        btnErgebnisse =(Button) findViewById(R.id.btn_ergebnisse);
        btnErgebnisse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActiviyErgebnisseSpieltag();}
        });

        /* Button Initialisieren - zum öffnen der Tabelle */

        btnTable = (Button) findViewById(R.id.btn_table);
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActiviyTabelle();
            }
        });

        /* Button Initialisieren - zum öffnen der Ergebnisse */


        btnMap =(Button) findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActivityMap();}
        });
    }


    /* permission für Internetzugang in der Manifest Datei*/
    /*  Klasse Fetch Data - Klasse erbt von der Klasse Thread */

    class fetchData extends Thread {

        String data = "";

        public void run() {


    // Handler kommunziert zwischen Hintergrundthread (Daten beziehen) und Mainthread
            mainHandler.post(new Runnable() {
                @Override

                /* Methode für die klasse - wird mit Run gestartet */

                /* Während des Hintergrundprozesses - Fenster Daten werden geladen wird erzeugt */

                public void run() {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Daten werden geladen");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });


            /* Tore und Matches */

            try {
                /* Text wird in ein URL Objekt in Java umgewandelt */
                URL url = new URL("https://api.openligadb.de/getmatchdata/bl1");

                /* Verbindung wird aufgebaut */
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // Inhalt wird ausgelesen

                String line;
                while((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }

                /* in Data werden alle Strings abgespeichert bis keine neuen Informationen mehr kommen */

                if(!data.isEmpty()) {

                    JSONArray result = new JSONArray(data);


                    for (int i  = 0; i < result.length(); i++) {
                        JSONObject matchJson = result.getJSONObject(i);

                        /* das wird aus der API Schnittstelle geholt
                         Goals sind leer in bei der API */

                        /* Temporäre Tor Liste für das aktuelle Match */

                        List<Goal> goals = new ArrayList<Goal>();
                        JSONArray goalsArray = matchJson.getJSONArray("goals");

                        /* Hier wird jedes Tor im Json Array Goals iteriert */
                        for(int j = 0; j < goalsArray.length(); j++) {
                            JSONObject goalJson = goalsArray.getJSONObject(j);

                            /* Auslesen der einzelnen Werte eines Tores + zwischenspeicherung */
                            int goalId = goalJson.getInt("goalID");
                            int goalGetterId = goalJson.getInt("goalGetterID");
                            String goalGetterName = goalJson.getString("goalGetterName");
                            int matchMinute = goalJson.getInt("matchMinute");
                            int scoreTeam1 = goalJson.getInt("scoreTeam1");
                            int scoreTeam2 = goalJson.getInt("scoreTeam2");

                            /* Erzeugen eines Tor Objektes mit den ausglesenen Werten*/

                            Goal goal = new Goal(
                                    goalId,
                                    goalGetterId,
                                    goalGetterName,
                                    matchMinute,
                                    scoreTeam1,
                                    scoreTeam2
                            );

                            // hinzufügen des erzeugten Tores zu der GoalList in Main Application
                            goalList.add(goal);

                            /* hinzufügen des erzeugten Tores zur temporären Torliste
                             wird bei der erzeugung eines Matches verwendet */
                            goals.add(goal);
                        }

                        // Match
                        int matchID = matchJson.getInt("matchID");
                        String team1 = matchJson.getJSONObject("team1").getString("teamName");
                        String team2 = matchJson.getJSONObject("team2").getString("teamName");
                        String matchDateTime = matchJson.getString("matchDateTime");
                        String midResult = "";
                        String finalResult = "";
                        JSONArray resultsArray = matchJson.getJSONArray("matchResults");
                        for(int j = 0; j < resultsArray.length(); j++) {
                            JSONObject resultJson = resultsArray.getJSONObject(j);

                            /* Anhand der Type ID werden die Ergebnisse ausgelesen und zugeteilt
                               1 = Halbzeit ,  2 = Final */
                            if(resultJson.getInt("resultTypeID") == 1) {
                                midResult = resultJson.getString("pointsTeam1") + ":" + resultJson.getString("pointsTeam2");
                            } else if(resultJson.getInt("resultTypeID") == 2) {
                                finalResult = resultJson.getString("pointsTeam1") + ":" + resultJson.getString("pointsTeam2");
                            }
                        }
                        Match match = new Match(
                                matchID,
                                team1,
                                team2,
                                goals, // Liste an Toren
                                midResult,
                                finalResult,
                                matchDateTime
                        );

                        /* hinzufügen zur Globalen Liste - (Match Inklusive Goal Liste)*/

                        matchList.add(match);

                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // finish();
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }



            // Tabellen Teams

            try {
                URL url = new URL("https://api.openligadb.de/getbltable/bl1/2023");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                data = "";
                while((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }

                if(!data.isEmpty()) {
                    JSONArray result = new JSONArray(data);

                    for (int i  = 0; i < result.length(); i++) {
                        JSONObject teamTable = result.getJSONObject(i);

                        int teamInfoId = teamTable.getInt("teamInfoId");
                        String teamName = teamTable.getString("teamName");
                        String shortName = teamTable.getString("shortName");
                        int matches = teamTable.getInt("matches");
                        int won = teamTable.getInt("won");
                        int draw = teamTable.getInt("draw");
                        int lost = teamTable.getInt("lost");
                        int goals = teamTable.getInt("goals");
                        int opponentGoals = teamTable.getInt("opponentGoals");
                        int points = teamTable.getInt("points");
                        String teamIconUrl = teamTable.getString("teamIconUrl");

                        TableTeam tableTeam = new TableTeam(
                                i + 1,
                                teamInfoId,
                                teamName,
                                shortName,
                                matches,
                                won,
                                draw,
                                lost,
                                goals,
                                opponentGoals,
                                points,
                                teamIconUrl
                        );


                        tableTeam.teamIcon = LoadImageFromWebOperations(teamIconUrl);
                        tableTeamList.add(tableTeam);
                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
              //  loadLocalData(); // Lade lokale Daten im Falle einer IOException
            } catch (JSONException e) {
                e.printStackTrace();
            }



            // Progress Dialog wird entfernt / beendet

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }


            });




        }


    }



    public static Drawable LoadImageFromWebOperations(String teamIconUrl) {
        try {
            InputStream is = (InputStream) new URL(teamIconUrl).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }




    /* Methode für Buttons erstellen */

    public void openActiviyTabelle() {
        Intent intent = new Intent(this, ActivityTabelle.class);
        startActivity(intent);
    }


    public void openActiviyErgebnisseSpieltag() {
        Intent intent = new Intent(this, ActivityErgebnisseSpieltag.class);
        startActivity(intent);

    }
    public void openActivityMap() {
        Intent intent = new Intent(this, ActivityMap.class);
        startActivity(intent);
    }
}