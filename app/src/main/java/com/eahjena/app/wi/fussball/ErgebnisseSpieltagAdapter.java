package com.eahjena.app.wi.fussball;

import static com.eahjena.app.wi.fussball.MainApplication.getTeamIconByTeamName;
import static com.eahjena.app.wi.fussball.MainApplication.matchList;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ErgebnisseSpieltagAdapter extends RecyclerView.Adapter<ErgebnisseSpieltagAdapter.ViewHolder> {

    Context context;

    /* Adapter, der die Daten für RecyclerView verwaltet und Views (Ansichten erstellt - Items?)

     ViewHolder managed  die Ansicht (Layout), die verwendet wird, um einen einzelnen Eintrag im RecyclerView darzustellen.*/

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView rlTvTeamName1;
        private final TextView rlTvTeamName2;
        private final ImageView rlIvTeamLogo1;
        private final ImageView rlIvTeamLogo2;
        private final TextView rlTvFinalResult;



        public ViewHolder(View view) {
            super(view);

            /* Bezug wird hergestellt */

            rlTvTeamName1 = (TextView) view.findViewById(R.id.rl_tv_teamName1);
            rlTvTeamName2 = (TextView) view.findViewById(R.id.rl_tv_teamName2);
            rlIvTeamLogo1 = (ImageView) view.findViewById(R.id.rl_iv_teamLogo1);
            rlIvTeamLogo2 = (ImageView) view.findViewById(R.id.rl_iv_teamLogo2);
            rlTvFinalResult = (TextView) view.findViewById(R.id.rl_tv_finalResult);
        }

        public TextView getRlTvTeamName1() {
            return rlTvTeamName1;
        }

        public TextView getRlTvTeamName2() {
            return rlTvTeamName2;
        }

        public ImageView getRlIvTeamLogo1() {
            return rlIvTeamLogo1;
        }

        public ImageView getRlIvTeamLogo2() {
            return rlIvTeamLogo2;
        }

        public TextView getRlTvFinalResult() {
            return rlTvFinalResult;
        }

    }


    public ErgebnisseSpieltagAdapter(Context context) {
        this.context = context;
    }

    /* erstellt neue Views - Lädt das Layout aus der XML Ergebnisse_Row_Layout
    Ansicht wird dann verwendet, um einen Eintrag (Item) im RecyclerView darzustellen*/

    @Override


    public ErgebnisseSpieltagAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ergebnisse_row_layout, viewGroup, false);

        return new ErgebnisseSpieltagAdapter.ViewHolder(view);
    }





    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {



        /* Zeile zum Clicken um auf die Details zu kommen

         Methode onBindViewHolder wird aufgerufen, um die Ansicht mit den Daten zu binden,
         die für einen bestimmten Eintrag (Item) im RecyclerView verwendet werden sollen. */

        viewHolder.itemView.setTag(matchList.get(position).matchId);
        viewHolder.itemView.setOnClickListener(globalClickListener);

        viewHolder.getRlTvTeamName1().setText(matchList.get(position).team1);
        viewHolder.getRlIvTeamLogo1().setImageDrawable(getTeamIconByTeamName(matchList.get(position).team1));
        viewHolder.getRlTvFinalResult().setText(matchList.get(position).finalResult);
        viewHolder.getRlIvTeamLogo2().setImageDrawable(getTeamIconByTeamName(matchList.get(position).team2));
        viewHolder.getRlTvTeamName2().setText(matchList.get(position).team2);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return matchList.size();
    }


    // On Click listener für Aufruf der Spiel Details

    private View.OnClickListener globalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int matchId = (int) v.getTag();
            Intent intent= new Intent(context, ActivitySpielDetail.class);
            intent.putExtra("matchId",matchId);
            context.startActivity(intent);
        }
    };
}