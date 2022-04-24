package com.ankushsoni.randomquotes
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.ankushsoni.randomquotes.adapter.BottomBarAdapter
import com.ankushsoni.randomquotes.view.fragment.CategoryFragment
import com.ankushsoni.randomquotes.view.fragment.CustomizeFragment
import com.ankushsoni.randomquotes.view.fragment.FavouriteFragment
import com.ankushsoni.randomquotes.view.fragment.HomeFragment
import com.ankushsoni.randomquotes.viewpager.NoSwipePager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainActivity : AppCompatActivity() {

    lateinit var meowBottomNavigation : MeowBottomNavigation
    lateinit var chipNavigationBar: ChipNavigationBar
    private var viewPager: NoSwipePager? = null
    private var pagerAdapter: BottomBarAdapter? = null



    var navigation: BottomNavigationView? = null


    var homeFragment : HomeFragment? = HomeFragment()
    var categoryFragment : CategoryFragment? = CategoryFragment()
    var favouriteFragment : FavouriteFragment? = FavouriteFragment()
    var customizeFragment : CustomizeFragment? = CustomizeFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
        setViewPager()
//        setBottomNavigation()
        setChipBar()
    }

    private fun setChipBar() {
        chipNavigationBar.setOnItemSelectedListener {
            when (it) {
                R.id.home -> {
                    viewPager!!.currentItem = 0
                }
                R.id.category -> {
                    viewPager!!.currentItem = 1
                }
                R.id.favorite -> {
                    viewPager!!.currentItem = 2
                }
                R.id.customize -> {
                    viewPager!!.currentItem = 3
                }
            }
        }
    }


    override fun onResume() {
        FullScreencall()
        super.onResume()
    }

    fun initView(){
        navigation = findViewById(R.id.navigation);
//        meowBottomNavigation = findViewById(R.id.bottom_navigation)
        chipNavigationBar = findViewById(R.id.menu_bottom)
        viewPager = findViewById(R.id.viewPager)

    }


    fun FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v: View = this.window.decorView
            v.setSystemUiVisibility(View.GONE)
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView: View = window.decorView
            val uiOptions: Int =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.setSystemUiVisibility(uiOptions)
        }
    }






    private fun setViewPager(){

        viewPager?.offscreenPageLimit = 3
        viewPager?.setPagingEnabled(false)

        pagerAdapter = BottomBarAdapter(supportFragmentManager)

        pagerAdapter!!.addFragments(homeFragment!!)
        pagerAdapter!!.addFragments(categoryFragment!!)
        pagerAdapter!!.addFragments(favouriteFragment!!)
        pagerAdapter!!.addFragments(customizeFragment!!)

        viewPager!!.adapter = pagerAdapter
    }

    private fun setActionBar(){
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_toolbar_title_layout)
    }

}