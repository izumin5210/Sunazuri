package info.izumin.android.sunazuri.infrastructure.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;
import com.github.gfx.static_gson.annotation.JsonSerializable;
import com.google.gson.FieldNamingPolicy;

import java.util.Date;

/**
 * Created by izumin on 5/20/2016 AD.
 */
@Table
@JsonSerializable(fieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
public class AuthorizedUserEntity {
    public static final String TAG = AuthorizedUserEntity.class.getSimpleName();

    @PrimaryKey(auto = false)
    @Column
    public long id;

    @Column
    public String name;

    @Column(indexed = true)
    public String screenName;

    @Column
    public Date createdAt;

    @Column
    public Date updatedAt;

    @Column
    public String icon;

    @Column
    public String email;
}
