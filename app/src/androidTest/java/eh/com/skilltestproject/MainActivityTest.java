package eh.com.skilltestproject;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import eh.com.skilltestproject.main.MainActivity;

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


   /* @Test
    public void testCaseRecyclerItemClickIntent()
    {
        onView(ViewMatchers.withId(R.id.item_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,click()))
                .check(matches(ViewMatchers.withId(R.id.activity_map)));
    }*/

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






}
