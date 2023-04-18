package com.fgarcia.kotlinstars.presentation.list

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
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import com.fgarcia.common.imageloader.ImageLoader
import com.fgarcia.common.viewbinding.viewBinding
import com.fgarcia.kotlinstars.databinding.FragmentListStarsBinding
import com.fgarcia.kotlinstars.presentation.list.adapter.StarsListAdapter
import com.fgarcia.kotlinstars.presentation.list.adapter.StartLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListStarsFragment : Fragment() {

    private val viewModel: ListStarsViewModel by viewModels()

    private val binding: FragmentListStarsBinding by viewBinding()

    private lateinit var listAdapter: StarsListAdapter

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
                    listAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initListStarsAdapter() {
        listAdapter = StarsListAdapter(imageLoader)
        binding.recyclerStars.run {
            setHasFixedSize(true)
            adapter = listAdapter.withLoadStateFooter(
                footer = StartLoadStateAdapter { listAdapter.retry() }
            )
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            listAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperList.displayedChild = when {
                    loadState.mediator?.refresh is Loading -> {
                        setShimmerVisibility(visibility = true)
                        FLIPPER_CHILD_LOADING
                    }
                    loadState.mediator?.refresh is Error && listAdapter.itemCount == 0 -> {
                        setShimmerVisibility(visibility = false)
                        setOnClickRetryButton()
                        FLIPPER_CHILD_ERROR
                    }
                    loadState.source.refresh is NotLoading
                            || loadState.mediator?.refresh is NotLoading -> showList()
                    else -> {
                         binding.recyclerStars.scrollToPosition(0)
                         showList()
                    }
                }
            }
        }
    }

    private fun showList(): Int {
        setShimmerVisibility(visibility = false)
        return FLIPPER_CHILD_LIST
    }


    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewStarsLoadingState.shimmerStart.run {
            isVisible = visibility
            if (visibility) startShimmer() else stopShimmer()
        }
    }

    private fun setOnClickRetryButton() = with(binding) {
        includeViewStatsErrorState.buttonRetry.setOnClickListener {
            listAdapter.retry()
        }
    }

    private companion object {
         const val FLIPPER_CHILD_LOADING = 0
         const val FLIPPER_CHILD_LIST = 1
         const val FLIPPER_CHILD_ERROR = 2
    }

}