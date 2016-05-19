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
public class TeamEntity {
    public static final String TAG = TeamEntity.class.getSimpleName();

    @PrimaryKey(auto = false)
    @Column(indexed = true)
    public String name;

    @Column
    public String privacy;

    @Column
    public String description;

    @Column
    public String icon;

    @Column(indexed = true)
    public AuthorizedUserEntity user;
}
