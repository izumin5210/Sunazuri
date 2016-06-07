package info.izumin.android.sunazuri.infrastructure.mock.cache

import info.izumin.android.sunazuri.infrastructure.cache.LoginCache
import java.util.*

/**
 * Created by izumin on 6/7/2016 AD.
 */
class FakeLoginCache : LoginCache {
    enum class Key {
        USER_ID, TEAM_ID
    }

    val map = EnumMap<Key, Any>(Key::class.java)

    override fun getUserId() = map[Key.USER_ID] as Long

    override fun putUserId(userId: Long) {
        map.put(Key.USER_ID, userId)
    }

    override fun getTeamId() = map[Key.TEAM_ID] as String

    override fun putTeamName(teamName: String?) {
        map.put(Key.TEAM_ID, teamName)
    }

    override fun removeAll() {
        map.clear()
    }

    override fun isUserCached() = map.containsKey(Key.USER_ID)

    override fun isTeamCached() = map.containsKey(Key.TEAM_ID)
}
