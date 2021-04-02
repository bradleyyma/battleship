package edu.colorado.caterpillars;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_help = (Button) findViewById(R.id.btn_help);
        btn_help.setOnClickListener((v)->{openHelpMenu();});

    }

    public void openHelpMenu(){
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
    }
}