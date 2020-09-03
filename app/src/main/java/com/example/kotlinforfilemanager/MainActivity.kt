package com.example.kotlinforfilemanager


import android.os.Bundle

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private var navigation_view: NavigationView? = null
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout =  findViewById(R.id.drawerLayout);
        navigation_view =  findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
// 用toolbar做為APP的ActionBar

        // 用toolbar做為APP的ActionBar
        setSupportActionBar(toolbar)

        // 將drawerLayout和toolbar整合，會出現「三」按鈕

        // 將drawerLayout和toolbar整合，會出現「三」按鈕
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

}