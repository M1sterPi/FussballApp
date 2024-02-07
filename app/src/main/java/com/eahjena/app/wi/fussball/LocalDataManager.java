package com.eahjena.app.wi.fussball;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LocalDataManager {

    private static final String PREF_NAME = "local_data";
    private static final String KEY_TABLE_TEAMS = "table_teams";

    //Methode zum Speichern der Daten für Offline Betrieb
    public static void saveTableTeams(Context context, List<TableTeam> tableTeams) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tableTeams);
        editor.putString(KEY_TABLE_TEAMS, json);
        editor.apply();
    }

    //Methode zum Abrufen der Daten im Offline Betrieb
    public static List<TableTeam> loadTableTeams(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_TABLE_TEAMS, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<TableTeam>>() {}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>(); // Rückgabe einer leeren Liste, wenn keine Daten vorhanden sind
    }

}
