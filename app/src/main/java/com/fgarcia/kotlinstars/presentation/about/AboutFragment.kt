package com.fgarcia.kotlinstars.presentation.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fgarcia.common.imageloader.ImageLoader
import com.fgarcia.common.usecase.base.ResultStatus
import com.fgarcia.common.viewbinding.viewBinding
import com.fgarcia.kotlinstars.databinding.FragmentAboutBinding
import com.fgarcia.kotlinstars.databinding.IncludeAboutBinding
import com.fgarcia.kotlinstars.domain.model.Author
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AboutFragment : Fragment() {

    private val binding: FragmentAboutBinding by viewBinding()

    private val viewModel: AboutViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAboutBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInitialLoadState()
        getListStarsPagingData()
    }

    private fun getListStarsPagingData() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(STARTED) {
                viewModel.getDataAuthor()
            }
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.flipperList.displayedChild = when (uiState) {
                        is ResultStatus.Loading -> FLIPPER_CHILD_LOADING
                        is ResultStatus.Success -> showAuthor(author = uiState.data)
                        is ResultStatus.Error -> FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }

    private fun showAuthor(author: Author): Int = with(binding.includeAbout) {
        textName.text = author.login
        showImage(author)
        return FLIPPER_CHILD_AUTHOR
    }

    private fun IncludeAboutBinding.showImage(author: Author) {
        imageLoader.load(
            image = imageAuthor,
            imageUrl = author.photoUrl
        )
    }


    private companion object {
        const val FLIPPER_CHILD_LOADING = 0
        const val FLIPPER_CHILD_AUTHOR = 1
        const val FLIPPER_CHILD_ERROR = 2
    }


}