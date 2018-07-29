package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import com.example.data.test.DataFactory
import com.example.data.test.ProjectFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ProjectsCacheDataStoreTest {

    private val cache = mock<ProjectsCache>()
    private val store = ProjectsCacheDataStore(cache)

    @Test
    fun getProjectsCompletes(){
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getProjects().test()
                .assertComplete()
    }

    @Test
    fun getProjectsReturnsData(){
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubGetProjects(Observable.just(data))

        store.getProjects().test()
                .assertValue(data)
    }

    @Test
    fun getProejectsCallsCacheSource(){
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjects(){
        stubSaveProjects(Completable.complete())
        stubSetLastCacheTime(Completable.complete())

        store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
                .assertComplete()
    }

    @Test
    fun saveProjectsCallsCacheSource(){
        stubSaveProjects(Completable.complete())
        stubSetLastCacheTime(Completable.complete())

        val data = listOf(ProjectFactory.makeProjectEntity())
        store.saveProjects(data).test()
        verify(cache).saveProjects(data)
    }

    @Test
    fun saveProjectsSetsLastCacheTime(){
        stubSaveProjects(Completable.complete())
        stubSetLastCacheTime(Completable.complete())

        store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        verify(cache).setLastCacheTime(any())
    }

    @Test
    fun clearProjectsCompletes(){
        stubClearProjects(Completable.complete())

        store.clearProjects().test()
                .assertComplete()
    }

    @Test fun clearProjectsCallsCacheSource(){
        stubClearProjects(Completable.complete())

        store.clearProjects().test()
        verify(cache).clearProjects()
    }


    @Test
    fun getBookmarkedProjectsCompletes(){
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getBookmarkedProjects().test()
                .assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsCacheSource(){
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getBookmarkedProjects().test()
        verify(cache).getBookmarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData(){
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubGetBookmarkedProjects(Observable.just(data))

        store.getBookmarkedProjects().test()
                .assertValue(data)
    }


    @Test
    fun setProjectAsBookmarkedCompletes(){
        stubSetProjectAsBookmarked(Completable.complete())

        store.setProjectAsBookmarked(DataFactory.randomString()).test()
                .assertComplete()
    }

    @Test
    fun setProjectAsBookmarkedCallsCacheSource(){
        stubSetProjectAsBookmarked(Completable.complete())

        val id = DataFactory.randomString()
        store.setProjectAsBookmarked(id).test()
        verify(cache).setProjectAsBookmarked(id)
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes(){
        stubSetProjectAsNotBookmarked(Completable.complete())

        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
                .assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCallsCacheSource(){
        stubSetProjectAsNotBookmarked(Completable.complete())
        val id = DataFactory.randomString()
        store.setProjectAsNotBookmarked(id).test()
        verify(cache).setProjectAsNotBookmarked(id)
    }

    private fun stubGetProjects(result: Observable<List<ProjectEntity>>){
        whenever(cache.getProjects())
                .thenReturn(result)
    }

    private fun stubSaveProjects(result: Completable){
        whenever(cache.saveProjects(any()))
                .thenReturn(result)
    }

    private fun stubSetLastCacheTime(result: Completable){
        whenever(cache.setLastCacheTime(any()))
                .thenReturn(result)
    }

    private fun stubClearProjects(result: Completable){
        whenever(cache.clearProjects())
                .thenReturn(result)
    }

    private fun stubGetBookmarkedProjects(result: Observable<List<ProjectEntity>>){
        whenever(cache.getBookmarkedProjects())
                .thenReturn(result)
    }

    private fun stubSetProjectAsBookmarked(result: Completable){
        whenever(cache.setProjectAsBookmarked(any()))
                .thenReturn(result)
    }

    private fun stubSetProjectAsNotBookmarked(result: Completable){
        whenever(cache.setProjectAsNotBookmarked(any()))
                .thenReturn(result)
    }

}