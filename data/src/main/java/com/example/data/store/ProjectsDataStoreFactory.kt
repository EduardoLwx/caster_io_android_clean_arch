package com.example.data.store

import com.example.data.repository.ProjectsDataStore

class ProjectsDataStoreFactory(
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean)
            : ProjectsDataStore {
        return if (projectsCached && !cacheExpired){
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    fun getCacheDataStore() : ProjectsDataStore {
        return projectsCacheDataStore
    }
}