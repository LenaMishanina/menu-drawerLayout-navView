package com.practicum.drawerlayoutlesson

/**     Урок 30-31     **/

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.practicum.drawerlayoutlesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavView()

        binding.apply {
            btnOpen.setOnClickListener {
                main.openDrawer(GravityCompat.START)//выдвинуть слева направо
//                main.closeDrawer(GravityCompat.START)//задвинуть слева направо
            }
        }
    }

    private fun initNavView() = with(binding) {
        navView.setNavigationItemSelectedListener {
            //it - item на который нажали
            when (it.itemId) {
                R.id.item1 -> Toast.makeText(this@MainActivity, "item1", Toast.LENGTH_SHORT).show()
                R.id.item2 -> Toast.makeText(this@MainActivity, "item2", Toast.LENGTH_SHORT).show()
                R.id.item3 -> Toast.makeText(this@MainActivity, "item3", Toast.LENGTH_SHORT).show()
            }
            main.closeDrawer(GravityCompat.START)//при нажатии на любой item меню задвигается
            true
        }
    }
}