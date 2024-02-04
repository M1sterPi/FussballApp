package com.eahjena.app.wi.fussball;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class GoalsAdapter extends RecyclerView.Adapter<com.eahjena.app.wi.fussball.GoalsAdapter.ViewHolder> {

    // private String[] localDataSet;

    Context context;
    List<Goal> goals;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    public static class ViewHolder extends RecyclerView.ViewHolder {

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

    public GoalsAdapter(Context context, List<Goal> goals) {
        this.context = context;
        this.goals = goals;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tore_row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getRlTvScoreMinute().setText(goals.get(position).matchMinute + ". min");
        viewHolder.getRltvGoalPlayerName().setText(goals.get(position).goalGetterName);
        viewHolder.getRltvScoreTeam1().setText(goals.get(position).scoreTeam1+":"+goals.get(position).scoreTeam2);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return goals.size();
    }
}

