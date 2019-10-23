package eh.com.skilltestproject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import eh.com.skilltestproject.main.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
       // onView(withId(R.id.))
    }





}
