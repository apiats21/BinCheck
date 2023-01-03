package com.example.bincheck.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.bincheck.R
import com.example.bincheck.databinding.ActivityMainBinding
//import com.example.bincheck.ui.binList.BinListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        observeViewModel()
        initializeViews()
    }

    private fun initializeViews() {
        binding.loadInfoBtn.setOnClickListener {
            val binText = binding.binEdit.text.toString()
            if (binText.isNotEmpty()) {
                viewModel.loadCardFromApi(binText)
            } else {
                showToast("Enter text!")
            }
        }
        binding.binEdit.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val DRAWABLE_RIGHT = 2
                when (event?.action) {
                    MotionEvent.ACTION_UP -> if(event.getRawX() >= (binding.binEdit.getRight() - binding.binEdit.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        binding.binEdit.setText("")
                        return true;
                    }
                }
                return false
            }
        })
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            binding.binLoad.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.cardStock.observe(this) {
            it?.let {
                showToast("Card ${it.bin} was added successfully")
                startActivity(Intent(this, BinListActivity::class.java))
            }
        }

        viewModel.toastError.observe(this) {
            showToast(it, Toast.LENGTH_LONG)
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}