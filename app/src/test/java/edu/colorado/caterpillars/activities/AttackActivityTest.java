package edu.colorado.caterpillars.activities;

import android.content.Intent;
import android.widget.*;
import androidx.test.core.app.ActivityScenario;
import edu.colorado.caterpillars.R;
import edu.colorado.caterpillars.fleet.ships.Minesweeper;
import edu.colorado.caterpillars.main.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class AttackActivityTest {
    private ActivityScenario<AttackActivity> scenario;
    @Before
    public void setUp(){
        scenario = ActivityScenario.launch(AttackActivity.class);
    }
    @After
    public void tearDown() {
        Game.endGame();
        scenario.close();
    }

    @Test
    public void swapPlayerIntention(){
        scenario.onActivity(activity -> {
            Button btn_cont = activity.findViewById(R.id.btn_continue);
            btn_cont.performClick();
            Intent expectedIntent = new Intent(activity, SwapPlayerActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        });
    }

    @Test
    public void attackTest(){
        scenario.onActivity(activity -> {
            EditText row = activity.findViewById(R.id.editTextAtkRow);
            EditText col = activity.findViewById(R.id.editTextAtkColumn);
            row.setText("1");
            col.setText("1");
            Button btn_atk = activity.findViewById(R.id.btn_atk);
            btn_atk.performClick();
        });
    }

    @Test
    public void attackHit(){
        Game game = Game.getInstance();
        game.swapPlayers();
        assertEquals(game.getActivePlayer().getName(), "Player 2");
        game.addShip(game.getNextShip(), 0, 0, "E", false);
        game.swapPlayers();
        scenario.onActivity(activity -> {
            EditText row = activity.findViewById(R.id.editTextAtkRow);
            EditText col = activity.findViewById(R.id.editTextAtkColumn);
            row.setText("1");
            col.setText("1");
            Button btn_atk = activity.findViewById(R.id.btn_atk);
            btn_atk.performClick();
        });
    }

    @Test
    public void invalidAttack(){
        scenario.onActivity(activity -> {
            EditText row = activity.findViewById(R.id.editTextAtkRow);
            EditText col = activity.findViewById(R.id.editTextAtkColumn);
            Button btn_atk = activity.findViewById(R.id.btn_atk);
            btn_atk.performClick();
            row.setText("-1");
            col.setText("1");
            btn_atk.performClick();
        });
    }

    @Test
    public void endOfGame(){
        Game game = Game.getInstance();
        game.addShip(new Minesweeper(), 0, 0, "E", false);
        game.swapPlayers();
        game.addShip(new Minesweeper(), 0, 0, "E", false);
        scenario.onActivity(activity -> {
            EditText row = activity.findViewById(R.id.editTextAtkRow);
            EditText col = activity.findViewById(R.id.editTextAtkColumn);
            Button btn_atk = activity.findViewById(R.id.btn_atk);

            row.setText("1");
            col.setText("1");
            btn_atk.performClick();
            TextView winText = activity.findViewById(R.id.textYouWin);
            assertEquals(TextView.VISIBLE, winText.getVisibility());
            activity.findViewById(R.id.btn_continue).performClick();
            Intent expectedIntent = new Intent(activity, MainActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        });
    }



}