//package com.example.domain.interactor.browse
//
//import com.example.domain.executor.PostExecutionThread
//import com.example.domain.model.Project
//import com.example.domain.repository.ProjectsRepository
//import com.example.domain.test_.ProjectDataFactory
//import com.nhaarman.mockito_kotlin.whenever
//import io.reactivex.Single
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.MockitoAnnotations
//
//
//class GetProjectsTest {
//
//    private lateinit var getProjects: GetProjects
//    @Mock lateinit var projectsRepository: ProjectsRepository
//    @Mock lateinit var postExecutionThread: PostExecutionThread
//
//    @Before
//    fun setup(){
//        MockitoAnnotations.initMocks(this)
//        getProjects = GetProjects(projectsRepository, postExecutionThread)
//    }
//
//    @Test
//    fun getProjectsCompletes(){
//        stubGetProjects(Single.just(ProjectDataFactory.makeProjectList(2)))
//        val testSingle = getProjects.buildUseCaseSingle().test()
//        testSingle.assertComplete()
//    }
//
//    @Test
//    fun getProjectsResultData(){
//        val projects = ProjectDataFactory.makeProjectList(2)
//        stubGetProjects(Single.just(projects))
//        val testSingle = getProjects.buildUseCaseSingle().test()
//        testSingle.assertValue(projects)
//    }
//
//    private fun stubGetProjects(single: Single<List<Project>>){
//        whenever(projectsRepository.getProjects()).thenReturn(single)
//    }
//
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 1)
//    }
//
//}