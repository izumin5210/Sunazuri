package info.izumin.android.sunazuri;

/**
 * Created by izumin on 6/8/2016 AD.
 */
public class TestSunazuri extends Sunazuri {
    public static final String TAG = TestSunazuri.class.getSimpleName();

    @Override
    protected void setupStetho() {
        // NOT setup Stetho for robolectric unit test
    }

    @Override
    protected void setupPicasso() {
        // Set no Picasso singleton instances on robolectric unit test
    }
}
