package com.muratguc.roofstack.data.datasourse

import androidx.paging.PagingSource
import com.muratguc.roofstack.data.model.Article
import com.muratguc.roofstack.data.model.NewsListResponseModel
import com.muratguc.roofstack.domain.usecase.NewsListUseCase
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Murat Güç on 2/1/2021.
 */
class NewsListPagingDataSource(
    private val useCase: NewsListUseCase,
    private val from: String?,
    private val to: String?
) :
    PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = useCase.newsList(from, to, position, params.loadSize)
            val list = response
            LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = if (list.isEmpty()) null else position.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}