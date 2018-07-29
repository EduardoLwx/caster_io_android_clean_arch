package com.example.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectsDataStoreFactoryTest {

    private val cacheStore = mock<ProjectsCacheDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()
    private val factory = ProjectsDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired(){
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenProjectsNotCached(){
        assertEquals(remoteStore, factory.getDataStore(false, false))
        assertEquals(remoteStore, factory.getDataStore(false, true))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }

}