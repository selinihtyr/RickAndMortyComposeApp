package com.selin.rickandmortycomposeapp.ui.theme.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.selin.rickandmortycomposeapp.data.repository.Repository
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList

class CharacterPagingSource(private val repo: Repository) : PagingSource<Int, CharacterResponseList>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponseList> {
        return try {
            val currentPage = params.key ?: 1
            val characters = repo.getCharactersByPage(currentPage)

            LoadResult.Page(
                data = characters,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (characters.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponseList>): Int? {
        TODO("Not yet implemented")
    }
}