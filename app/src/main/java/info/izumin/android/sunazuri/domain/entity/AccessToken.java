package info.izumin.android.sunazuri.domain.entity;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public class AccessToken {
    public static final String TAG = AccessToken.class.getSimpleName();

    public final String accessToken;
    public final String tokenType;
    public final String scope;

    public AccessToken(String accessToken,
                       String tokenType,
                       String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;
    }
}
