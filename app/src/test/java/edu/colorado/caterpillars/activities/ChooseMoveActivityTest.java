package edu.colorado.caterpillars.activities;

import androidx.test.core.app.ActivityScenario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class ChooseMoveActivityTest {
    @Test
    public void onCreate(){
        ActivityScenario<ChooseMoveActivity> scenario = ActivityScenario.launch(ChooseMoveActivity.class);
        scenario.close();
    }
}