package edu.colorado.caterpillars;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class P1PlaceShipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1_place_ship);

        Button btn_place = (Button) findViewById(R.id.btn_place);
        btn_place.setOnClickListener((v)->{placeShip();});

    }

    public void placeShip(){

    }
}