package info.izumin.android.sunazuri.infrastructure.dao;

import info.izumin.android.sunazuri.infrastructure.entity.CategoryEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by izumin on 6/10/2016 AD.
 */
@Singleton
class CategoriesDao @Inject constructor(val orma: OrmaProvider) {

    fun findAll(team: TeamEntity): Observable<CategoryEntity> {
        return categoryRelation().selector().teamEq(team).executeAsObservable()
    }

    fun insert(entities: List<CategoryEntity>, team: TeamEntity) {
        entities.forEach { categoryRelation().teamEq(team).pathEq(it.path).upserter().execute(it) }
    }

    private fun categoryRelation() = orma.db.relationOfCategoryEntity()
}
