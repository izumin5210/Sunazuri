package info.izumin.android.sunazuri.infrastructure.cache;

/**
 * Created by izumin on 5/25/2016 AD.
 */
public interface LoginCache {
    long getUserId();
    void putUserId(long userId);
    String getTeamId();
    void putTeamName(String teamName);
    void removeAll();
    boolean isUserCached();
    boolean isTeamCached();
}
