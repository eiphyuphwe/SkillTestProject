package eh.com.skilltestproject;

import android.content.Intent;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import eh.com.skilltestproject.main.MainActivity;
import eh.com.skilltestproject.model.City;
import eh.com.skilltestproject.model.Coord;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {


    private String moreInfo;
    private static final String MESSAGE = "This is a test";
    private static final String PACKAGE_NAME = "eh.com.skilltestproject";


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Before
    public void initValidString()
    {
        moreInfo = " <u>More Info:</u>.";
    }

    @Test
    public void clickOnItemRecyclerView()
    {
        onView(withId(R.id.item_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));

    }

    @Test
    public void testCaseRecyclerItemViewDisplay()
    {
        onView(ViewMatchers.withId(R.id.item_list))
                .check(matches(withViewAtPosition(1, Matchers.allOf(
                        ViewMatchers.withId(R.id.lay_content), isDisplayed()))));
    }


   @Test
    public void testCaseRecyclerItemClickIntent()
    {
        City city = new City();
        city.set_id(1);
        city.setName("AA");
        city.setCountry("Test");
        city.setCoord(new Coord(1,1));
        Intent intent = new Intent();
        intent.putExtra("item_city",city);
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.item_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2,MyViewAction.clickChildViewWithId(R.id.txtMoreInfo)));
    }

    public Matcher<View> withViewAtPosition(final int position,final Matcher<View> itemMatcher)
    {
        return new BoundedMatcher<View,RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {

                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }




        public static class MyViewAction{

            public static ViewAction clickChildViewWithId(final int id)
            {
                return new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return null;
                    }

                    @Override
                    public void perform(UiController uiController, View view) {

                        View v = view.findViewById(id);
                        v.performClick();

                    }
                };
            }
        }


}
