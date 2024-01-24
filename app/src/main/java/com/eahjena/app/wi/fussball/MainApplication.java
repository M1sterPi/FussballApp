package com.eahjena.app.wi.fussball;

import android.app.Application;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class MainApplication extends Application {


    public static boolean isLoaded = false ;
    public static ArrayList<TableTeam> tableTeamList = new ArrayList<TableTeam>();
    public static ArrayList<Goal> goalList = new ArrayList<>();
    public static ArrayList<Match> matchList = new ArrayList<Match>();



    public static Match getMatchByMatchId(int matchId) {
        for (int i = 0; i < matchList.size(); i++) {
            //Log.d("","hhhh"+tableTeamList.get(i).teamName);
            if(matchList.get(i).matchId == matchId) {
                return matchList.get(i);
            }
        }
        return null;
    }

    // Kommt vom kommunikationsprozess mit fetch Data
    // Teamname kommt als Text
    public static Drawable getTeamIconByTeamName(String teamName) {
        for (int i = 0; i < tableTeamList.size(); i++) {
            //Log.d("","hhhh"+tableTeamList.get(i).teamName);
            if(tableTeamList.get(i).teamName.equals(teamName)) {
                return tableTeamList.get(i).teamIcon;
            }
        }
        return null;
    }


}