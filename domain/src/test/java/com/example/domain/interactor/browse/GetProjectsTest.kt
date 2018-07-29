package com.example.domain.interactor.browse

import com.example.domain.executor.PostExecutionThread
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import com.example.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletes(){
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testSingle = getProjects.buildUseCaseSingle().test()
        testSingle.assertComplete()
    }

    @Test
    fun getProjectsResultData(){
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(projects))
        val testSingle = getProjects.buildUseCaseSingle().test()
        testSingle.assertValue(projects)
    }

    private fun stubGetProjects(single: Observable<List<Project>>){
        whenever(projectsRepository.getProjects()).thenReturn(single)
    }

}