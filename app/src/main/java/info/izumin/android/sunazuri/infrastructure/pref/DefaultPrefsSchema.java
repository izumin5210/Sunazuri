package info.izumin.android.sunazuri.infrastructure.pref;

import com.rejasupotaro.android.kvs.annotations.Key;
import com.rejasupotaro.android.kvs.annotations.Table;

/**
 * Created by izumin on 5/25/2016 AD.
 */
@Table(name = "info.izumin.android.sunazuri_preferences")
public interface DefaultPrefsSchema {
    @Key(name = "user_id")
    long userId = -1;

    @Key(name = "team_name")
    String teamName = "";
}
