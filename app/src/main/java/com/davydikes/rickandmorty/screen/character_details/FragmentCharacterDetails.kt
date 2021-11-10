package com.davydikes.rickandmorty.screen.character_details

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.davydikes.rickandmorty.R
import com.davydikes.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.davydikes.rickandmorty.support.NavigationFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.davydikes.rickandmorty.support.setVerticalMargin
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class FragmentCharacterDetails :
    NavigationFragment<FragmentCharacterDetailsBinding>(R.layout.fragment_character_details) {

    override val viewBinding: FragmentCharacterDetailsBinding by viewBinding()

    private val args: FragmentCharacterDetailsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.characters?.let { item ->
            Picasso.get()
                .load(item.image)
                .into(viewBinding.tvImage, object : Callback {
                    override fun onSuccess() {
                        Log.d(ContentValues.TAG, "success1")
                    }

                    override fun onError(e: Exception?) {
                        Log.d(ContentValues.TAG, "error")
                    }
                })
            viewBinding.tvDetails.text = "name: ${item.name}\nstatus: ${item.status}" +
                    "\nspecies: ${item.species}\ngender: ${item.gender}"

        }
    }

    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
        viewBinding.toolbar.setVerticalMargin(marginTop = top)
    }

    override val backPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
}