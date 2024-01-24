package com.eahjena.app.wi.fussball;

/* Schritt 11 - Create a new Class named TableAdapter */

import static com.eahjena.app.wi.fussball.MainApplication.tableTeamList;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    /* 11 a */

    Context context;

    /* View vom Layout holen */

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView rlTvPlace;
        private final ImageView rlIvLogo;
        private final TextView rlTvTeam;
        private final TextView rlTvGoals;
        private final TextView rlTvPoints;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            rlTvPlace = (TextView) view.findViewById(R.id.rl_tv_place);
            rlIvLogo = (ImageView) view.findViewById(R.id.rl_iv_logo);
            rlTvTeam = (TextView) view.findViewById(R.id.rl_tv_team);
            rlTvGoals = (TextView) view.findViewById(R.id.rl_tv_goals);
            rlTvPoints = (TextView) view.findViewById(R.id.rl_tv_points);
        }

        public TextView getRlTvPlace() {
            return rlTvPlace;
        }

        public ImageView getRlIvLogo() {
            return rlIvLogo;
        }

        public TextView getRlTvTeam() {
            return rlTvTeam;
        }

        public TextView getRlTvGoals() {
            return rlTvGoals;
        }

        public TextView getRlTvPoints() {
            return rlTvPoints;
        }
    }



    public TableAdapter(Context context) {
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.teamtable_row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getRlTvPlace().setText(""+(position+1));
        viewHolder.getRlIvLogo().setImageDrawable(tableTeamList.get(position).teamIcon);
        viewHolder.getRlTvTeam().setText(tableTeamList.get(position).teamName);
        viewHolder.getRlTvGoals().setText("+"+tableTeamList.get(position).goals);
        viewHolder.getRlTvPoints().setText(""+tableTeamList.get(position).points);

        // viewHolder.getIvLogo().setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.a_1_fc_nuernberg));
        // viewHolder.getIvLogo().setImageResource(context.getResources().getIdentifier(tableTeamList.get(position).teamIconUrl, "drawable", context.getPackageName()));
        // viewHolder.getIvLogo().setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.spvgg_greuther_fuerth));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tableTeamList.size();
    }
}
