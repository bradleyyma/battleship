package edu.colorado.caterpillars.activities;


import static org.junit.Assert.*;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import edu.colorado.caterpillars.fleet.ships.Minesweeper;
import edu.colorado.caterpillars.main.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import edu.colorado.caterpillars.R;

import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class PlaceShipActivityTest {

    @Rule
    public ActivityScenarioRule<PlaceShipActivity> rule = new ActivityScenarioRule<>(PlaceShipActivity.class);
    private ActivityScenario<PlaceShipActivity> scenario;
    private EditText row, col, dir;

    @Before
    public void createActivity(){
        Game.endGame();
        scenario = rule.getScenario();
    }

    @After
    public void tearDown(){
        Game.endGame();
        scenario.close();
    }

    @Test
    public void addShipWithNoDirection(){
        scenario.onActivity((activity -> {
            row = activity.findViewById(R.id.editTextRow);
            row.setText("1");
            col = activity.findViewById((R.id.editTextColumn));
            col.setText("3");
            activity.findViewById(R.id.btn_place).performClick();
        }));
    }

    @Test
    public void addShipWithNoRowOrCol(){
        scenario.onActivity((activity -> activity.findViewById(R.id.btn_place).performClick()));
    }

    @Test
    public void addValidShip(){
        scenario.onActivity((activity -> {
            row = activity.findViewById(R.id.editTextRow);
            row.setText("1");
            col = activity.findViewById((R.id.editTextColumn));
            dir = activity.findViewById((R.id.editTextDir));
            col.setText("3");
            dir.setText("E");
            activity.findViewById(R.id.btn_place).performClick();
        }));
    }

    @Test
    public void addInvalidShip(){
        scenario.onActivity((activity -> {
            row = activity.findViewById(R.id.editTextRow);
            row.setText("1");
            col = activity.findViewById((R.id.editTextColumn));
            dir = activity.findViewById((R.id.editTextDir));
            col.setText("3");
            dir.setText("E");
            activity.findViewById(R.id.btn_place).performClick();
            row.setText("1");
            col.setText("3");
            dir.setText("E");
            activity.findViewById(R.id.btn_place).performClick();
        }));

    }

    @Test
    public void createActivityWithShip(){
        Game game = Game.getInstance();
        game.addShip(new Minesweeper(), 0, 0, "E", false);
        scenario = ActivityScenario.launch(PlaceShipActivity.class);

    }

    @Test
    public void addingAllShips(){
        scenario.onActivity(activity -> {
            row = activity.findViewById(R.id.editTextRow);
            col = activity.findViewById((R.id.editTextColumn));
            dir = activity.findViewById((R.id.editTextDir));
            Button btn = activity.findViewById((R.id.btn_place));
            row.setText("1");
            col.setText("1");
            dir.setText("E");
            btn.performClick();
            row.setText("2"); // Submarine
            col.setText("1");
            dir.setText("E");
            btn.performClick();
            row.setText("4");
            col.setText("1");
            dir.setText("E");
            btn.performClick();
            row.setText("5");
            col.setText("1");
            dir.setText("E");
            btn.performClick();
            Intent expectedIntent = new Intent(activity, SwapPlayerActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        });
    }

    @Test
    public void finishedPlacingShip(){
        Game game = Game.getInstance();
        game.addShip(game.getNextShip(), 0, 0, "E", false);
        game.addShip(game.getNextShip(), 1, 0, "E", false);
        game.addShip(game.getNextShip(), 3, 0, "E", false);
        game.addShip(game.getNextShip(), 4, 0, "E", false);
        scenario = ActivityScenario.launch(PlaceShipActivity.class);
        scenario.onActivity(activity -> {
            Intent expectedIntent = new Intent(activity, SwapPlayerActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        });
    }


}