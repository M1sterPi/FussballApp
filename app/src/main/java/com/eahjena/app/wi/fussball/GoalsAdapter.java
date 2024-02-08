package com.eahjena.app.wi.fussball;
// deklariert das Paket, in dem sich die Klasse befindet

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
// importiert die notwendigen Klassen


public class GoalsAdapter extends RecyclerView.Adapter<com.eahjena.app.wi.fussball.GoalsAdapter.ViewHolder> {
// Definiert GoalsAdpater als eine erweiterung des RecyclerView.Adapter mit dem spezifischen  ViewHolder-Typ GoalsAdapter.
// Viewholder. dies ermöglicht die Verwendung des Adapters mit einem Recyclerview
    // private String[] localDataSet;

    Context context;
    //Eine Instanz von Context die verwendet wird, um Zugriff auf Ressourcen und
    // spezifische Anwendungsfunktionen zu erhalten.
    List<Goal> goals;
    // eine Liste von Goal-Objekten die die Daten für jeden Eintrag im RecyclerView darstellen

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Definiert eine statische Subklasse ViewHolder, die von RecyclerView.viewholder erbt.
        //Sie dient als Container für die Ansichtskomponenten eines Listenelements
        //TextView Komponenten werden initialisiert, indem sie mittels finViewByID von
        // der übergeheben Ansicht (view) referenziert werden

        private final TextView rlTvScoreMinute;
        private final TextView rltvGoalPlayerName;
        private final TextView rltvScoreTeam1;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            rlTvScoreMinute = (TextView) view.findViewById(R.id.rl_tv_scoreMinute);
            rltvGoalPlayerName = (TextView) view.findViewById(R.id.rl_tv_goalPlayerName);
            rltvScoreTeam1 = (TextView) view.findViewById(R.id.rl_tv_scoreTeam1);
        }

        public TextView getRlTvScoreMinute() {return rlTvScoreMinute;}

        public TextView getRltvGoalPlayerName() {return rltvGoalPlayerName;}

        public TextView getRltvScoreTeam1() {return rltvScoreTeam1;}



    }

    /**
     * Initialize the dataset of the Adapter.
     */

    //Der Konstruktor des Adapters, der den Kontext und die Datenliste entgegennimmt
    // und die entsprechenden Felder initialisiert
    public GoalsAdapter(Context context, List<Goal> goals) {
        this.context = context;
        this.goals = goals;
    }

    // Create new views (invoked by the layout manager)
    @Override
    //wird aufgerufen um neuen viewholder zu erstellen.
    //diese Methode inflated  die Layoutdatei tore_row_layout auf und erstellt eine neue viewHolder Instanz
    // mit dieser Ansicht
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tore_row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    //wird aufgerufen um dne Inhalt des ViewHolder zu ersetzen.
    // Methode bezieht das entsprechende Goal-Objekt aus der Liste basierend auf der Position und
    // setzt die Texte der TextViews in der viewHolder-Instanz
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getRlTvScoreMinute().setText(goals.get(position).matchMinute + ". min");
        viewHolder.getRltvGoalPlayerName().setText(goals.get(position).goalGetterName);
        viewHolder.getRltvScoreTeam1().setText(goals.get(position).scoreTeam1+":"+goals.get(position).scoreTeam2);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    //Gibt die Größe der Datenliste zurück, was dem RecylerView hilft, die Anzahl der darzustellenden elemnte zu bestimmen
    public int getItemCount() {
        return goals.size();
    }
}

