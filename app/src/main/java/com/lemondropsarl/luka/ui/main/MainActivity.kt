package com.lemondropsarl.luka.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.UserModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*
import javax.inject.Inject

@SuppressLint("unused")
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, NavigationView.OnNavigationItemSelectedListener {


    companion object {
        const val RC_SIGN_IN = 123
    }

    //private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    //private lateinit var mBinding : ActivityMainBinding

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setup Navigation
        setupNavigation()
        //Bind User Information
        setupHeader()

    }

    private fun setupHeader() {


        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            startSigning()
        } else {
            //Update UI
            val model = UserModel(currentUser.email, currentUser.displayName, currentUser.photoUrl)

            updateProfile(model)
        }
    }


    private fun updateProfile(user: UserModel) {
        val header = navigation_view.getHeaderView(0)
        header.headerEmail.text = user.email
        header.headerUsername.text = user.username
        Glide.with(header.headerProfile.context)
            .load(user.profileUrl)
            .into(header.headerProfile)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            //val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                //get user detail
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {

                    val model = UserModel(user.email, user.displayName, user.photoUrl)

                    updateProfile(model)
                }
            } else {
                //check response error
            }
        }
    }

    private fun startSigning() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.AppTheme_NoActionBar)
                .build(), RC_SIGN_IN
        )


    }



    private fun setupNavigation() {


        navController = Navigation.findNavController(this, R.id.fragment_Host)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.FeedListFragment, R.id.HomeFragment), drawer
        )

        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)


        setupActionBarWithNavController(navController, appBarConfiguration)
        //set the header  view

        /*val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.open_drawer, R.string.close_drawer
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()*/

        navigation_view.let { NavigationUI.setupWithNavController(it, navController) }
        navigation_view.setNavigationItemSelectedListener(this)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> navController.navigate(R.id.HomeFragment)
            R.id.nav_feed -> navController.navigate(R.id.FeedListFragment)
        }


        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {

            super.onBackPressed()
        }

    }

}
