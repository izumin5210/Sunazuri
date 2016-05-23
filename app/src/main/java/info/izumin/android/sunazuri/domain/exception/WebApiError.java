package info.izumin.android.sunazuri.domain.exception;

/**
 * Created by izumin on 5/23/2016 AD.
 */
public class WebApiError extends RuntimeException {
    public static final String TAG = WebApiError.class.getSimpleName();

    public final String error;

    public WebApiError(String error, String detailMessage) {
        super(detailMessage);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
