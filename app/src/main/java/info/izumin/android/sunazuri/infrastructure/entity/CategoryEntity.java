package info.izumin.android.sunazuri.infrastructure.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by izumin on 6/10/2016 AD.
 */
@Table(constraints = "UNIQUE (path, team)")
public class CategoryEntity {
    public static final String TAG = CategoryEntity.class.getSimpleName();

    @PrimaryKey(auto = true, autoincrement = true)
    public long id;

    @Column(indexed = true)
    public String path;

    @Column(indexed = true)
    public TeamEntity team;

    @Column
    public int count;
}
