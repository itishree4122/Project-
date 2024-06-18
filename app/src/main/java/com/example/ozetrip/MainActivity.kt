package com.example.ozetrip

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val home = Home()
    private val settings = Settings()
    private val profile = Profile()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val drawerLayout: DrawerLayout = findViewById(R.id.main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { item ->
            // Handle navigation view item clicks here.
            val id = item.itemId

            // Close drawer after item is selected
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val menuIcon: ImageView = findViewById(R.id.menu_icon)
        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.main)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        supportFragmentManager.beginTransaction().replace(R.id.container, home).commit()

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, home).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, profile).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, settings).commit()
                    return@setOnItemSelectedListener true
                }
                // Add cases for other menu items if needed
                else -> return@setOnItemSelectedListener false
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val appBarLayout = findViewById<AppBarLayout>(R.id.appBarLayout)
        appBarLayout.setBackgroundColor(getColor(R.color.white))

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setBackgroundResource(R.drawable.bottom_nav_background)

        bottomNavigationView.itemIconTintList = resources.getColorStateList(R.color.white, theme)
        bottomNavigationView.itemTextColor = resources.getColorStateList(R.color.white, theme)

    }
}