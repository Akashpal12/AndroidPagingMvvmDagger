package com.school.pagging3mvvm.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.school.pagging3mvvm.models.Post
import com.school.pagging3mvvm.retrofit.ApiService

class PostPagingSource(private val apiService: ApiService) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPosts(currentPage, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
