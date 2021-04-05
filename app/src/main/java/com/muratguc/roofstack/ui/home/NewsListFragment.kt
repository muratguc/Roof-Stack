package com.muratguc.roofstack.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.muratguc.roofstack.common.LoaderStateAdapter
import com.muratguc.roofstack.common.convertLongToTime
import com.muratguc.roofstack.common.openInBrowser
import com.muratguc.roofstack.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    private val viewModel: NewsListViewModel by viewModels()

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private var newsListAdapter = NewsListRVA()

    companion object {
        fun newInstance() = NewsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {

        binding.apply {
            lifecycleOwner = viewLifecycleOwner

            newsList.adapter = newsListAdapter.withLoadStateFooter(LoaderStateAdapter())
            newsList.layoutManager = LinearLayoutManager(requireContext())
            newsListAdapter.onItemClick = {
                requireActivity().openInBrowser(requireActivity(), it.url)
            }

            filter.setOnClickListener {
                val builder = MaterialDatePicker.Builder.dateRangePicker().setSelection(
                    Pair(viewModel.from.value, viewModel.to.value)
                )
                val picker = builder.build()
                picker.show(requireActivity().supportFragmentManager, picker.toString())

                picker.addOnPositiveButtonClickListener {
                    picker.selection?.let { selection ->
                        viewModel.from.value = selection.first
                        viewModel.to.value = selection.second

                        newsListAdapter.refresh()
                    }
                }

            }
        }
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchNews().observe(viewLifecycleOwner) { pagingData ->
                newsListAdapter.submitData(lifecycle, pagingData)
            }
        }
    }

}