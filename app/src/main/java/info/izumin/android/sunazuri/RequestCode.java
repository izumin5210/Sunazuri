package info.izumin.android.sunazuri;

import rx.Observable;

/**
 * Created by izumin on 5/21/2016 AD.
 */
public enum RequestCode {
    OAUTH(100)
    ;

    public final int code;

    RequestCode(int code) {
        this.code = code;
    }

    public RequestCode valueOf(int code) {
        return Observable.from(values()).filter(rc -> rc.code == code).toBlocking().first();
    }
}
