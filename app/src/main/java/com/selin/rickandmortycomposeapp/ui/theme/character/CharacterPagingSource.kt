package com.selin.rickandmortycomposeapp.ui.theme.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.selin.rickandmortycomposeapp.data.repository.Repository
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList

class CharacterPagingSource(private val repo: Repository) : PagingSource<Int, CharacterResponseList>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponseList> {
        val page = params.key ?: 1
        return try {
            val response = repo.getAllCharacters()
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponseList>): Int? {
        TODO("Not yet implemented")
    }
}