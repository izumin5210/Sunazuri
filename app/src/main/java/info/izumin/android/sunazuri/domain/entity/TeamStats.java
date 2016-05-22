package info.izumin.android.sunazuri.domain.entity;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public class TeamStats {
    public static final String TAG = TeamStats.class.getSimpleName();

    public final int members;
    public final int posts;
    public final int comments;
    public final int stars;
    public final int dailyActiveUsers;
    public final int weeklyActiveUsers;
    public final int monthlyActiveUsers;

    public TeamStats(int members,
                     int posts,
                     int comments,
                     int stars,
                     int dailyActiveUsers,
                     int weeklyActiveUsers,
                     int monthlyActiveUsers) {
        this.members = members;
        this.posts = posts;
        this.comments = comments;
        this.stars = stars;
        this.dailyActiveUsers = dailyActiveUsers;
        this.weeklyActiveUsers = weeklyActiveUsers;
        this.monthlyActiveUsers = monthlyActiveUsers;
    }
}
