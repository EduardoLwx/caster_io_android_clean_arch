package com.example.domain.interactor.browse

import com.example.domain.executor.PostExecutionThread
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import com.example.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock lateinit var  projectsRepository: ProjectsRepository
    @Mock lateinit var  postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getBookmarkedCompletes(){
        stubGetbookmarkedProjects(ProjectDataFactory.makeProjectList(2))
        var testSingle = getBookmarkedProjects.buildUseCaseSingle().test()
        testSingle.assertComplete()
    }

    @Test
    fun getBookmarkedResults(){
        var list = ProjectDataFactory.makeProjectList(2)
        stubGetbookmarkedProjects(list)
        var testSingle = getBookmarkedProjects.buildUseCaseSingle().test()
        testSingle.assertValue(list)
    }

    private fun stubGetbookmarkedProjects(list: List<Project>){
        whenever(projectsRepository.getBookmarkedProjects())
                .thenReturn(Single.just(list))
    }
}