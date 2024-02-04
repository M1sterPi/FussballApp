package com.eahjena.app.wi.fussball;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


/* Activity für die aktuelle Bundesligatabelle - Schritt 7 */

public class ActivityTabelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabelle); /* wählt die View aus xml */

        /* Button um auf das Menü zurück zu kommen erstellen */

        Button btnBackTabelleToMain;


        btnBackTabelleToMain = (Button)findViewById(R.id.btn_back_tabelle);
        btnBackTabelleToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActiviyMain();}
        });




        RecyclerView recyclerView = findViewById(R.id.team_table);
        TableAdapter tableAdapter = new TableAdapter(this);
        recyclerView.setAdapter( tableAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );
    }

    /* Methode für den Button */

    public void openActiviyMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}