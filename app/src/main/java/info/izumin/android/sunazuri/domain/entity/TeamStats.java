package info.izumin.android.sunazuri.domain.entity;

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
public class TeamStats {
    public static final String TAG = TeamStats.class.getSimpleName();

    @PrimaryKey(auto = true, autoincrement = true)
    public long id;

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
}
