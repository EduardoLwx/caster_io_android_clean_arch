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

class UnbookmarkProjectTest {
    lateinit var unbookmarkProject: UnbookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        unbookmarkProject = UnbookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkProjectCompletes(){
        stubUnbookmarkProject(Completable.complete())

        unbookmarkProject.buildUseCaseCompletable(
            UnbookmarkProject.Params.forProject(ProjectDataFactory.makeProject().id)
        ).test().assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectThrowsException(){
        unbookmarkProject.buildUseCaseCompletable().test()
    }

    fun stubUnbookmarkProject(completable: Completable){
        whenever(projectsRepository.unbookmarkProject(any()))
                .thenReturn(completable)
    }
}