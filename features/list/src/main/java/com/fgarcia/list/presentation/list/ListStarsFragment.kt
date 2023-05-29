package com.fgarcia.list.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import com.fgarcia.common.imageloader.ImageLoader
import com.fgarcia.common.arch.viewbinding.viewBinding
import com.fgarcia.list.databinding.FragmentListStarsBinding
import com.fgarcia.list.presentation.list.adapter.StarsListAdapter
import com.fgarcia.list.presentation.list.adapter.StartLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListStarsFragment : Fragment() {

    private val viewModel: ListStarsViewModel by viewModels()

    private val binding: FragmentListStarsBinding by viewBinding()

    private lateinit var listRepositoryAdapter: StarsListAdapter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentListStarsBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListStarsAdapter()
        observeInitialLoadState()
        getListStarsPagingData()
    }

    private fun getListStarsPagingData() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listStarsPagingData().collect { pagingData ->
                    listRepositoryAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initListStarsAdapter() {
        listRepositoryAdapter = StarsListAdapter(imageLoader)
        binding.recyclerStars.run {
            setHasFixedSize(true)
            adapter = listRepositoryAdapter.withLoadStateFooter(
                footer = StartLoadStateAdapter { listRepositoryAdapter.retry() }
            )
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            listRepositoryAdapter.loadStateFlow.collectLatest { loadState ->
              binding.flipperList.displayedChild = when (loadState.refresh) {
                    is Loading -> {
                        setShimmerVisibility(visibility = true)
                        FLIPPER_CHILD_LOADING
                    }
                    is NotLoading -> {
                        setShimmerVisibility(visibility = false)
                        FLIPPER_CHILD_LIST
                    }
                    is LoadState.Error -> {
                        setShimmerVisibility(visibility = false)
                        setOnClickRetryButton()
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewStarsLoadingState.shimmerStart.run {
            isVisible = visibility
            if (visibility) startShimmer() else stopShimmer()
        }
    }

    private fun setOnClickRetryButton() = with(binding) {
        includeViewStatsErrorState.buttonRetry.setOnClickListener {
            listRepositoryAdapter.retry()
        }
    }

    private companion object {
         const val FLIPPER_CHILD_LOADING = 0
         const val FLIPPER_CHILD_LIST = 1
         const val FLIPPER_CHILD_ERROR = 2
    }

}
