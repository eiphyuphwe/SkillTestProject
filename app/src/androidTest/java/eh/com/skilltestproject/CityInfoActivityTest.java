package eh.com.skilltestproject;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CityInfoActivityTest {

    @Rule
    public ActivityTestRule<CityInfoActivity> activityTestRule =
            new ActivityTestRule<>(CityInfoActivity.class);

    @Test
    public void checkTextViewDisplay()
    {
        onView(withId(R.id.txtCountryName))
                .check(matches(isDisplayed()));
        onView(withId(R.id.txtLatitude))
                .check(matches(isDisplayed()));
        onView(withId(R.id.txtLon))
                .check(matches(isDisplayed()));
    }

}
