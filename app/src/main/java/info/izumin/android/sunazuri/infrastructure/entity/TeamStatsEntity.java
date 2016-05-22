package info.izumin.android.sunazuri.infrastructure.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;
import com.github.gfx.static_gson.annotation.JsonSerializable;
import com.google.gson.FieldNamingPolicy;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Table
@JsonSerializable(fieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
public class TeamStatsEntity {
    public static final String TAG = TeamStatsEntity.class.getSimpleName();

    @Column
    public int members;

    @Column
    public int posts;

    @Column
    public int comments;

    @Column
    public int stars;

    @Column
    public int dailyActiveUsers;

    @Column
    public int weeklyActiveUsers;

    @Column
    public int monthlyActiveUsers;

    @PrimaryKey(auto = false)
    @Column
    public TeamEntity team;
}
