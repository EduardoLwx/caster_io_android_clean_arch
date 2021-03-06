package com.example.data.store

import com.example.data.mapper.ProjectMapper
import com.example.data.repository.ProjectsCache
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,  //TODO: access this in this point. It`s strange.
        private val factory: ProjectsDataStoreFactory): ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(), cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                }).flatMap {
                    factory.getDataStore(it.first, it.second).getProjects()
                }.flatMap { projects ->
                    factory.getCacheDataStore().saveProjects(projects) //TODO: Just resaving? And If the projetcs already been on cache
                            .andThen(Observable.just(projects))
                }.map { list ->
                    list.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects().map { list ->
            list.map {
                mapper.mapFromEntity(it)
            }
        }
    }

}