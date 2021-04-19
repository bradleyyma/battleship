package edu.colorado.caterpillars.activities;

import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import edu.colorado.caterpillars.R;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void correctIntentForPlayButton(){
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity((activity -> {
            activity.findViewById(R.id.btn_play).performClick();
            Intent expectedIntent = new Intent(activity, P1PlaceShipActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }));
    }

    @Test
    public void correctIntentForHelpButton(){
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity((activity -> {
            activity.findViewById(R.id.btn_help).performClick();
            Intent expectedIntent = new Intent(activity, HelpActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }));
    }


}