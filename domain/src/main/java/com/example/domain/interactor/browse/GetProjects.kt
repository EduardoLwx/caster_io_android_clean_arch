package com.example.domain.interactor.browse

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.SingleUseCase
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<Project>, Nothing>(postExecutionThread) {


    public override fun buildUseCaseSingle(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}