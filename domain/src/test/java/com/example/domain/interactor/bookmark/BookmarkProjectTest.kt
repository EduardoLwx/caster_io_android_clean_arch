package com.example.domain.interactor.bookmark

import com.example.domain.executor.PostExecutionThread
import com.example.domain.repository.ProjectsRepository
import com.example.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkProjectTest {

    private lateinit var bookmarkProject: BookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes(){
        stubBookmarkProject(Completable.complete())
        var testCompletable = bookmarkProject.buildUseCaseCompletable(
                BookmarkProject.Params.forProject(ProjectDataFactory.makeProject().id)
        ).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException(){
        bookmarkProject.buildUseCaseCompletable().test()
    }


    private fun stubBookmarkProject(complete: Completable){
        whenever(projectsRepository.bookmarkProject(any()))
                .thenReturn(complete)
    }

}