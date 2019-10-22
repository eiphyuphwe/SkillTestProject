package eh.com.skilltestproject;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import eh.com.skilltestproject.utils.Utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("eh.com.skilltestproject", appContext.getPackageName());
    }

    @Test
    public void readFileAsset() throws Exception
    {
        Context ctx = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Utilities utilities = new Utilities();
        assertNotNull(utilities.loadDataFromAsset(ctx));



    }








}
