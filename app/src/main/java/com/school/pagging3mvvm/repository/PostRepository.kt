package com.school.pagging3mvvm.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.school.pagging3mvvm.models.Post
import com.school.pagging3mvvm.pagging.PostPagingSource
import com.school.pagging3mvvm.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(private val apiService: ApiService) {
    fun getPosts()=Pager<Int, Post> (
        config = PagingConfig(
            pageSize = 10,
        ),
            pagingSourceFactory = { PostPagingSource(apiService) }
        ).liveData
    }
