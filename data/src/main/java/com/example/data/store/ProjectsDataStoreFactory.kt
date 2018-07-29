package com.example.data.store

import com.example.data.repository.ProjectsDataStore

open class ProjectsDataStoreFactory(
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    open fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean)
            : ProjectsDataStore {
        return if (projectsCached && !cacheExpired){
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    open fun getCacheDataStore() : ProjectsDataStore {
        return projectsCacheDataStore
    }
}