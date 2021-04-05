package com.muratguc.roofstack.ui.repodetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muratguc.roofstack.data.model.Article
import com.muratguc.roofstack.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {
    private var article: Article? = null

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val REPO_RESPONSE = "param1"

        fun newInstance(newsListResponseModel: Article) = NewsDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_RESPONSE, newsListResponseModel)
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
            article = it.getParcelable(REPO_RESPONSE)
        }
    }

    private fun initViews() {
        article?.let {
            binding.apply {
                lifecycleOwner = viewLifecycleOwner
                article = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}