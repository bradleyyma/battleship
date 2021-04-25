package edu.colorado.caterpillars.activities;

import androidx.test.core.app.ActivityScenario;
import edu.colorado.caterpillars.main.Game;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class HelpActivityTest {
    @After
    public void tearDown() {
        Game.endGame();
    }
    @Test
    public void onCreate(){
        ActivityScenario<HelpActivity> scenario = ActivityScenario.launch(HelpActivity.class);
        scenario.close();
    }
}