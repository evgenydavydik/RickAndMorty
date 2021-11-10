package com.davydikes.rickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.davydikes.rickandmorty.databinding.ActivityMainBinding
import com.davydikes.rickandmorty.support.SupportActivityInset
import com.davydikes.rickandmorty.support.setWindowTransparency

class MainActivity : SupportActivityInset<ActivityMainBinding>() {
    override lateinit var viewBinding: ActivityMainBinding

    private val navHostFragment by lazy { (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setWindowTransparency(this)
    }

    override fun getActiveFragment(): Fragment? {
        return navHostFragment.childFragmentManager.fragments[0]
    }
}