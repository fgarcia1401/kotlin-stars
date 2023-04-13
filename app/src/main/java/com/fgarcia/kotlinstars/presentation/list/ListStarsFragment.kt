package com.fgarcia.kotlinstars.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fgarcia.kotlinstars.databinding.FragmentListStarsBinding
import com.fgarcia.kotlinstars.presentation.list.adapter.StarsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListStarsFragment : Fragment() {

    private var _binding: FragmentListStarsBinding ?= null
    private val binding: FragmentListStarsBinding get() = _binding!!

    private val viewModel: ListStarsViewModel by viewModels()

    private val listRepositoryAdapter = StarsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentListStarsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListStarsAdapter()
        getListStarsPagingData()
    }

    private fun getListStarsPagingData() {
        lifecycleScope.launch {
            viewModel.listStarsPagingData().collect { pagingData ->
                listRepositoryAdapter.submitData(pagingData)
            }
        }
    }

    private fun initListStarsAdapter() {
        binding.recyclerCharacters.run {
            setHasFixedSize(true)
            adapter = listRepositoryAdapter
        }
    }

}