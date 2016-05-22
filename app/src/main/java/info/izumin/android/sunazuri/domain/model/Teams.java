package info.izumin.android.sunazuri.domain.model;

import info.izumin.android.sunazuri.domain.entity.Team;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public class Teams extends ArrayList<Team> {
    public static final String TAG = Teams.class.getSimpleName();

    public Teams() {
    }

    public Teams(Collection<? extends Team> collection) {
        super(collection);
    }
}
