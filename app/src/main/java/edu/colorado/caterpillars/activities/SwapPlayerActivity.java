package edu.colorado.caterpillars.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.colorado.caterpillars.main.Game;
import edu.colorado.caterpillars.R;

public class SwapPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap_player);
        Game game = Game.getInstance();
        game.endTurn();
        TextView nextPlayer = findViewById(R.id.txt_pass_device);
        nextPlayer.setText("Pass the device to " + game.getActivePlayer().getName());
        Button btn_swap = (Button) findViewById(R.id.btn_swap_confirm);
        if(game.getTurnNumber() < 2){
            btn_swap.setOnClickListener((v)->openPlaceShipActivity());
        }else{
            btn_swap.setOnClickListener((v)->openChooseMoveActivity());
        }


    }

    public void openPlaceShipActivity(){
        Intent intent = new Intent(this, PlaceShipActivity.class);
        startActivity(intent);
    }
    public void openChooseMoveActivity(){
        Intent intent = new Intent(this, ChooseMoveActivity.class);
        startActivity(intent);
    }
}