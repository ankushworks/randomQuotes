package com.ankushsoni.randomquotes.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ankushsoni.randomquotes.viewpager.SmartFragmentStatePagerAdapter



class BottomBarAdapter(fragmentManager: FragmentManager?) :
    SmartFragmentStatePagerAdapter(fragmentManager) {
    private val fragments: MutableList<Fragment> = ArrayList()

    // Our custom method that populates this Adapter with Fragments
    fun addFragments(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}