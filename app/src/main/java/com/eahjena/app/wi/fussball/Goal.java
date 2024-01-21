package com.eahjena.app.wi.fussball;

/* Schritt 10 b*/

public class Goal {

    int goalId;
    int goalGetterId;
    String goalGetterName;
    int matchMinute;
    int scoreTeam1;
    int scoreTeam2;

    public Goal(int goalId, int goalGetterId, String goalGetterName, int matchMinute, int scoreTeam1, int scoreTeam2) {
        this.goalId = goalId;
        this.goalGetterId = goalGetterId;
        this.goalGetterName = goalGetterName;
        this.matchMinute = matchMinute;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
    }
}
