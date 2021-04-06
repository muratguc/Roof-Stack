package com.muratguc.roofstack.ui.newsdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.muratguc.roofstack.data.model.Article
import com.muratguc.roofstack.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {
    private var article: Article? = null

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARTICLE_RESPONSE = "article_response"

        fun newInstance(newsListResponseModel: Article) = NewsDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARTICLE_RESPONSE, newsListResponseModel)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getParcelable(ARTICLE_RESPONSE)
        }
    }

    private fun initViews() {
        article?.let {
            binding.apply {
                lifecycleOwner = viewLifecycleOwner
                article = it

                newsContent.settings.apply {
                    javaScriptEnabled = true
                    javaScriptCanOpenWindowsAutomatically = true
                    domStorageEnabled = true
                    setSupportZoom(false)
                    builtInZoomControls = true
                }

                newsContent.webViewClient = object : WebViewClient() {

                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        return true
                    }
                }
                newsContent.loadUrl(it.url)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}