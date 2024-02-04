package com.eahjena.app.wi.fussball;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;



public class ActivityErgebnisseSpieltag extends AppCompatActivity {

    /* Button deklarieren um zurück ins Menü zu kommen */

    private Button btnBackErgebnisseToMain;

    /* setzt das Layout für XML - activity_ergebnisse_spieltag */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnisse_spieltag);

        /* OnClick Listener um zurück auf die Main Actvity zu kommen */

        Button btnBackErgebnisseToMain = (Button)findViewById(R.id.btn_back_ergebnisse_spieltag);
        btnBackErgebnisseToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActiviyMain();}
        });




        /* Recyclerview :ergebnisse_spieltag vom XML activity_ergebnisse_spieltag
        ErgebnisseSpieltagAdapter verknüpft die Daten mit der View  */


        RecyclerView recyclerView = findViewById(R.id.ergebnisse_spieltag);
        ErgebnisseSpieltagAdapter ergebnisseSpieltagAdapter = new ErgebnisseSpieltagAdapter(this);
        recyclerView.setAdapter( ergebnisseSpieltagAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager( this) );


    }

    /* Methode für den zurück Button  */

    public void openActiviyMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}