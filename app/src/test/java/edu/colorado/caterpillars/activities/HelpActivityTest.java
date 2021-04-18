package edu.colorado.caterpillars.activities;

import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import edu.colorado.caterpillars.R;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class HelpActivityTest {
    @Test
    public void onCreate(){
        ActivityScenario<HelpActivity> scenario = ActivityScenario.launch(HelpActivity.class);
//        scenario.onActivity((activity -> {
//        }));
    }
}