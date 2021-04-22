package edu.colorado.caterpillars.activities;

import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import edu.colorado.caterpillars.R;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SwapPlayerActivityTest {
    @Test
    public void onCreate(){
        ActivityScenario<SwapPlayerActivity> scenario = ActivityScenario.launch(SwapPlayerActivity.class);
        scenario.onActivity((activity -> {
            activity.findViewById(R.id.btn_swap_confirm).performClick();
            Intent expectedIntent = new Intent(activity, PlaceShipActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }));
        scenario.close();
    }
}