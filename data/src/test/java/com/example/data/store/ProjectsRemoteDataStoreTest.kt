package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsRemote
import com.example.data.test.DataFactory
import com.example.data.test.ProjectFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsRemoteDataStoreTest {

    private val remote = mock<ProjectsRemote>()
    private val store = ProjectsRemoteDataStore(remote)

    @Test
    fun getProjectsCompletes(){
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getProjects().test()
                .assertComplete()
    }

    @Test
    fun getProjectsCallsRemoteSource(){
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getProjects().test()
        verify(remote).getProjects()
    }

    @Test
    fun getProjectsReturnsData(){
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubGetProjects(Observable.just(data))

        store.getProjects().test()
                .assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException(){
        store.saveProjects(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException(){
        store.clearProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException(){
        store.getBookmarkedProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowsException(){
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowsException(){
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }


    private fun stubGetProjects(result: Observable<List<ProjectEntity>>){
        whenever(remote.getProjects())
                .thenReturn(result)
    }

}