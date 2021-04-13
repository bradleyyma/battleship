package edu.colorado.caterpillars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class P1PlaceShipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1_place_ship);
        EditText rowText = findViewById(R.id.editTextRow);
        EditText colText = findViewById(R.id.editTextColumn);
        EditText dirText = findViewById(R.id.editTextDir);
        Game game = new Game();

        int [] gridMap = {R.id.gridSpot00,R.id.gridSpot01,R.id.gridSpot02,R.id.gridSpot03,R.id.gridSpot04,R.id.gridSpot05,R.id.gridSpot06,R.id.gridSpot07,R.id.gridSpot08,R.id.gridSpot09,
                R.id.gridSpot10,R.id.gridSpot11,R.id.gridSpot12,R.id.gridSpot13,R.id.gridSpot14,R.id.gridSpot15,R.id.gridSpot16,R.id.gridSpot17,R.id.gridSpot18,R.id.gridSpot19,
                R.id.gridSpot20,R.id.gridSpot21,R.id.gridSpot22,R.id.gridSpot23,R.id.gridSpot24,R.id.gridSpot25,R.id.gridSpot26,R.id.gridSpot27,R.id.gridSpot28,R.id.gridSpot29,
                R.id.gridSpot30,R.id.gridSpot31,R.id.gridSpot32,R.id.gridSpot33,R.id.gridSpot34,R.id.gridSpot35,R.id.gridSpot36,R.id.gridSpot37,R.id.gridSpot38,R.id.gridSpot39,
                R.id.gridSpot40,R.id.gridSpot41,R.id.gridSpot42,R.id.gridSpot43,R.id.gridSpot44,R.id.gridSpot45,R.id.gridSpot46,R.id.gridSpot47,R.id.gridSpot48,R.id.gridSpot49,
                R.id.gridSpot50,R.id.gridSpot51,R.id.gridSpot52,R.id.gridSpot53,R.id.gridSpot54,R.id.gridSpot55,R.id.gridSpot56,R.id.gridSpot57,R.id.gridSpot58,R.id.gridSpot59,
                R.id.gridSpot60,R.id.gridSpot61,R.id.gridSpot62,R.id.gridSpot63,R.id.gridSpot64,R.id.gridSpot65,R.id.gridSpot66,R.id.gridSpot67,R.id.gridSpot68,R.id.gridSpot69,
                R.id.gridSpot70,R.id.gridSpot71,R.id.gridSpot72,R.id.gridSpot73,R.id.gridSpot74,R.id.gridSpot75,R.id.gridSpot76,R.id.gridSpot77,R.id.gridSpot78,R.id.gridSpot79,
                R.id.gridSpot80,R.id.gridSpot81,R.id.gridSpot82,R.id.gridSpot83,R.id.gridSpot84,R.id.gridSpot85,R.id.gridSpot86,R.id.gridSpot87,R.id.gridSpot88,R.id.gridSpot89,
                R.id.gridSpot90,R.id.gridSpot91,R.id.gridSpot92,R.id.gridSpot93,R.id.gridSpot94,R.id.gridSpot95,R.id.gridSpot96,R.id.gridSpot97,R.id.gridSpot98,R.id.gridSpot99
        };

        Button btn_place = findViewById(R.id.btn_place);
        btn_place.setOnClickListener((v)->{
            int r = Integer.parseInt(rowText.getText().toString());
            int c = Integer.parseInt(colText.getText().toString());
            String dir = dirText.getText().toString();
            System.out.println(dir);
            Ship battleship = new Battleship();
            game.addShip(battleship,r-1,c-1,dir,false);
            int [][] grid = game.getActivePlayer().getLower().getGrid();
            for(int i=0; i<10;i++){
                for(int j=0; j<10;j++){
                    if(grid[i][j] > 1){
                        System.out.println(i);
                        System.out.println(j);
                        int index = 10*i + j;
                        ImageView square = findViewById(gridMap[index]);
                        square.setVisibility(ImageView.VISIBLE);
                    }
                }
            }
//            if(r > 0 && r <= 10 && c > 0 && c <= 10){
//                int index = 10*(r-1) + (c-1);
//                ImageView square = findViewById(gridMap[index]);
//                square.setVisibility(ImageView.VISIBLE);
//            }
        });


    }
}