package edu.colorado.caterpillars.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.colorado.caterpillars.R;
import edu.colorado.caterpillars.main.Game;

public class ChooseMoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Game game;
        game = Game.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_move);
        TextView playerMoveTitle = findViewById(R.id.playerMoveTitle);
        playerMoveTitle.setText(game.getActivePlayer().getName() + " Choose Move");
        Button btn_atk = findViewById(R.id.btn_atk);
        btn_atk.setOnClickListener((v) -> openAttackActivity());
    }
    void openAttackActivity(){
        Intent intent = new Intent(this, AttackActivity.class);
        startActivity(intent);
    }
}