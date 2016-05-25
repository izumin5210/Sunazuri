package info.izumin.android.sunazuri.infrastructure.pref;

import android.content.Context

/**
 * Created by izumin on 5/25/2016 AD.
 */
class PrefsProvider {
    lateinit var defaultPrefs: DefaultPrefs

    constructor(context: Context) {
        defaultPrefs = DefaultPrefs.get(context)
    }
}
