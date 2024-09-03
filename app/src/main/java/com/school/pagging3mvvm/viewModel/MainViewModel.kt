package com.school.pagging3mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.school.pagging3mvvm.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: PostRepository) : ViewModel() {
    val posts =repository.getPosts().cachedIn(viewModelScope)
}