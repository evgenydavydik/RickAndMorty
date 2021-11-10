package com.davydikes.rickandmorty.screen.main

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.davydikes.rickandmorty.R
import com.davydikes.rickandmorty.databinding.FragmentMainBinding
import com.davydikes.rickandmorty.support.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.davydikes.rickandmorty.models.Characters
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.davydikes.rickandmorty.support.navigateSafe

class FragmentMain : NavigationFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override val viewBinding: FragmentMainBinding by viewBinding()

    private val viewModel: ViewModelMain by viewModel()

    private val adapter = RecyclerViewAdapterCharacters(
        onClick = ::onItemClick
    )
    private var clickBtn: Boolean = false

    private fun onItemClick(character: Characters) {
        findNavController().navigateSafe(FragmentMainDirections.toCharacterDetails(character))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            clickBtn = savedInstanceState.getBoolean("onOf", false)
        }
        viewModel.networkConnectionLiveData.observe(this.viewLifecycleOwner, { isConnected ->
            if (isConnected) {
                viewBinding.layoutDisconnected.visibility = View.GONE
                viewBinding.recyclerView.visibility = View.VISIBLE
                viewBinding.recyclerView.adapter = adapter
                viewModel.importCharacters()
                viewModel.apiCharacterLiveData.observe(this.viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            } else if (!clickBtn) {
                viewBinding.layoutDisconnected.visibility = View.VISIBLE
                viewBinding.recyclerView.visibility = View.GONE
                viewBinding.btnOffline.setOnClickListener {
                    clickBtn = true
                    viewBinding.layoutDisconnected.visibility = View.GONE
                    viewBinding.recyclerView.visibility = View.VISIBLE
                    viewBinding.recyclerView.adapter = adapter
                    viewModel.charactersLiveData.observe(this.viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                }
            } else {
                viewBinding.layoutDisconnected.visibility = View.GONE
                viewBinding.recyclerView.visibility = View.VISIBLE
                viewBinding.recyclerView.adapter = adapter
                viewModel.charactersLiveData.observe(this.viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("onOf", clickBtn)
    }

    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
        viewBinding.toolbar.setVerticalMargin(marginTop = top)
        viewBinding.recyclerView.setPadding(0, 0, 0, bottom)
    }

    override val backPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
}
