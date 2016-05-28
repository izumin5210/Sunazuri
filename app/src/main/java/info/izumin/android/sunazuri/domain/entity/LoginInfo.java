package info.izumin.android.sunazuri.domain.entity;

/**
 * Created by izumin on 5/26/2016 AD.
 */
public class LoginInfo {
    public static final String TAG = LoginInfo.class.getSimpleName();

    public final AuthorizedUser user;
    public final Team team;

    public LoginInfo(AuthorizedUser user, Team team) {
        this.user = user;
        this.team = team;
    }
}
