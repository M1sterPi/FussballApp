package com.eahjena.app.wi.fussball;

import static com.eahjena.app.wi.fussball.MainApplication.getTeamIconByTeamName;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivitySpielDetail extends AppCompatActivity {



    private Button btnSpielDetail;



    /* verknüpfung der XML Datei */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel_detail);


        btnSpielDetail = (Button) findViewById(R.id.btn_back_spiel_detail);
        btnSpielDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActiviyErgebnisseSpieltag();}
        });



        ImageView ivTeamLogo1 = (ImageView)findViewById(R.id.iv_teamLogo1);
        ImageView ivTeamLogo2 = (ImageView)findViewById(R.id.iv_teamLogo2);
        TextView tvFinalResult = (TextView)findViewById(R.id.tv_finalResult);
        TextView tvMidResult = (TextView)findViewById(R.id.tv_midResult);
        TextView tvTeamName1 = (TextView)findViewById(R.id.tv_teamName1);
        TextView tvTeamName2 = (TextView)findViewById(R.id.tv_teamName2);
        TextView tvMatchDateTime = (TextView)findViewById(R.id.tv_matchDateTime);

        Bundle bundle = getIntent().getExtras();

        /* Spiel wird anhand seiner ID zurück gegeben */

        if(bundle.get("matchId")!= null)
        {
            int matchId = bundle.getInt("matchId");
            Match match = MainApplication.getMatchByMatchId(matchId);

            /* Übergabe an UI Elemente übergeben */

            ivTeamLogo1.setImageDrawable(getTeamIconByTeamName(match.team1));
            ivTeamLogo2.setImageDrawable(getTeamIconByTeamName(match.team2));
            tvFinalResult.setText(match.finalResult);
            tvMidResult.setText(match.midResult);
            tvTeamName1.setText(match.team1);
            tvTeamName2.setText(match.team2);
            tvMatchDateTime.setText(match.matchDateTime);



            RecyclerView recyclerView = findViewById(R.id.spiel_detail);
            GoalsAdapter goalsAdapter = new GoalsAdapter(this, match.goals);
            recyclerView.setAdapter( goalsAdapter );
            recyclerView.setLayoutManager( new LinearLayoutManager(this) );
        }
    }




    public void openActiviyErgebnisseSpieltag() {
        Intent intent = new Intent(this, ActivityErgebnisseSpieltag.class);
        startActivity(intent);
    }
}
