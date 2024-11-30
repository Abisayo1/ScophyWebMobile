package com.abisayo.cloudspace_scophy

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.abisayo.cloudspace_scophy.databinding.ActivityMemberDashboardBinding
import com.google.android.material.navigation.NavigationView

class MemberDashboardActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding : ActivityMemberDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Ensure this line works

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            when(it.itemId){
                R.id.nav_home -> {
                    setToolbarTitle("Project")


                }
            }

            when(it.itemId){
                R.id.nav_tasks -> {
                    setToolbarTitle("Tasks")


                }
            }

            when(it.itemId){
                R.id.documents -> {
                    setToolbarTitle("Documents")

                    changeFragment(Documents())
                }
            }

            when(it.itemId){
                R.id.tickets -> {
                    setToolbarTitle("Tickets")

                    changeFragment(Tickets())
                }
            }

            when(it.itemId){
                R.id.open_ticket -> {
                    setToolbarTitle("Open New Ticket")

                    changeFragment(OpenNewTicket())
                }
            }

            when(it.itemId){
                R.id.replies -> {
                    setToolbarTitle("Predefined Replies")

                    changeFragment(PredefinedReplies())
                }
            }

            when(it.itemId){
                R.id.calender -> {
                    setToolbarTitle("Calender")

                    changeFragment(Calender())
                }
            }
            true
        }



        setToolbarTitle("Project")


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

    }

    fun setToolbarTitle(title:String){
        supportActionBar?.title = title
    }

    fun changeFragment(frag: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,frag).commit()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

