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
public class AccessTokenEntity {
    public static final String TAG = AccessTokenEntity.class.getSimpleName();

    @Column
    public String accessToken;

    @Column
    public String tokenType;

    @Column
    public String scope;

    @PrimaryKey(auto = false)
    @Column
    public AuthorizedUserEntity user;
}
