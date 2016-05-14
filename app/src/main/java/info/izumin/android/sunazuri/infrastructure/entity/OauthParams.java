package info.izumin.android.sunazuri.infrastructure.entity;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public class OauthParams {
    public static final String TAG = OauthParams.class.getSimpleName();

    public final String endpoint;
    public final String clientId;
    public final String clientSecret;
    public final String redirectUri;
    public final String scope;
    public final String responseType;
    public final String authorizePath;
    public final String grantType;

    public OauthParams(String endpoint, String clientId, String clientSecret, String redirectUri, String scope, String responseType, String authorizePath, String grantType) {
        this.endpoint = endpoint;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.scope = scope;
        this.responseType = responseType;
        this.authorizePath = authorizePath;
        this.grantType = grantType;
    }

    public String getAuthorizeUri() {
        return endpoint + authorizePath
                + "?client_id="     + clientId
                + "&redirect_uri="  + redirectUri
                + "&scope="         + scope
                + "&response_type=" + responseType;
    }
}
