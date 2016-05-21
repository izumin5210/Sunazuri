package info.izumin.android.sunazuri.infrastructure.dao;

import android.content.Context
import info.izumin.android.sunazuri.infrastructure.entity.OrmaDatabase

/**
 * Created by izumin on 5/21/2016 AD.
 */
open class OrmaProvider(val context: Context) {
    open val db by lazy { OrmaDatabase.builder(context).build() }
}
