package com.example.domain.interactor.browse

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.SingleUseCase
import com.example.domain.model.Project
import com.example.domain.repository.ProjectsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(private val projectsRepository: ProjectsRepository,
                                                postExecutionThread: PostExecutionThread)
    : SingleUseCase <List<Project>, Nothing>(postExecutionThread)  {

    override fun buildUseCaseSingle(params: Nothing?): Single<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}