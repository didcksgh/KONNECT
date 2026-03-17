package com.example.konnect;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.konnect.dashboard.DashboardActivity;
import com.example.konnect.dashboard.FriendsFragment;
import com.example.konnect.dashboard.ProfileFragment;
import com.example.konnect.entry.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;


@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class JaysonAcostaTest {

    private static final int SIMULATED_DELAY_MS = 500;

    /* Login the user
     * Make sure that the Username and Email get put into the user class
     * Put into ProfileFragment
     */
   @Test
    public void login(){
       try (ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class)){
           String username = "Jhi";
           String password = "Jayson890";
           String email = "Jayson@iastate.edu";

           onView(withId(R.id.Login_Username)).perform(typeText(username), closeSoftKeyboard());
           onView(withId(R.id.Login_Password)).perform(typeText(password), closeSoftKeyboard());
           onView(withId(R.id.LoginButton)).perform(click());

           try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException ignored) {}

           onView(withId(R.id.ProfileUsername)).check(matches(withText(endsWith(username))));
           onView(withId(R.id.ProfileEmail)).check(matches(withText(endsWith(email))));
       }
   }

   /* Checks if the fragments are switching when clicked inside of Dashboard Activity */
    @Test
    public void switchFragments(){
        try (ActivityScenario<DashboardActivity> scenario = ActivityScenario.launch(DashboardActivity.class)) {

            onView(ViewMatchers.withId(R.id.profile)).perform(click());
            onView(ViewMatchers.withId(R.id.fragment_container)).check(matches(isDisplayed()));

            scenario.onActivity(activity -> {
                Fragment currentFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                assertTrue(currentFragment instanceof ProfileFragment);
            });

            onView(ViewMatchers.withId(R.id.friends)).perform(click());
            onView(ViewMatchers.withId(R.id.fragment_container)).check(matches(isDisplayed()));

            scenario.onActivity(activity -> {
                Fragment currentFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                assertTrue(currentFragment instanceof FriendsFragment);
            });
        }
    }

    /* Checks to see if the methods of creating constraint layouts are working properly */
    @Test
    public void friendsFragment(){
        try (FragmentScenario<FriendsFragment> scenario = FragmentScenario.launchInContainer(FriendsFragment.class)){
            scenario.onFragment(fragment -> {

                ConstraintLayout layoutFR = fragment.createFRLayout(new LinearLayout(fragment.getContext()), new LinearLayout(fragment.getContext()), "user1", "User One", 1, false);
                ConstraintLayout layoutF = fragment.createFLayout("user2", "User Two", 2);
                ConstraintLayout layoutG = fragment.createGLayout("Group One", 1);

                assertNotNull(layoutFR);
                assertNotNull(layoutF);
                assertNotNull(layoutG);
            });
        }
    }

    /* Adds new views into containers
     * Checks if the containers are visible
     * Clicks on the containers
     * Checks if they are gone
     */
    @Test
    public void collapseContainers(){
        try (FragmentScenario<FriendsFragment> scenario = FragmentScenario.launchInContainer(FriendsFragment.class)) {
            scenario.onFragment(fragment -> {
                LinearLayout containerFR = Objects.requireNonNull(fragment.getView()).findViewById(R.id.Container_FR);
                LinearLayout containerF = Objects.requireNonNull(fragment.getView()).findViewById(R.id.Container_F);
                LinearLayout containerG = Objects.requireNonNull(fragment.getView()).findViewById(R.id.Container_G);

                containerFR.addView(fragment.createFRLayout(new LinearLayout(fragment.getContext()), new LinearLayout(fragment.getContext()), "user1", "User One", 1, false));
                containerF.addView(fragment.createFLayout("user2", "User Two", 2));
                containerG.addView(fragment.createGLayout("Group One", 1));
            });

            onView(withId(R.id.Container_FR)).check(matches(isDisplayed()));
            onView(withId(R.id.Container_F)).check(matches(isDisplayed()));
            onView(withId(R.id.Container_G)).check(matches(isDisplayed()));

            onView(withId(R.id.ImageView_VFR)).perform(click());
            onView(withId(R.id.ImageView_VF)).perform(click());
            onView(withId(R.id.ImageView_G)).perform(click());

            onView(withId(R.id.Container_FR)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            onView(withId(R.id.Container_F)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            onView(withId(R.id.Container_G)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        }
    }
}
