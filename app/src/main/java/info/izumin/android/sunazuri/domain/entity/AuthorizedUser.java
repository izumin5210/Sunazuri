package info.izumin.android.sunazuri.domain.entity;

import java.util.Date;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public class AuthorizedUser {
    public static final String TAG = AuthorizedUser.class.getSimpleName();

    public final long id;
    public final String name;
    public final String screenName;
    public final Date createdAt;
    public final Date updatedAt;
    public final String icon;
    public final String email;

    public final AccessToken token;

    public AuthorizedUser(long id,
                          String name,
                          String screenName,
                          Date createdAt,
                          Date updatedAt,
                          String icon,
                          String email,
                          AccessToken token) {
        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.icon = icon;
        this.email = email;
        this.token = token;
    }
}
