package info.izumin.android.sunazuri;

import android.content.Context;
import com.facebook.stetho.Stetho;

/**
 * Created by izumin on 6/7/2016 AD.
 */
public class StethoWrapper {
    public static final String TAG = StethoWrapper.class.getSimpleName();

    private final Context context;

    public StethoWrapper(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setup() {
        Stetho.initializeWithDefaults(context);
    }
}
