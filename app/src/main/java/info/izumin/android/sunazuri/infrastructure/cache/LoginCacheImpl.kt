package info.izumin.android.sunazuri.infrastructure.cache;

import info.izumin.android.sunazuri.infrastructure.pref.PrefsProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/25/2016 AD.
 */
@Singleton
internal class LoginCacheImpl @Inject constructor(
        val prefsProvider: PrefsProvider
) : LoginCache {

    override fun getUserId() = prefsProvider.defaultPrefs.userId

    override fun putUserId(userId: Long) {
        prefsProvider.defaultPrefs.userId = userId
    }

    override fun isUserCached() = prefsProvider.defaultPrefs.hasUserId()

    override fun getTeamId() = prefsProvider.defaultPrefs.teamName

    override fun putTeamName(teamName: String) {
        prefsProvider.defaultPrefs.teamName = teamName
    }

    override fun isTeamCached() = prefsProvider.defaultPrefs.hasTeamName()

    override fun removeAll() {
        prefsProvider.defaultPrefs.removeTeamName();
        prefsProvider.defaultPrefs.removeUserId();
    }
}
