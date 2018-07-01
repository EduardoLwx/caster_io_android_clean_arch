//package com.example.domain.test_
//
//import com.example.domain.model.Project
//import com.example.domain.test_.BaseTypesFactory.randomBoolean
//import com.example.domain.test_.BaseTypesFactory.randomString
//
//object ProjectDataFactory {
//
//    fun makeProject(): Project {
//        return Project(randomString(), randomString(), randomString(), randomString(), randomString(),
//                randomString(), randomString(), randomBoolean())
//    }
//
//    fun makeProjectList(size: Int): List<Project> {
//        val projects = mutableListOf<Project>()
//        repeat(size){
//            projects.add(makeProject())
//        }
//        return projects
//    }
//}