package edu.colorado.caterpillars.activities;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import edu.colorado.caterpillars.R;
import org.junit.Test;
import org.junit.runner.RunWith;




@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void what(){
        onView(withId(R.id.btn_play)).perform(click());
        assert(true);
    }
}