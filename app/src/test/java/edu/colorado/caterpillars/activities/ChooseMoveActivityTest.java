package edu.colorado.caterpillars.activities;

import android.content.Intent;
import android.widget.Button;
import androidx.test.core.app.ActivityScenario;
import edu.colorado.caterpillars.R;
import edu.colorado.caterpillars.main.Game;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class ChooseMoveActivityTest {
    @After
    public void tearDown() {
        Game.endGame();
    }
    @Test
    public void onCreate(){
        ActivityScenario<ChooseMoveActivity> scenario = ActivityScenario.launch(ChooseMoveActivity.class);
        scenario.close();
    }

    @Test
    public void openAttackActivity(){
        ActivityScenario<ChooseMoveActivity> scenario = ActivityScenario.launch(ChooseMoveActivity.class);
        scenario.onActivity(activity -> {
            Button btn_atk = activity.findViewById(R.id.btn_atk);
            btn_atk.performClick();
            Intent expectedIntent = new Intent(activity, AttackActivity.class);
            Intent actual = shadowOf(activity).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        });

        scenario.close();
    }
}