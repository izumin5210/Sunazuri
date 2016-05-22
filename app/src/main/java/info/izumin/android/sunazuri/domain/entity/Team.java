package info.izumin.android.sunazuri.domain.entity;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public class Team {
    public static final String TAG = Team.class.getSimpleName();

    public final String name;
    public final String privacy;
    public final String description;
    public final String icon;
    public final long userId;

    public final TeamStats stats;

    public Team(String name,
                String privacy,
                String description,
                String icon,
                long userId, TeamStats stats) {
        this.name = name;
        this.privacy = privacy;
        this.description = description;
        this.icon = icon;
        this.userId = userId;
        this.stats = stats;
    }

    public Team(String name,
                String privacy,
                String description,
                String icon,
                long userId) {
        this(name, privacy, description, icon, userId, null);
    }
}
