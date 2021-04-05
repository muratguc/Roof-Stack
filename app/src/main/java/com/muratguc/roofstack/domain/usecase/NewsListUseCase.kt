package com.muratguc.roofstack.domain.usecase

import com.muratguc.roofstack.data.model.Article
import com.muratguc.roofstack.data.model.NewsListResponseModel
import com.muratguc.roofstack.domain.repository.NewsService
import javax.inject.Inject

/**
 * Created by Murat Güç on 2/1/2021.
 */
class NewsListUseCase @Inject constructor(
    private val newsService: NewsService
) {
    suspend fun newsList(from: String?, to: String?, page: Int, pageSize: Int): List<Article> {
        return newsService.getNews(from = from, to = to, page = page, pageSize = pageSize).articles
    }

}