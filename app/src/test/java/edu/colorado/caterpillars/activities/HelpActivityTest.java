package edu.colorado.caterpillars.activities;

import androidx.test.core.app.ActivityScenario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class HelpActivityTest {
    @Test
    public void onCreate(){
        ActivityScenario<HelpActivity> scenario = ActivityScenario.launch(HelpActivity.class);
        scenario.close();
//        scenario.onActivity((activity -> {
//        }));
    }
}