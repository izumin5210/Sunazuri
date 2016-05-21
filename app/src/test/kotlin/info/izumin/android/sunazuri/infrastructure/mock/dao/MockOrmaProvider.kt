package info.izumin.android.sunazuri.infrastructure.mock.dao;

import android.content.Context
import info.izumin.android.sunazuri.infrastructure.dao.OrmaProvider
import info.izumin.android.sunazuri.infrastructure.entity.OrmaDatabase

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockOrmaProvider(context: Context, override val db: OrmaDatabase): OrmaProvider(context = context) {
}

