package edu.colorado.caterpillars.activities;
import android.widget.*;
import edu.colorado.caterpillars.*;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.colorado.caterpillars.fleet.Ship;

import edu.colorado.caterpillars.main.Game;

import java.util.Locale;

public class PlaceShipActivity extends AppCompatActivity {

    EditText rowText,colText,dirText;
    TextView shipText,playerText;
    Game game;

    public int[] getGridMap(){
        return new int[]{R.id.gridSpot00, R.id.gridSpot01, R.id.gridSpot02, R.id.gridSpot03, R.id.gridSpot04, R.id.gridSpot05, R.id.gridSpot06, R.id.gridSpot07, R.id.gridSpot08, R.id.gridSpot09,
                R.id.gridSpot10, R.id.gridSpot11, R.id.gridSpot12, R.id.gridSpot13, R.id.gridSpot14, R.id.gridSpot15, R.id.gridSpot16, R.id.gridSpot17, R.id.gridSpot18, R.id.gridSpot19,
                R.id.gridSpot20, R.id.gridSpot21, R.id.gridSpot22, R.id.gridSpot23, R.id.gridSpot24, R.id.gridSpot25, R.id.gridSpot26, R.id.gridSpot27, R.id.gridSpot28, R.id.gridSpot29,
                R.id.gridSpot30, R.id.gridSpot31, R.id.gridSpot32, R.id.gridSpot33, R.id.gridSpot34, R.id.gridSpot35, R.id.gridSpot36, R.id.gridSpot37, R.id.gridSpot38, R.id.gridSpot39,
                R.id.gridSpot40, R.id.gridSpot41, R.id.gridSpot42, R.id.gridSpot43, R.id.gridSpot44, R.id.gridSpot45, R.id.gridSpot46, R.id.gridSpot47, R.id.gridSpot48, R.id.gridSpot49,
                R.id.gridSpot50, R.id.gridSpot51, R.id.gridSpot52, R.id.gridSpot53, R.id.gridSpot54, R.id.gridSpot55, R.id.gridSpot56, R.id.gridSpot57, R.id.gridSpot58, R.id.gridSpot59,
                R.id.gridSpot60, R.id.gridSpot61, R.id.gridSpot62, R.id.gridSpot63, R.id.gridSpot64, R.id.gridSpot65, R.id.gridSpot66, R.id.gridSpot67, R.id.gridSpot68, R.id.gridSpot69,
                R.id.gridSpot70, R.id.gridSpot71, R.id.gridSpot72, R.id.gridSpot73, R.id.gridSpot74, R.id.gridSpot75, R.id.gridSpot76, R.id.gridSpot77, R.id.gridSpot78, R.id.gridSpot79,
                R.id.gridSpot80, R.id.gridSpot81, R.id.gridSpot82, R.id.gridSpot83, R.id.gridSpot84, R.id.gridSpot85, R.id.gridSpot86, R.id.gridSpot87, R.id.gridSpot88, R.id.gridSpot89,
                R.id.gridSpot90, R.id.gridSpot91, R.id.gridSpot92, R.id.gridSpot93, R.id.gridSpot94, R.id.gridSpot95, R.id.gridSpot96, R.id.gridSpot97, R.id.gridSpot98, R.id.gridSpot99
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_ship);
        playerText = findViewById(R.id.playerPlaceShipTitle);
        rowText = findViewById(R.id.editTextRow);
        colText = findViewById(R.id.editTextColumn);
        dirText = findViewById(R.id.editTextDir);
        shipText = findViewById(R.id.currShip);
        game = Game.getInstance();
        playerText.setText(game.getActivePlayer().getName());
        if(game.getNextShip() == null){
            shipText.setText("None");
            Intent intent = new Intent(this, SwapPlayerActivity.class);
            startActivity(intent);
        }
        shipText.setText(game.getNextShip().getName());
        int [] gridMap = getGridMap();
        int[][] grid = game.getActivePlayer().getLower().getGrid();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] > 1) {
                    int index = 10 * i + j;
                    ImageView square = findViewById(gridMap[index]);
                    square.setVisibility(ImageView.VISIBLE);
                }
            }
        }

        Button btn_place = findViewById(R.id.btn_place);
        btn_place.setOnClickListener((v) -> addShip());


    }


    public void addShip() {
        int [] gridMap = getGridMap();
        try {
            int r = Integer.parseInt(rowText.getText().toString());
            int c = Integer.parseInt(colText.getText().toString());
            String dir = dirText.getText().toString().toUpperCase();
            if (dir.matches("")) {
                Toast.makeText(this, "Please enter data in all fields before adding a ship",
                        Toast.LENGTH_LONG).show();
            }else {

                try {
                    Ship ship = game.getNextShip();
                    game.addShip(ship,r - 1, c - 1, dir, false);
                    ship = game.getNextShip();
                    if(ship == null){
                        shipText.setText("None");
                        Intent intent = new Intent(this, SwapPlayerActivity.class);
                        startActivity(intent);
                    }else{
                        shipText.setText(ship.getName());
                    }


                    int[][] grid = game.getActivePlayer().getLower().getGrid();
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            int id = grid[i][j];
                            if (id > 1) {
                                int index = 10 * i + j;
                                ImageView square = findViewById(gridMap[index]);
                                square.setVisibility(ImageView.VISIBLE);
                                if(id % 2 == 0){
                                    square.setImageResource(R.drawable.ship_spot);
                                }else{
                                    square.setImageResource(R.drawable.captain_spot);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Cannot add ship. Overlaps with the edge of the grid or with another ship.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(this, "Please enter data in all fields before adding a ship",
                    Toast.LENGTH_LONG).show();
        }
    }
}