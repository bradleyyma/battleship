package edu.colorado.caterpillars.activities;


import static org.junit.Assert.*;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import androidx.test.core.app.ActivityScenario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import edu.colorado.caterpillars.R;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class PlaceShipActivityTest {

    private ActivityScenario<PlaceShipActivity> scenario;
    private EditText row, col, dir;

    @Before
    public void createActivity(){
        scenario = ActivityScenario.launch(PlaceShipActivity.class);
    }

    @After
    public void tearDown(){
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

//    @Test
//    public void addShipWithNoRowOrCol(){
//        scenario.onActivity((activity -> {
//            shadowOf(getMainLooper()).idle();
//            activity.findViewById(R.id.btn_place).performClick();
//        }));
//    }

//    @Test
//    public void addValidShip(){
//        scenario.onActivity((activity -> {
//            shadowOf(getMainLooper()).idle();
//            row = activity.findViewById(R.id.editTextRow);
//            row.setText("1");
//            col = activity.findViewById((R.id.editTextColumn));
//            dir = activity.findViewById((R.id.editTextDir));
//            col.setText("3");
//            dir.setText("E");
//            activity.findViewById(R.id.btn_place).performClick();
//        }));
//    }
//
//    @Test
//    public void addInvalidShip(){
//        scenario.onActivity((activity -> {
//            row = activity.findViewById(R.id.editTextRow);
//            row.setText("1");
//            col = activity.findViewById((R.id.editTextColumn));
//            dir = activity.findViewById((R.id.editTextDir));
//            col.setText("3");
//            dir.setText("E");
//            activity.findViewById(R.id.btn_place).performClick();
//            row.setText("1");
//            col.setText("3");
//            dir.setText("E");
//            activity.findViewById(R.id.btn_place).performClick();
//        }));
//
//    }

    @Test
    public void addingAllShips(){
        scenario.onActivity((activity -> {
            row = activity.findViewById(R.id.editTextRow);
            col = activity.findViewById((R.id.editTextColumn));
            dir = activity.findViewById((R.id.editTextDir));
            Button btn = activity.findViewById((R.id.btn_place));
            row.setText("1");
            btn.performClick();
            row.setText("1");
            col.setText("1");
            dir.setText("E");
            btn.performClick();
            row.setText("1"); // invalid
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
        }));
        scenario.close();

    }
}